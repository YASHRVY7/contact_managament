import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

class Contact{
    private String name;
    private String phone;

    public Contact(String name,String phone){
        this.name=name;
        this.phone=phone;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    @Override
    public String toString(){
        return "Name:"+name+",phone:"+phone;
    }
}

public class ContactManager {
    private static final List<Contact> contact=new ArrayList<>();
    private static final Scanner scanner=new Scanner(System.in);
    public static void main(String[] args) {
        int choice=0;
        while(choice!=6){
            displayMenu();
            choice=getUserChoice();
            handleUserChoice(choice);
        }
    }
    private static void displayMenu(){
        System.out.println("\nContact Management System");
        System.out.println("1. Add Contact");
        System.out.println("2. View All Contacts");
        System.out.println("3. Update Contact");
        System.out.println("4. Delete Contact");
        System.out.println("5. Find Contact");
        System.out.println("6. Exit");
        System.out.print("Choose an option: ");

    }
    private static int getUserChoice() {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input.");
            scanner.nextLine(); // Clear invalid input
            return -1;
        } finally {
            scanner.nextLine(); // Clear newline character
        }
    }
    private static void handleUserChoice(int choice){
        switch (choice){
            case 1 -> addContact();
            case 2 -> viewContact();
            case 3 -> updateContact();
            case 4 -> deleteContact();
            case 5 -> findContact();
            default -> {
                if (choice!=6){
                    System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }

    private static void addContact(){
        System.out.println("Enter Contact name:");
        String name=scanner.nextLine();
        System.out.println("Enter Contact phone number:");
        String phone=scanner.nextLine();
        contact.add(new Contact(name,phone));
        System.out.println("Contact added successfully.");
    }
    private static void viewContact(){
        if(contact.isEmpty()){
            System.out.println("No contacts found.");
        }
        else {
            System.out.println("\nContacts List:");
            for(int i=0;i<contact.size();i++){
                System.out.println((i+1)+"."+contact.get(i));
            }
        }

    }
    private static void updateContact() {
        viewContact();
        System.out.print("Enter the contact number to update: ");
        if (scanner.hasNextInt()) {
            int index = scanner.nextInt() - 1;
            scanner.nextLine();
            if (index >= 0 && index < contact.size()) {
                System.out.print("Enter new name: ");
                String newName = scanner.nextLine();

                System.out.print("Enter new phone number: ");
                String newPhone = scanner.nextLine();

                Contact contactup = contact.get(index);
                contactup.setName(newName);
                contactup.setPhone(newPhone);

                System.out.println("Contact updated successfully.");
            } else {
                System.out.println("Invalid contact number.");
            }
        } else {
            System.out.println("Invalid input. Please enter a valid contact number.");
            scanner.nextLine();
        }
    }
    private static void deleteContact(){
        viewContact();
        System.out.print("Enter the contact number to update: ");
        int index=scanner.nextInt()-1;
        scanner.nextLine();
        if (index >= 0 && index < contact.size()){
            contact.remove(index);
            System.out.println("Contact deleted successfully.");
        }
        else {
            System.out.println("Invalid contact number.");
        }


    }
    private static void findContact(){
        System.out.print("Enter the name or phone number to search: ");
        String searchTerm = scanner.nextLine();
        boolean found=false;
        for(Contact contactf : contact){
            if(contactf.getName().contains(searchTerm)||contactf.getPhone().contains(searchTerm)){
                System.out.println("Found: " + contactf);
                found = true;
            }
        }
        if(!found){
            System.out.println("No contact found with that name or phone number.");
        }

    }
}
