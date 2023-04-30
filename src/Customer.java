import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JFrame;


public class Customer {
    static ArrayList<Customer> customerList = new ArrayList<Customer>();
    //encapsulation
    private String name;
    private String  number;
    private String notes;
    private String regMonth;
    private String regYear;
    private int sprayTans;
    //private Promo promo;
    // in class over loading a constructor 
    public Customer(String inputName, String inputNumber, String inputNotes){
        this.name = inputName;
        this.number = inputNumber;
        this.notes = inputNotes;
        this.addNewCust();
        customerList.add(this);

    }

    public Customer(String inputName, String inputNumber, String inputNotes, int inputSprays, String inputMonth, String inputYear){
        this.name = inputName;
        this.number = inputNumber;
        this.notes = inputNotes;
        this.sprayTans = inputSprays;
        this.regMonth = inputMonth;
        this.regYear = inputYear;
        customerList.add(this);
    }
    public String toString(){
        String finalString = "";
        finalString = "Name: "+ name + "\n" + "Number: "+ number + "\n" + "Notes: "+ notes + "\n" + "Registration Date: " + regMonth +  " " + regYear+ "\n" + "Spray Tans: "+ sprayTans + "\n";
        return finalString;
    }

    static public ArrayList<Customer> getCustomerList(){
        return customerList;
    }

    static void addCustomer(Customer newCustomer){
        customerList.add(newCustomer);
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

    public static void main(String[] args) {
        
        //Customer Testing

        Customer myCustomer = new Customer("Kim K", "952-555-555", "Looks so amazing");
        myCustomer.addNewCust();
        System.out.print(myCustomer.getAllCustomerDetails());

        Customer myCustomer1 = new Customer("Sarah Roche", "952-555-555", "Looks so amazing", 4, "January", "2023");


        Customer myCustomer2 = new Customer("Winston", "952-555-555", "Kinda orange", 3, "March", "2023");


        Customer myCustomer3 = new Customer("Julia P ", "952-555-555", "Great colo", 4, "April", "2023");
   

        Customer myCustomer4 = new Customer("Taylor Swift", "952-555-555", "Love", 5, "March", "2023");


        Customer myCustomer5 = new Customer("Kiera", "952-555-555", "Looks so amazing");
        myCustomer5.addNewCust();

        NewCustomerByMonth newMarchCustomersReport = new NewCustomerByMonth();
        System.out.println(newMarchCustomersReport.runReport("March"));
        NewCustomerByMonth newAprilCustomersReport = new NewCustomerByMonth();
        System.out.println(newAprilCustomersReport.runReport("April"));

        //Transaction testing
        Transaction sarahRapidSpray = new Transaction(myCustomer1, "Rapid", 40, "2023-04-22");
        Transaction sarahRapidSpray2 = new Transaction(myCustomer1, "Rapid", 40, "2023-07-22");
        Transaction kimRapidSpray = new Transaction(myCustomer, "Rapid", 40, "2023-14-22");


        //ArrayList<Transaction> transactions = new ArrayList<Transaction>();
        // = sarahRapidSpray.getTransactionList();
        //Lambdas from class
        // transactions.forEach(transaction -> System.out.println("Customer Name " + transaction.getCustomerName()+ ""))


        TransactionsByCustomerReport transactionsBySarahReport = new TransactionsByCustomerReport();
        System.out.println("Transactions \n");
        System.out.println(transactionsBySarahReport.runReport("Sarah Roche"));

        CustomerGUI gui = new CustomerGUI();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.pack();
        gui.setVisible(true);


        //sarahRapidSpray.printTransactionsList();
        System.out.println("");
    }
}