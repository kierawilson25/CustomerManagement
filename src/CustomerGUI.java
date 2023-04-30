import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CustomerGUI extends JFrame implements ActionListener {
    private JLabel nameLabel, numberLabel, notesLabel, transReportLabel, transNameLable, newCustReportLabel, monthLabel;
    private JTextField nameField, numberField, notesField, transReportField, monthField;
    private JButton addButton, clearButton, displayButton, runReportButton, runReport2Button;
    private JTextArea displayArea;
    private ArrayList<Customer> customers;
    

    public CustomerGUI() {
        super("Customer Management System");

        // initialize the GUI components
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
        //transaction report GUI
        transReportLabel = new JLabel("Transaction report ");
        transNameLable = new JLabel("Customer Name: ");
        transReportField = new JTextField(20);
        runReportButton = new JButton("Run Report");
        JScrollPane scrollPane = new JScrollPane(displayArea);
        //New Customer by Month GUI
        newCustReportLabel = new JLabel("New Customers by Month Report ");
        monthLabel = new JLabel("Month: ");
        monthField = new JTextField(20);
        runReport2Button = new JButton("Run Report");

        // add action listeners to the buttons
        addButton.addActionListener(this);
        clearButton.addActionListener(this);
        displayButton.addActionListener(this);
        runReportButton.addActionListener(this);
        runReport2Button.addActionListener(this);

        // add the components to the frame
        JPanel panel = new JPanel(new GridLayout(8, 2, 10, 10));
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(numberLabel);
        panel.add(numberField);
        panel.add(notesLabel);
        panel.add(notesField);
        panel.add(new JLabel(""));
        panel.add(addButton);
        panel.add(new JLabel(""));
        panel.add(clearButton);
        panel.add(new JLabel(""));
        panel.add(displayButton);
        //transactions report
        JPanel panel1 = new JPanel(new GridLayout(7, 1, 10, 10));
        panel1.add(transReportLabel);
        //panel1.add(new JLabel(""));
        panel1.add(transNameLable);
        panel1.add(transReportField);
        //panel1.add(new JLabel(""));
        panel1.add(runReportButton);
        //New Customer report
        JPanel panel2 = new JPanel(new GridLayout(7, 1, 10, 10));
        panel2.add(newCustReportLabel);
        // panel2.add(new JLabel(""));
        panel2.add(monthLabel);
        // panel2.add(new JLabel(""));
        panel2.add(monthField);
        // panel2.add(new JLabel(""));
        panel2.add(runReport2Button);
        // panel1.add(newCustReportLabel);
        // panel1.add(new JLabel(""));
        // panel1.add(monthLabel);
        // panel1.add(new JLabel(""));
        // panel1.add(monthField);
        // panel1.add(new JLabel(""));
        // panel1.add(runReport2Button);


        // JPanel displayPanel = new JPanel();
        // displayPanel.add(displayButton);


        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(panel, BorderLayout.NORTH);
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
        if (e.getSource() == addButton) {
            // add a new customer to the list
            String name = nameField.getText();
            String number = numberField.getText();
            String notes = notesField.getText();
            

            Customer customer = new Customer(name, number, notes);

            // display a confirmation message
            JOptionPane.showMessageDialog(this, "Customer added successfully!");

            // clear the input fields
            nameField.setText("");
            numberField.setText("");
            //emailField.setText("");
            notesField.setText("");
        } else if (e.getSource() == clearButton) {
            // clear the input fields
            nameField.setText("");
            numberField.setText("");
            notesField.setText("");
        } else if (e.getSource() == displayButton) {
            // display all the customers in the list
            displayArea.setText("");
            for (Customer myCustomer : customers) {
                displayArea.append(myCustomer.toString() + "\n");
            }
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

        }
    }

    public static void main(String[] args) {
        CustomerGUI gui = new CustomerGUI();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.pack();
        gui.setVisible(true);
    }
}

