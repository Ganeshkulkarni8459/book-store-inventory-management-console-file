package UserManagement;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class User_Management {
	static ArrayList<User> al = new ArrayList<>();
	static{
		try {
			LoadDataIntoArrayList();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	public static void UserManagement() throws IOException {
		Scanner sc = new Scanner(System.in);

		boolean CanIKeepRunningTheProgram = true;

		while(CanIKeepRunningTheProgram == true) {
			System.out.println("@@@@@@ WELCOME TO USER_MANAGEMENT PROGRAM @@@@@@");
			System.out.println("\n");

			System.out.println("What Would You Like To Do?");
			System.out.println("1. ADD USER");
			System.out.println("2. EDIT USER");
			System.out.println("3. DELETE USER");
			System.out.println("4. SEARCH USER");
			System.out.println("5. QUIT");

			int OptionSelectedByUser = sc.nextInt();

			if(OptionSelectedByUser == User_Option.QUIT) {
				WriteDataIntoTextFile();
				System.out.println("!!PROGRAM CLOSED!!");
				CanIKeepRunningTheProgram = false;
			}
			else if(OptionSelectedByUser == User_Option.ADD_USER) {
				AddUser();
				System.out.println("\n");
			}
			else if(OptionSelectedByUser == User_Option.EDIT_USER) {
				System.out.println("Enter the name of the User which you want to Edit :");
				sc.nextLine();
				String eu = sc.nextLine();
				EditUser(eu);
				System.out.println("\n");

			}
			else if(OptionSelectedByUser == User_Option.DELETE_USER) {
				System.out.println("Enter the name of the User which you want to Delete :");
				sc.nextLine();
				String du = sc.nextLine();
				Delete_User(du);
				System.out.println("\n");

			}
			else if(OptionSelectedByUser == User_Option.SEARCH_USER) {
				System.out.println("Enter the name of the User which you want to Search :");
				sc.nextLine();
				String su = sc.nextLine();
				Search_User(su);
				System.out.println("\n");
			}
		}
		System.out.println("\n");

	}
	public static void AddUser() {
		Scanner sc = new Scanner(System.in);
		User u = new User();

		System.out.print("Enter UserName :");
		u.Username = sc.nextLine();

		System.out.print("Enter Login-Name :");
		u.LoginName = sc.nextLine();

		System.out.print("Enter Password :");
		u.Password = sc.nextLine();

		System.out.print("Enter User-ID :");
		u.UserID = sc.nextLine();

		System.out.println("\n");
		System.out.println("Enter UserName is: "+u.Username);
		System.out.println("Enter Login-Name is: "+u.LoginName);
		System.out.println("Enter Password is: "+u.Password);
		System.out.println("Enter User-ID is: "+u.UserID);

		al.add(u);
	}
	public static void EditUser(String Username) {
		for(User u:al) {
			if(u.Username.equalsIgnoreCase(Username)) {

				Scanner sc = new Scanner(System.in);

				System.out.println("Edit User Name is :"+u.Username);

				System.out.print("New Username is :");
				u.Username = sc.nextLine();

				System.out.print("New Login_Name is :");
				u.LoginName = sc.nextLine();

				System.out.print("New Password is :");
				u.Password = sc.nextLine();

				System.out.print("New UserID is :");
				u.UserID = sc.nextLine();

				System.out.println("User Information Updated");
				return;

			}
		}
		System.out.println("User Not Found!!");
	}
	public static void Search_User(String Username) {
		for(User u:al) {
			if(u.Username.equalsIgnoreCase(Username)) {
				System.out.println("User-Name is :"+u.Username);
				System.out.println("Login_Name is :"+u.LoginName);
				System.out.println("Password is :"+u.Password);
				System.out.println("UserID is :"+u.UserID);
			}
		}
		System.out.println("User Not Found!!");
	}
	public static void Delete_User(String Username) {
		Iterator<User> itr = al.iterator();
		while(itr.hasNext()) {
			User u = itr.next();
			if(u.Username.equalsIgnoreCase(Username)) {
				itr.remove();
				System.out.println("User "+u.Username+" has been deleted");
				break;
			}
		}
	}
	public static void WriteDataIntoTextFile() throws IOException {

		File file = new File("C:\\Users\\ganuk\\eclipse-workspace\\Book-Store-Inventory\\src\\UserManagement\\User.csv");

		FileWriter fw = new FileWriter(file,false);

		BufferedWriter bw = new BufferedWriter(fw);

		for(User u:al) {

			bw.write(u.Username+",");
			bw.write(u.LoginName+",");
			bw.write(u.Password+",");
			bw.write(u.UserID);
			bw.newLine();
		}
		bw.close();
		fw.close();
		file = null;
	}
	public static void LoadDataIntoArrayList() throws IOException {
		File file = new File("C:\\Users\\ganuk\\eclipse-workspace\\Book-Store-Inventory\\src\\UserManagement\\User.csv");

		FileReader fr = new FileReader(file);

		BufferedReader br = new BufferedReader(fr);

		String Data = "";

		while((Data = br.readLine()) != null) {

			String[] array = Data.split(",");
			User u = new User();
			if(array.length > 3) {
				u.Username = array[0].trim();
				u.LoginName = array[1].trim();
				u.Password = array[2].trim();
				u.UserID = array[3].trim();

				al.add(u);
			}
		}
		file =  null;
		fr.close();
		br.close();
	}
	public static boolean isValidateUserAndPassword(String LoginName,String Password) {
		Iterator<User> itr = al.iterator();
		while(itr.hasNext()) {
			User u = itr.next();
			if(u.LoginName.equalsIgnoreCase(LoginName) && u.Password.equalsIgnoreCase(Password)) {
				return true;
			}
		}
		return false;
	}

}
