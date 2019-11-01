//controller class. Contains various functions which are invoked in the UI class.
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Controller {
	
	static ArrayList<Person> person_list = new ArrayList<Person>();
	Scanner scanner = new Scanner(System.in);
	
	//Function to bootstrap default users.
	public void initialize() {
		
		person_list.add(new Person("john","smith",new Address("12345","abc@gmail.com","google")));
		person_list.add(new Person("kyle","morgan",new Address("88888","xyz@yahoo.com","facebook")));
		person_list.add(new Person("steven","rooney",new Address("77777","pqr@gmail.com","accenture")));
				
	}
	
	//function to search user. It is invoked in the add,remove and edit functions.
	public static ArrayList<Person> search(String search_key) {
		ArrayList<Person> found_list = new ArrayList<Person>();
		 //The search key can be first name,last name or full name
			for(Person p: person_list) {
				if(p.first_name.equalsIgnoreCase(search_key) || p.last_name.equalsIgnoreCase(search_key)
						|| (p.first_name+" "+p.last_name).equalsIgnoreCase(search_key)) {
					found_list.add(p);
					
				}
			}
		return found_list;
	}
	
	
	//Function to add new user
	public void add(String first_name, String last_name,String phone,String email,String company) {
		//check for duplicate records
		if(search(first_name+" "+last_name).isEmpty()) {
			person_list.add(new Person(first_name,last_name,new Address(phone,email,company)));
			System.out.println("Record added successfully.");
		}
		else {
			System.out.println("Record with this name already exists");
		}

	}
	
	//function to remove user
	public void remove(String search_key) {
		ArrayList<Person> found_list = search(search_key);
		if(found_list.size()==0)
		{
			System.out.println("No record found.");
		}
		else if(found_list.size()==1)
		{
			for(Person p: found_list)
			{
				person_list.remove(p);
			}
			
			System.out.println("Record removed successfully.");
		}
		else {
			//if multiple users found, confirm before deleting 
			System.out.println(found_list.size()+" records found. Do you want to delete the records(y/n)");
			String ch = scanner.next();
			if(ch.equalsIgnoreCase("y")) {
				for(Person p: found_list)
				{
					person_list.remove(p);
				}
				System.out.println("Records removed successfully.");
			}
			else {
				System.out.println("No records removed.");
			}
			
			
		}
		
	}
	
	//Function to edit user
	public void edit(String search_key,int edit_option,String edit_key) {
		ArrayList<Person> found_list = search(search_key);
		if(found_list.size()==0)
		{
			System.out.println("No record found.");
		}
		else
		{
			if(found_list.size()==1) {
				for(Person p: found_list) {
					person_list.remove(p);
					if(edit_option==1)
					{
						p.address_obj.phone = edit_key;
					}
					else if(edit_option==2) {
						p.address_obj.email = edit_key;
					}
					else {
						p.address_obj.company = edit_key;
					}
					person_list.add(p);
				}
				System.out.println("Record updated successfully.");
				
			}
			else {
				//If multiple records found, ask for the full name of the user
				System.out.println(found_list.size()+" records found. Please enter the full name of the user you want to edit");
				String ch = scanner.next();
				found_list = search(ch);
				if(found_list.size()!=0)
				{
					for(Person p: found_list) {
						person_list.remove(p);
						if(edit_option==1)
						{
							p.address_obj.phone = edit_key;
						}
						else if(edit_option==2) {
							p.address_obj.email = edit_key;
						}
						else {
							p.address_obj.company = edit_key;
						}
						person_list.add(p);
					}
					System.out.println("Record updated successfully.");
				}
				else {
					System.out.println("No record found.");
				}	
			}
		}
		
	}
	

	//function to display full info 
	public static boolean display_record(String search_key) {
		ArrayList<Person> found_list = search(search_key);
		if(found_list.size()==0)
		{
			System.out.println("No record found.");
			return false;
		}
		else
		{
			for(Person p: found_list) {
				System.out.println("First name: "+p.first_name);
				System.out.println("Last name: "+p.last_name);
				System.out.println("Phone: "+p.address_obj.phone);
				System.out.println("Email: "+p.address_obj.email);
				System.out.println("Company: "+p.address_obj.company);
				System.out.println("------------------------");
			}
			return true;
		}
	}
	
	//function to display the names of all users sorted by first name or last name
	public void display_all(int sorting_option) {
		
		ArrayList<String> names = new ArrayList<String>();
		
		
			if(sorting_option==1)
			{
				for(Person p: person_list) {
					names.add(p.first_name+" "+p.last_name);
				}
			}
			else
			{
				for(Person p: person_list) {
					names.add(p.last_name+" "+p.first_name);
				}				
			}
		
		
		Collections.sort(names);
		System.out.println("First_name  Last_name");
		for(String x:names) {
			String[] y = x.split(" ");
			if(sorting_option==1)
			{
				System.out.println(y[0]+"\t\t"+y[1]);
			}
			else {
				System.out.println(y[1]+"\t\t"+y[0]);
			}
		}
		
	}

}
