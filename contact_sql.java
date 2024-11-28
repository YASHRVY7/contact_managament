package contact_manager_sql;
import java.sql.*;
import java.util.*;

class Contact{
	private int id;
	private String name;
	private String phone;
	
	public Contact(int id,String name,String phone) {
		this.id=id;
		this.name=name;
		this.phone=phone;
	}
	public Contact(String name,String phone) {
		this.name=name;
		this.phone=phone;
	}
	 @Override
	  public String toString() {
	       return "ID: " + id + ", Name: " + name + ", Phone: " + phone;
	}
}

public class contact_sql {
	 private static final Scanner scanner=new Scanner(System.in);
	 private static final String URL = "jdbc:mysql://localhost:3306/new_project?characterEncoding=UTF-8";
	 private static final String USERNAME = "root";
	 private static final String PASSWORD = "717273";
	 public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		try(Connection connection=DriverManager.getConnection(URL,USERNAME,PASSWORD)){
			
			int choice;
			do {
				displayMenu();
				choice=getUserChoice();
				handleUserChoice(choice, connection);
			}while(choice!=5);
		}
		catch(SQLException e) {
			System.out.println("Database connection failed: " + e.getMessage());
		}
	}
	 private static void displayMenu() {
	        System.out.println("\nContact Management System");
	        System.out.println("1. Add Contact");
	        System.out.println("2. View All Contacts");
	        System.out.println("3. Update Contact");
	        System.out.println("4. Delete Contact");
	        System.out.println("5. Exit");
	        System.out.print("Choose an option: ");
	 }
	 private static int getUserChoice() {
		 try {
			 return scanner.nextInt();
		 }
		 catch (Exception e){
			 System.out.println("Invalid input.");
			 scanner.nextLine();
			 return -1;
		 }
		 finally {
			 scanner.nextLine();
		 }
	 }
	 private static void handleUserChoice(int choice,Connection connection) throws SQLException{
		 switch(choice) {
		 	case 1 -> addContact(connection);
		 	case 2 -> viewContacts(connection);
		 	case 3 -> updateContact(connection);
		 	case 4 -> deleteContact(connection);
		 	case 5 -> System.out.println("Exiting. Thank you!");
		 	default -> System.out.println("Invalid choice. Please try again.");
		 	
		 }
	 }
	 private static void addContact(Connection connection) throws SQLException {
		 System.out.println("Enter Contact name");
		 String name = scanner.nextLine();
		 System.out.println("Enter Contact Phone:");
	     String phone = scanner.nextLine();
	     String qry = "INSERT INTO Contact (name, phone) VALUES (?, ?)";
	     try(PreparedStatement statement=connection.prepareStatement(qry)){
	    	 statement.setString(1, name);
	         statement.setString(2, phone);
	         statement.executeUpdate();
	         System.out.println("Contact added successfully.");
	     }
		 
	 }
	 private static void viewContacts(Connection connection) throws SQLException {
		 String query = "SELECT * FROM Contact";
		 try(Statement statement=connection.createStatement();ResultSet resultSet=statement.executeQuery(query)){
			 System.out.println("\nContacts List:");
			  while (resultSet.next()) {
				  int id = resultSet.getInt("ID");
	               String name = resultSet.getString("NAME");
	               String phone = resultSet.getString("PHONE");
	               System.out.println(new Contact(id, name, phone));
			  }
			  
		 }
	 

	 }
	 private static void updateContact(Connection connection) throws SQLException {
		 viewContacts(connection);
		 System.out.print("Enter the Contact ID to update: ");
	     int id = scanner.nextInt();
	     scanner.nextLine();
	     System.out.println("Enter new name:");
	     String name = scanner.nextLine();
	     System.out.println("Enter new phone:");
	     String phone = scanner.nextLine();
	     
	     String query="UPDATE Contact SET NAME=?,PHONE=? WHERE ID=?";
	     try(PreparedStatement statement=connection.prepareStatement(query)){
	    	 statement.setString(1, name);
	         statement.setString(2, phone);
	         statement.setInt(3, id);
	         int row=statement.executeUpdate();
	         if (row > 0) {
	                System.out.println("Contact updated successfully.");
	           } else {
	                System.out.println("Contact not found.");
	           }
	     }
	 }
	 private static void deleteContact(Connection connection) throws SQLException {
		 viewContacts(connection);
		 System.out.print("Enter the contact ID to update: ");
	     int id = scanner.nextInt();
	     String query="DELETE FROM Contact WHERE ID=?";
	     try(PreparedStatement statement=connection.prepareStatement(query)){
	    	 statement.setInt(1, id);
	    	 int row = statement.executeUpdate();
	    	 if(row>0) {
	    		 System.out.println("Contact deleted successfully.");
	    	 }
	    	 else {
	    		 System.out.println("Contact not found.");
	    	 }
	     }
	     
	 }

}
