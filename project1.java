import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.in;

class details{
    String name;
    String phone;
    details(String x, String y){
        this.name=x;
        this.phone=y;
    }

}

public class project1 {
    static ArrayList<details> contacts = new ArrayList<>();
    static Scanner in=new Scanner(System.in);
    public static void main(String[] args) {
        int choice=0;
        while(choice!=6){
            System.out.println("The contact management");
            System.out.println("1.Add contact");
            System.out.println("2.Update contact");
            System.out.println("3.delete contact ");
            System.out.println("4.view all contact");
            System.out.println("5. Find contact");
            System.out.println("Choose the Option");
            choice=in.nextInt();
            in.nextLine();
            switch (choice){
                case 1:
                    addcontact();
                    break;
                case 2:
                    updatecontact();
                    break;
                case 3:
                    delatecontact();
                    break;
                case 4:
                    viewcontact();
                    break;
                case 5:
                    findcontact();
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    static void addcontact(){
        System.out.println("Enter the name");
        String name=in.nextLine();
        System.out.println("Enter the phone number");
        String phone=in.nextLine();
        contacts.add(new details(name,phone));
        System.out.println("Contacts added Successfully");
        System.out.println("--------------------------------------------------------------");

    }
    static void updatecontact(){
        viewcontact();
        System.out.println("Enter the index number to update the contact");
        int index=in.nextInt()-1;
        in.nextLine();
        if(index>=0 && index<contacts.size()){
            System.out.println("Enter the name");
            String name=in.nextLine();
            System.out.println("Enter the phone number");
            String phone=in.nextLine();
            contacts.set(index,new details(name,phone));
            System.out.println("Contacts updated Successfully");
            viewcontact();
            System.out.println("--------------------------------------------------------------");
        }
    }
    static void viewcontact(){
        if(contacts.isEmpty()){
            System.out.println("contact is empty");
        }
        for(int i=0;i<contacts.size();i++){
            details detail=contacts.get(i);
            System.out.println((i + 1)+ ". Name: " + detail.name + ", Phone: " + detail.phone);
            System.out.println("--------------------------------------------------------------");
        }
    }
    static void delatecontact(){
        viewcontact();
        System.out.println("Enter the index number to update the contact");
        int index=in.nextInt()-1;
        in.nextLine();
        if(index>=0 && index<contacts.size()){
                contacts.remove(index);
            System.out.println("Contacts deleted Successfully");
        }
        else{
            System.out.println("Enter valid index number");
        }
    }

    static void findcontact(){
        System.out.println("Enter the contact name or phone number to find");
        String findconc=in.nextLine();

        boolean found=false;
        for(details contact:contacts){
            if(contact.name.contains(findconc)||(contact.phone.contains(findconc))){
                System.out.println("Found name : " + contact.name+" phone :"+contact.phone);
                found = true;
            }
            else{
                System.out.println("No contact found");
            }
        }
    }
}

