package InventoryManagement;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class BookStoreManagement {
	static ArrayList<Book> al = new ArrayList<>();
	
	static {
		try {
			LoadDataIntoArrayList();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	public static void InventoryManagement() throws IOException {
		Scanner sc = new Scanner(System.in);
		
		boolean CanIKeepRunningTheProgram = true;
		
		while(CanIKeepRunningTheProgram == true) {
			System.out.println("\n###### WELCOME TO INVENTORY MANAGEMENT #######");
			System.out.println("\n");
			
			System.out.println("What would you like to Do?");
			System.out.println("1. Add Book");
			System.out.println("2. Update Book");
			System.out.println("3. Search Book");
			System.out.println("4. Delete Book");
			System.out.println("5. Purchase Book");
			System.out.println("6. Exit");
			
			int OptionSeletedByUser = sc.nextInt();
			
			if(OptionSeletedByUser == Book_Option.ADD_BOOK) {
				Add_Book();
				System.out.println("\n");
			}
			else if(OptionSeletedByUser == Book_Option.UPDATE_BOOK) {
				System.out.println("Book's Present in inventory are as follows :\n\n");
				DisplayBook();
				System.out.println("\n\nEnter the Name of the Book which you want to Update is:");
				sc.nextLine();
				String ub = sc.nextLine();
				Update_Book(ub);
				System.out.println("\n");
			}
            else if(OptionSeletedByUser == Book_Option.SEARCH_BOOK) {
            	System.out.println("Book's Present in inventory are as follows :\n\n");
            	DisplayBook();
            	System.out.println("\n\nEnter the Name of the Book which you want to Search is:");
				sc.nextLine();
				String sb = sc.nextLine();
				Search_Book(sb);
				System.out.println("\n");
			}
            else if(OptionSeletedByUser == Book_Option.DELETE_BOOK) {
            	System.out.println("Book's Present in inventory are as follows :\n\n");
            	DisplayBook();
            	System.out.println("\n\nEnter the Name of the Book which you want to Delete is:");
				sc.nextLine();
				String db = sc.nextLine();
				Delete_Book(db);
				System.out.println("\n");
			}
            else if(OptionSeletedByUser == Book_Option.PURCHASE_BOOK) {
            	System.out.println("Book's Present in inventory are as follows :\n\n");
            	DisplayBook();
            	PurchaseBooks();
            }
            else if(OptionSeletedByUser == Book_Option.EXIT) {
            	WriteDataIntoTextFile();
				System.out.println("!!PROGRAM CLOSED!!");
				CanIKeepRunningTheProgram = false;
			}
		}
		System.out.println("\n");
	}
	public static void Add_Book() {
		Scanner sc = new Scanner(System.in);
		Book b = new Book();
		
		System.out.print("Enter the Book_Title :");
		b.Book_Title = sc.nextLine();
		
		System.out.print("Enter the Book_ISBN :");
		b.Book_ISBN = sc.nextLine();
		
		System.out.print("Enter the Author_Name :");
		b.Author_Name = sc.nextLine();
		
		System.out.print("Enter the Quantity :");
		b.Quantity = sc.nextLine();
		
		System.out.print("Enter the Book_Price per book :");
		b.Price = sc.nextLine();
		
		System.out.println("Enter Book_Name is : "+b.Book_Title);
		System.out.println("Enter Book_ISBN is : "+b.Book_ISBN);
		System.out.println("Enter Author_Name is : "+b.Author_Name);
		System.out.println("Enter Quantity is : "+b.Quantity);
		System.out.println("Enter Book_Price is : "+b.Price);
		
		al.add(b);
	}
	public static void Update_Book(String Book_Title) {
		for(Book b:al) {
			if(b.Book_Title.equalsIgnoreCase(Book_Title)) {
				
				Scanner sc = new Scanner(System.in);
				
				System.out.println("Enter the Book_Name which you want to Update :"+b.Book_Title);
				
				System.out.print("New Book_Title is:");
				b.Book_Title = sc.nextLine();
				
				System.out.print("New Book_ISBN is:");
				b.Book_ISBN = sc.nextLine();
				
				System.out.print("New Author_Name is:");
				b.Author_Name = sc.nextLine();
				
				System.out.print("New Quantity is:");
				b.Quantity = sc.nextLine();
				
				System.out.print("New the Book_Price :");
				b.Price = sc.nextLine();
				
				System.out.println("Book Information Updated");
				return;
			}
		}
		System.out.println("Book Not Found!!");
	}
	public static void Search_Book(String Book_Title) {
		for(Book b:al) {
			if(b.Book_Title.equalsIgnoreCase(Book_Title)) {
				System.out.println("Book_Title is :"+b.Book_Title);
				System.out.println("Book_ISBN is :"+b.Book_ISBN);
				System.out.println("Author Name is :"+b.Author_Name);
				System.out.println("Quantity is :"+b.Quantity);
				System.out.println("Enter Book_Price is : "+b.Price);
			}
		}
		System.out.println("Book Not Found!!");
	}
	public static void Delete_Book(String Book_Title) {
		Iterator<Book> itr = al.iterator();
		while(itr.hasNext()) {
			Book b = itr.next();
			if(b.Book_Title.equalsIgnoreCase(Book_Title)) {
				itr.remove();
				System.out.println("Book "+b.Book_Title+" has been deleted");
				break;
			}
		}
	}
	public static void WriteDataIntoTextFile() throws IOException {
		
		File file = new File("C:\\Users\\ganuk\\eclipse-workspace\\Book-Store-Inventory\\src\\InventoryManagement\\Book.csv");
		
		FileWriter fw = new FileWriter(file,false);
		
		BufferedWriter bw = new BufferedWriter(fw);
		
		for(Book b:al) {
			
			bw.write(b.Book_Title+",");
			bw.write(b.Book_ISBN+",");
			bw.write(b.Author_Name+",");
			bw.write(b.Quantity+",");
			bw.write(b.Price);
			bw.newLine();
		}
		bw.close();
		fw.close();
		

	}
	public static void LoadDataIntoArrayList() throws IOException {
		
		File file = new File("C:\\Users\\ganuk\\eclipse-workspace\\Book-Store-Inventory\\src\\InventoryManagement\\Book.csv");
		
		FileReader fr = new FileReader(file);
		
		BufferedReader br = new BufferedReader(fr);
		
		String Data = " ";
		
		while((Data = br.readLine()) != null) {
			
			String[] array = Data.split(",");
			Book b = new Book();
			if(array.length>4) {
				b.Book_Title = array[0].trim();
				b.Book_ISBN = array[1].trim();
				b.Author_Name = array[2].trim();
				b.Quantity = array[3].trim();
				b.Price = array[4].trim();
				
				al.add(b);
			}
		}
		fr.close();
		br.close();
		file = null;
	}
	public static void DisplayBook() throws IOException {
		System.out.println("@@@@ DISPLAY BOOK RECORD @@@@@");

		File file = new File("C:\\Users\\ganuk\\eclipse-workspace\\Book-Store-Inventory\\src\\InventoryManagement\\Book.csv");

		FileReader fr = new FileReader(file);

		BufferedReader br = new BufferedReader(fr);

		String data = " ";

		while((data = br.readLine()) != null) {

			String[] array = data.split(",");
			Book b = new Book();
			if(array.length>4) {
				System.out.println(b.Book_Title = array[0].trim());
			}
		}
	}
	public static void PurchaseBooks() {
		Scanner sc = new Scanner(System.in);

		System.out.println("\nEnter the name of the Book you want to purchase:");
		String BookName = sc.nextLine();

		Book purchaseBook = null;
		for(Book book:al) {
			if(book.Book_Title.equalsIgnoreCase(BookName)) {
				purchaseBook = book;
				break;
			}
		}
		if(purchaseBook != null) {
			System.out.println("\nEnter the quantity you want to purchase:");
			int quantity = sc.nextInt();

			if(quantity > Integer.parseInt(purchaseBook.Quantity)) {
				System.out.println("Not enough quantity available in the inventory.");
			}
			else {
				int TotalPrice = quantity * Integer.parseInt(purchaseBook.Price);

				System.out.println("Total Price : "+TotalPrice);
				
				purchaseBook.Quantity = String.valueOf(Integer.parseInt(purchaseBook.Quantity) - quantity);

				System.out.println("\nPurchase successful.");
				
				System.out.println("\nAvailable Quantity :"+purchaseBook.Quantity);
			}
		}
		else {
			System.out.println("Product not found in the inventory.");
		}
	}
}