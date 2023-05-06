import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JFrame;


public class Customer implements Serializable {
    static ArrayList<Customer> customerList = new ArrayList<Customer>();
    static boolean updated;
    //encapsulation
    private String name;
    private String  number;
    private String notes;
    private String regMonth;
    private String regYear;
    private int sprayTans;
    
    //private Promo promo;
    // in class over loading a constructor 
    public Customer(String inName){
        name = inName;
    }

    public Customer(String inputName, String inputNumber, String inputNotes){
        this.name = inputName;
        this.number = inputNumber;
        this.notes = inputNotes;
        this.addNewCust();
        customerList.add(this);
        saveCustomers();
        getCustomerList();
    }

    public Customer(String inputName, String inputNumber, String inputNotes, int inputSprays, String inputMonth, String inputYear){
        this.name = inputName;
        this.number = inputNumber;
        this.notes = inputNotes;
        this.sprayTans = inputSprays;
        this.regMonth = inputMonth;
        this.regYear = inputYear;
        customerList.add(this);
        updated = true;
        saveCustomers();
        getCustomerList();
    }

    public String toString(){
        getCustomers();
        String finalString = "";
        finalString = "Name: "+ name + "\n" + "Number: "+ number + "\n" + "Notes: "+ notes + "\n" + "Registration Date: " + regMonth +  " " + regYear+ "\n" + "Spray Tans: "+ sprayTans + "\n";
        return finalString;
    }

    static public ArrayList<Customer> getCustomerList(){
        getCustomers();
        return customerList;
    }

    public static Customer getCustomer(String name){
        Customer placeholdCustomer = new Customer(name);
        for (Customer customer : customerList) {
            if(customer.name.equals(name))
            {
                return customer;
            }
        }
        return placeholdCustomer;
    }

    static void addCustomer(Customer newCustomer){
        customerList.add(newCustomer);
        saveCustomers();
        getCustomerList();
    }

    public String getName(){
        return name;
    }

    public void setName(String newName){
        this.name = newName;
    }

    public String getNumber(){
        return number;
    }

    public void setNumber(String newNumber){
        this.number = newNumber;
    }

    public String getNotes(){
        return notes;
    }

    public void setNotes(String newNotes){
        this.notes = newNotes;
    }

    public void addNote(String newNotes){
        this.notes = this.notes + "\n" + newNotes;
    }

    public String getRegMonth(){
        return regMonth;
    }

    public void setRegMonth(String newRegMonth){
        this.regMonth = newRegMonth;
    }

    public String getRegYear(){
        return regYear;
    }

    public void setRegYear(String newRegYear){
        this.regMonth = newRegYear;
    }

    public int getSprayTans(){
        return sprayTans;
    }

    public void setSprayTan(int newSprayTans){
        this.sprayTans = newSprayTans;
    }

    public void addSprayTan(){
        this.sprayTans++;
    }

    public void addNewCust(){
        //something I looked at in the homework 
        LocalDate currentDate = LocalDate.now();
        regMonth = currentDate.getMonth().toString();
        //*** class thing I think */
        regYear = Integer.toString(currentDate.getYear());
        setSprayTan(1);
    }

    public String getAllCustomerDetails(){
        String allDetails = "";
        allDetails = "Name: " + name + "\n" + "Number: " + number + "\n" + "Notes: " + notes + "\n" + "Registration Date: " + regMonth + ", " + regYear + "\n" + "Spray Tans: "  + Integer.toString(sprayTans) + "\n";
        return allDetails;
    }

    static void saveCustomers(){

        try (FileOutputStream fileOutputStream = new FileOutputStream("customerData");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);) {

        objectOutputStream.writeObject(customerList);

        } catch (FileNotFoundException e) {
        System.out.println("File not found : " + e);
        throw new RuntimeException(e);
        } catch (IOException ioe) {
        System.out.println("Error while writing data : "+ ioe);
        ioe.printStackTrace();
        }
    }

    static void getCustomers(){
        try (FileInputStream fileInputStream = new FileInputStream("customerData");
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);) {
            customerList = (ArrayList) objectInputStream.readObject();
            for (Object customer : customerList){
                    customer = (Customer) customer;
            }
            } catch (IOException ioe) {
            ioe.printStackTrace();
            } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
            }
    }



    public static void main(String[] args) {
        
        //Customer Testing
        Customer myCustomer = new Customer("Kim K", "952-555-555", "Looks so amazing");
        Customer myCustomer1 = new Customer("Sarah Roche", "952-555-555", "Looks so amazing", 4, "January", "2023");
        Customer myCustomer2 = new Customer("Winston", "952-555-555", "Kinda orange", 3, "March", "2023");
        Customer myCustomer3 = new Customer("Julia P ", "952-555-555", "Great colo", 4, "April", "2023");
        Customer myCustomer4 = new Customer("Taylor Swift", "952-555-555", "Love", 5, "March", "2023");
        Customer myCustomer5 = new Customer("Kiera", "952-555-555", "Looks so amazing");

        //Transaction testing
        Transaction sarahRapidSpray = new Transaction(myCustomer1, "Rapid", 40, "2023-04-22");
        Transaction sarahRapidSpray2 = new Transaction(myCustomer1, "Rapid", 40, "2023-07-22");
        Transaction kimRapidSpray = new Transaction(myCustomer, "Rapid", 40, "2023-14-22");

        //run the program
        CustomerGUI.run();

        
    }
}