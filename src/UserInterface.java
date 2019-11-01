import java.util.Scanner;

public class UserInterface {
	public static void main(String[] args) {
		
		int main_option=0,sorting_option=0,search_option=0,edit_option=0;
		String proceed,search_key,edit_key;
		Scanner scanner = new Scanner(System.in);
		Controller controller = new Controller();
		//bootstrap default users
		controller.initialize();
		
		do {
			//display main menu
			System.out.println("************Menu***********");
			System.out.println("1. Add user \n2. Search user/Delete user/n3. Edit user \n3. List all users");
			System.out.println("Please enter your choice: ");
			main_option = scanner.nextInt();
			
			switch(main_option)
			{
			case 1:
			{
				//take input for all fields for a new user.
				System.out.println("Enter first name:");
				String first_name = scanner.next();
				System.out.println("Enter last name:");
				String last_name = scanner.next();
				System.out.println("Enter phone number:");
				String phone = scanner.next();
				System.out.println("Enter email:");
				String email = scanner.next();
				System.out.println("Enter company name:");
				String company = scanner.next();
				//add method called
				controller.add(first_name,last_name,phone,email,company);
				break;
			}
			case 2:
			{
				//The search,remove and edit functionality has been combine for code reuse
				// The search will work with first name,last name or full name.
				System.out.println("Please enter name of the user you want to search: ");
				search_key = scanner.next();
				boolean found_flag=controller.display_record(search_key);
				//If a record is found, the user can be removed or edited.
				if(found_flag==true) {
					System.out.println("1. Remove user \n2. Edit user \n3. Exit \nPlease enter your choice:");
					int ch=scanner.nextInt();
					switch(ch) {
					case 1:
					{
						//remove user
						controller.remove(search_key);
						break;
					}
					case 2:
					{
						//edit user
						System.out.println("1. Edit phone number \n2. Edit email \n3. Edit company \nPlease Enter your choice");
						edit_option = scanner.nextInt();
						System.out.println("Enter new value");
						edit_key= scanner.next();
						controller.edit(search_key, edit_option, edit_key);
						break;
					}
					case 3:
					{
						break;
					}
					}
				}

				break;
				
			}
			case 3:
			{
				//display names of all users
				System.out.println("1. sort by first name \n2. sort by last name");
				System.out.println("Please enter your choice: ");
				sorting_option = scanner.nextInt();
				controller.display_all(sorting_option);
				break;
			}
			default:
			{
				System.out.println("Please enter valid option");
				break;
			}
			}
			//check if user wants to go back to main menu or exit
			System.out.println("Press y to go back to main menu / n to exit");
			proceed = scanner.next();			
		}while(proceed.equals("y"));
		
		
		
		
		
	}

}
