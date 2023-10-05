package ApplicationMain;

import java.io.IOException;
import java.util.Scanner;

import InventoryManagement.BookStoreManagement;
import UserManagement.User;
import UserManagement.User_Management;

public class BookStoreInventory {
	public static void main(String args[]) throws IOException {
		Scanner sc = new Scanner(System.in);

		boolean CanIKeepRunningTheProgram = true;
		while(CanIKeepRunningTheProgram == true) {

			System.out.println("@@@@@@ WELCOME TO BOOK STORE INVENTORY MANAGEMENT @@@@@@");
			System.out.println("\n");

			System.out.println("Select an option:");
			System.out.println("1. Sign Up");
			System.out.println("2. Log In");
			System.out.println("3. Exit");

			int option = sc.nextInt();
			
			if(option == 1) {
				// Sign Up
				User_Management.AddUser();

				User_Management.WriteDataIntoTextFile();

				System.out.println("Sign up successful! You can now log in.");
				
				User_Management.LoadDataIntoArrayList();
			}
			else if(option == 2) {
				
				
				System.out.println("Enter the Login_Name :");
				String loginName = sc.next();

				System.out.println("Enter the Password :");
				String password = sc.next();

				if (!User_Management.isValidateUserAndPassword(loginName, password)) {
					System.out.println("!!!! LOGIN FAILED. CLOSE THE APPLICATION !!!!!!");
					return;
				} 
				else {
					System.out.println("@@@@@ LOGIN SUCCESSFUL @@@@@");

					while(CanIKeepRunningTheProgram == true) {
						System.out.println("@@@@@@ WELCOME TO BOOK STORE INVENTORY MANAGEMENT @@@@@@");
						System.out.println("\n");

						System.out.println("What would you like to do?");
						System.out.println("1. User_Management");
						System.out.println("2. Book_Store_Inventory Management");
						System.out.println("3. Exit");

						int OptionSelectedByUser = sc.nextInt();

						if(OptionSelectedByUser == 1) {
							User_Management.UserManagement();
						}
						else if(OptionSelectedByUser == 2) {
							BookStoreManagement.InventoryManagement();
						}
						else if(OptionSelectedByUser == 3) {
							System.out.println("Application closed!!");
							break;
						}
					}
				}
			}
			else if(option == 3) {
				CanIKeepRunningTheProgram = false;
			}
			else if(option != 1||option != 2||option != 3){
				System.out.println("Option selected is Invalid!!\n\n");
				System.out.println("Please re-enter your option\n\n");
			}
		}
	}
}

