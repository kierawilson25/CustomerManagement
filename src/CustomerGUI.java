import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CustomerGUI extends javax.swing.JFrame implements ActionListener{
    private JLabel nameLabel, numberLabel, notesLabel, transReportLabel, transNameLable, newCustReportLabel, monthLabel, nameLabel1, serviceLabel, paymentLabel, dateLabel;
    private JTextField nameField, numberField, notesField, transReportField, monthField, nameField1, serviceField, paymentField, datesField;
    private JButton addButton, clearButton, displayButton, runReportButton, runReport2Button, addTransaction, clearButton1, displayTransactions;
    private JTextArea displayArea;
    private Border blackline;
    private ArrayList<Customer> customers;
    private ArrayList<Transaction> transactions;

    public static void run(){
        Customer.getCustomers();
        Transaction.getTransactions();
        CustomerGUI gui = new CustomerGUI();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.pack();
        gui.setVisible(true);
    }
    

    public CustomerGUI() {
        super("Customer Management System");

        //add customer 
        nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);
        numberLabel = new JLabel("Number:");
        numberField = new JTextField(20);
        notesLabel = new JLabel("Notes:");
        notesField = new JTextField(20);
        addButton = new JButton("Add Customer");
        clearButton = new JButton("Clear Fields");
        displayButton = new JButton("Display Customers");
        displayArea = new JTextArea(20, 40);

        //add transaction
        nameLabel1 = new JLabel("Customer Name:");
        nameField1 = new JTextField(20);
        serviceLabel = new JLabel("Service:");
        serviceField = new JTextField(20);
        paymentLabel = new JLabel("Payment:");
        paymentField = new JTextField(20);
        dateLabel = new JLabel("Date: (Format YYYY-MM-DD)");
        datesField = new JTextField(20);
        addTransaction = new JButton("Add Transaction");
        clearButton1 = new JButton("Clear Fields");
        displayTransactions = new JButton("Display Transactions");

        //transaction report GUI
        transReportLabel = new JLabel("Transaction report ");
        transNameLable = new JLabel("Customer Name: ");
        transReportField = new JTextField(20);
        runReportButton = new JButton("Run Report");
        
        //New Customer by Month GUI
        newCustReportLabel = new JLabel("Customer Report");
        monthLabel = new JLabel("Month: ");
        monthField = new JTextField(20);
        runReport2Button = new JButton("Run Report");

        JScrollPane scrollPane = new JScrollPane(displayArea);

        // add action listeners to the buttons
        addButton.addActionListener(this);
        clearButton.addActionListener(this);
        displayButton.addActionListener(this);
        runReportButton.addActionListener(this);
        runReport2Button.addActionListener(this);
        addTransaction.addActionListener(this);
        clearButton1.addActionListener(this);
        displayTransactions.addActionListener(this);


        blackline = BorderFactory.createLineBorder(Color.black);


        // add the components to the frame
        JPanel panel = new JPanel(new GridLayout(12, 2));
        JLabel customerHeading = new JLabel("Customers");
        Font font = new Font("Lucida Grande", Font.BOLD,12);
        customerHeading.setFont(font);
        panel.add(customerHeading);
        panel.add(new JLabel(""));
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(numberLabel);
        panel.add(numberField);
        panel.add(notesLabel);
        panel.add(notesField);
        //panel.add(new JLabel(""));
        panel.add(addButton);
        
        //panel.add(new JLabel(""));
       //panel.add(clearButton);
        //panel.add(new JLabel(""));
        panel.add(displayButton);
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        JLabel transactionsHeading = new JLabel("Transactions");
        transactionsHeading.setFont(font);
        panel.add(transactionsHeading);
        panel.add(new JLabel(""));
        //transactions
        panel.add(nameLabel1);
        panel.add(nameField1);
        panel.add(serviceLabel);
        panel.add(serviceField);
        panel.add(paymentLabel);
        panel.add(paymentField);
        panel.add(dateLabel);
        panel.add(datesField);
        panel.add(addTransaction);
        panel.add(displayTransactions);
        panel.setBorder(blackline);


        //transactions report
        JPanel panel1 = new JPanel(new GridLayout(7, 1, 10, 10));
        panel1.add(transReportLabel);
        //panel1.add(new JLabel(""));
        panel1.add(transNameLable);
        panel1.add(transReportField);
        //panel1.add(new JLabel(""));
        panel1.add(runReportButton);
        panel1.setBorder(blackline);
        //New Customer report
        JPanel panel2 = new JPanel(new GridLayout(7, 1, 10, 10));
        panel2.add(newCustReportLabel);
        // panel2.add(new JLabel(""));
        panel2.add(monthLabel);
        // panel2.add(new JLabel(""));
        panel2.add(monthField);
        // panel2.add(new JLabel(""));
        panel2.add(runReport2Button);
        panel2.setBorder(blackline);



        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(panel, BorderLayout.CENTER);
        mainPanel.add(panel1, BorderLayout.WEST);
        mainPanel.add(panel2, BorderLayout.EAST);
        mainPanel.add(scrollPane, BorderLayout.SOUTH);
        //mainPanel.add(displayPanel, BorderLayout.CENTER);
        add(mainPanel);

        customers = Customer.getCustomerList();

        // initialize the list of customers
        //customers = new ArrayList<Customer>();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        customers = Customer.getCustomerList();
        transactions = Transaction.getTransactionList();

        if (e.getSource() == addButton) {
            // add a new customer to the list
            String name = nameField.getText();
            String number = numberField.getText();
            String notes = notesField.getText();
            

            Customer customer = new Customer(name, number, notes);
            Customer.saveCustomers();


            // display a confirmation message
            JOptionPane.showMessageDialog(this, "Customer added successfully!");

            // clear the input fields
            nameField.setText("");
            numberField.setText("");
            //emailField.setText("");
            notesField.setText("");
            displayArea.setText("");

        } else if (e.getSource() == displayButton) {
            // display all the customers in the list
            Customer.getCustomers();
            customers = Customer.getCustomerList();
            displayArea.setText("");
            for (Customer myCustomer : customers) {
                displayArea.append(myCustomer.toString() + "\n");
            }
            Customer.saveCustomers();
        } else if (e.getSource() == runReportButton){
            displayArea.setText("");
            String customer = transReportField.getText().toString();
            TransactionsByCustomerReport transactionReport = new TransactionsByCustomerReport();
            String displayText = transactionReport.runReport(customer);
            displayArea.setText(displayText);
        }else if (e.getSource() == runReport2Button){
            String month = monthField.getText().toLowerCase();
            NewCustomerByMonth monthReport = new NewCustomerByMonth();
            displayArea.setText("");
            displayArea.setText(monthReport.runReport(month));

        }else if (e.getSource() == addTransaction){
            displayArea.setText("");
            String custName = nameField1.getText();
            Customer customer = Customer.getCustomer(custName);
            String service = serviceField.getText();
            int payment = Integer.parseInt(paymentField.getText());
            String date = datesField.getText();
            Transaction newTransaction = new Transaction(customer, service, payment, date);
            displayArea.setText("");
            JOptionPane.showMessageDialog(this, "Transaction added successfully!");

            nameField1.setText("");
            serviceField.setText("");
            paymentField.setText("");
            datesField.setText("");
            Transaction.saveTransactions();


        } else if (e.getSource() == displayTransactions){
            Transaction.getTransactions();
            transactions = Transaction.getTransactionList();
            displayArea.setText("");
            for (Transaction myTransaction : transactions) {
                displayArea.append(myTransaction.toString() + "\n");
            }
        }

    }

    public static void main(String[] args) {
        CustomerGUI gui = new CustomerGUI();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.pack();
        gui.setVisible(true);
    }
}

