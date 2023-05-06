import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Transaction implements Serializable {
    static ArrayList<Transaction> transactionList = new ArrayList<Transaction>();
    
    private Customer customer;
    private String spraySolution;
    private int payment;
    private String[] dateArray; 
    private String dateString;

    //Expects a date in this format 2023-04-22 
    public Transaction(Customer inCustomer, String inSpray, int inPayment, String inDate){
        customer = inCustomer;
        spraySolution = inSpray;
        payment = inPayment;
        dateString = inDate;
        dateArray = inDate.split("-");
        transactionList.add(this);
        saveTransactions();
        getTransactions();
    }

    public Transaction(Customer inCustomer, int inPayment, String inDate){
        customer = inCustomer;
        payment = inPayment;
        dateString = inDate;
        dateArray = inDate.split("-");
        transactionList.add(this);
        saveTransactions();
        getTransactions();
    }

    public void addTransaction(Customer inCustomer, String inSpray, int inPayment, String inDate){
        Transaction newTransaction = new Transaction(inCustomer, inSpray, inPayment, inDate);

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

    static void saveTransactions(){

        try (FileOutputStream fileOutputStream = new FileOutputStream("transactionData");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);) {

        objectOutputStream.writeObject(transactionList);

        } catch (FileNotFoundException e) {
        System.out.println("File not found : " + e);
        throw new RuntimeException(e);
        } catch (IOException ioe) {
        System.out.println("Error while writing data : "+ ioe);
        ioe.printStackTrace();
        }
    }

    static void getTransactions(){
        try (FileInputStream fileInputStream = new FileInputStream("transactionData");
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);) {
            transactionList = (ArrayList) objectInputStream.readObject();
            for (Object transaction : transactionList){
                    transaction = (Transaction) transaction;
            }
            } catch (IOException ioe) {
            ioe.printStackTrace();
            } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
            }
    }

}
