import java.util.ArrayList;

public class NewCustomerByMonth extends Report{
    String reportType = "New Customer Report";
    int newCustomers;
    String newCustomersString = "";


    public String getReportType(){
        return this.reportType;
    };

    //quick fix to abstract method
    public String runReport(){
        return "Please Supply a month ";
    }

    public String runReport(String month){
        String reportString;
        ArrayList<Customer> customerList = new ArrayList<Customer>();
        customerList = Customer.getCustomerList();
        //Lambda - from class, for each loop */
        customerList.forEach(customer ->  checkMonth(customer.getRegMonth(), month, customer));
        reportString = "New " + month + " customers: " + newCustomers + "\n";
        reportString = reportString.concat(newCustomersString);
        System.out.println("new Customer String " + newCustomersString);
        return reportString;
    }

    public void checkMonth(String customerMonth, String reportMonth, Customer customer){
        if(customerMonth.toLowerCase().equals(reportMonth.toLowerCase())) 
        { 
            newCustomers++;
            newCustomersString = newCustomersString.concat("\n");
            newCustomersString = newCustomersString.concat(customer.getAllCustomerDetails());
            // }
        }
    }
}
