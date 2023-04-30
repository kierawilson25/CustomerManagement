import java.time.LocalDate;
import java.util.ArrayList;

public class Transaction {
    static ArrayList<Transaction> transactionList = new ArrayList<Transaction>();
    
    private Customer customer;
    private String spraySolution;
    private int payment;
    private String[] dateArray; 
    private String dateString;

    public Transaction(Customer inCustomer, String inSpray, int inPayment, String inDate){
        customer = inCustomer;
        spraySolution = inSpray;
        payment = inPayment;
        dateString = inDate;
        dateArray = inDate.split("-");
        transactionList.add(this);
    }

    public Transaction(Customer inCustomer, int inPayment, String inDate){
        customer = inCustomer;
        payment = inPayment;
        dateString = inDate;
        dateArray = inDate.split("-");
        transactionList.add(this);
    }

    public static ArrayList<Transaction> getTransactionList(){
        return transactionList;
    }

    public void printTransactionsList(){
        transactionList.forEach(transaction -> System.out.println("Customer: " + transaction.getCustomerName() + "\n Spray Solution: " + transaction.getSpraySolution() + "\n Payment Recieved: " + Integer.toString(transaction.getPayment()) + "\n Transaction Date: " + transaction.getDateString() ));
    }

    public void setTodaysDate(){
        dateString = LocalDate.now().toString();
        dateArray = dateString.split("-"); //dates will come in like this 2021-12-17, YYYY-MM-DD
    }

    public void setCustomer(Customer newCustomer){
        customer = newCustomer;
    }

    public Customer getCustomer(){
        return customer;
    }
    
    public String getCustomerName(){
        return customer.getName();
    }

    public void setSpraySolution(String newSpraySolution){
        spraySolution = newSpraySolution;
    }
    
    public String getSpraySolution(){
        return spraySolution;
    }

    public void setPayment(int newPayment){
        payment = newPayment;
    }

    public int getPayment(){
        return payment;
    }

    public void setDate(String newDate){
        dateString = newDate;
        dateArray = newDate.split("-");
    }

    public String getDateString(){
        return dateString;
    }

    public String[] getDateArray(){
        return dateArray;
    }

    public String toString(){
        String result = "";
        result = "Name: " + getCustomerName() + "\n" + "Spray Solution: " + spraySolution + "\n" +  "Payment: " + payment + " \n" + "Date: " + dateString + "\n";
        return result;
    }

}
