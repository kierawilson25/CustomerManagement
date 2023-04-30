import java.util.ArrayList;

public class TransactionsByCustomerReport extends Report {
    String reportType = "Transactions Report";
    String reportString = "";



    public String runReport(String custName){
        ArrayList<Transaction> transactionList = new ArrayList<Transaction>();
        transactionList = Transaction.getTransactionList();
         for(int i =0 ; i< transactionList.size(); i++){
            if(transactionList.get(i).getCustomerName().equals(custName)){
                //learned to override the toStrng method in class
                reportString = reportString + transactionList.get(i).toString() +  "\n";
            }
         }
         return reportString;

    }

}

