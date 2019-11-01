import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ControllerTest {
    Controller controller = new Controller();

    @Test
    //Check if all default users have been initialized
    public void initialize() {
        controller.initialize();
        assertEquals(Controller.person_list.size(),4);
    }

    @Test
    // Check if new user is added. search functionality is also tested.
    public void add() {
        controller.add("wayne", "johnson", "12234", "abc", "cts");
        boolean found = Controller.search("wayne johnson").isEmpty();
        //If the record is added, the list would not be empty and the value of found would be false
        assertEquals(found,false);
    }

    @Test
    // Check if user is edited. search functionality is also tested.
    public void edit() {
        // 1 implies phone number and 9999 is the updated value
        controller.edit("wayne johnson",1,"9999");
        ArrayList<Person> p_returned = Controller.search("wayne johnson");
        // check if the updated value is equal to 9999
        assertTrue(p_returned.get(0).address_obj.phone=="9999");

        //check for email
        controller.edit("wayne johnson",2,"xyz");
        p_returned = Controller.search("wayne johnson");
        assertTrue(p_returned.get(0).address_obj.email=="xyz");

        //check for company name
        controller.edit("wayne johnson",3,"accenture");
        p_returned = Controller.search("wayne johnson");
        assertTrue(p_returned.get(0).address_obj.company=="accenture");
    }

    @Test
    public void remove() {
        controller.remove("wayne johnson");
        boolean found = Controller.search("wayne johnson").isEmpty();
        //If the record is removed, the list would not be empty and the value of found would be true
        assertEquals(found,true);
    }
}