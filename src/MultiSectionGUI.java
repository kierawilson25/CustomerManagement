import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MultiSectionGUI extends JFrame implements ActionListener {
    // Define components for the first section
    private JLabel nameLabel, ageLabel;
    private JTextField nameField, ageField;
    private JButton addButton, clearButton;
    private JTextArea displayArea;
    private ArrayList<Person> people;

    // Define components for the second section
    private JLabel widthLabel, heightLabel, areaLabel;
    private JTextField widthField, heightField, areaField;
    private JButton calculateButton;

    // Define components for the third section
    private JLabel number1Label, number2Label, resultLabel;
    private JTextField number1Field, number2Field, resultField;
    private JButton addButton2, subtractButton;

    public MultiSectionGUI() {
        super("Multi-Section GUI");

        // Initialize the components for the first section
        nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);
        ageLabel = new JLabel("Age:");
        ageField = new JTextField(20);
        addButton = new JButton("Add Person");
        clearButton = new JButton("Clear Fields");
        displayArea = new JTextArea(10, 20);
        people = new ArrayList<Person>();

        // Add action listeners to the buttons for the first section
        addButton.addActionListener(this);
        clearButton.addActionListener(this);

        // Add the components to the first section
        JPanel section1Panel = new JPanel(new GridLayout(3, 2, 10, 10));
        section1Panel.add(nameLabel);
        section1Panel.add(nameField);
        section1Panel.add(ageLabel);
        section1Panel.add(ageField);
        section1Panel.add(addButton);
        section1Panel.add(clearButton);

        JPanel section1DisplayPanel = new JPanel();
        section1DisplayPanel.add(new JLabel("People:"));
        section1DisplayPanel.add(displayArea);

        // Add the first section to the frame
        JPanel mainPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        mainPanel.add(section1Panel);
        mainPanel.add(section1DisplayPanel);

        // Initialize the components for the second section
        widthLabel = new JLabel("Width:");
        widthField = new JTextField(20);
        heightLabel = new JLabel("Height:");
        heightField = new JTextField(20);
        areaLabel = new JLabel("Area:");
        areaField = new JTextField(20);
        calculateButton = new JButton("Calculate");

        // Add action listeners to the buttons for the second section
        calculateButton.addActionListener(this);

        // Add the components to the second section
        JPanel section2Panel = new JPanel(new GridLayout(3, 2, 10, 10));
        section2Panel.add(widthLabel);
        section2Panel.add(widthField);
        section2Panel.add(heightLabel);
        section2Panel.add(heightField);
        section2Panel.add(areaLabel);
        section2Panel.add(areaField);

        JPanel section2ButtonPanel = new JPanel();
        section2ButtonPanel.add(calculateButton);

        // Add the second section to the frame
        mainPanel.add(section2Panel);
        mainPanel.add(section2ButtonPanel);

        // Initialize the components for the third section
        number1Label = new JLabel("Number 1:");
        number1Field = new JTextField(20);
        number2Label = new JLabel("Number 2:");
        number2Field = new JTextField(20);
        resultLabel = new JLabel("Result is: ");
        resultField = new JTextField(20);
        resultField.setEditable(false);
        addButton2 = new JButton("Add");
        subtractButton = new JButton("Subtract");

        // Add action listeners to the buttons for the third section
        addButton2.addActionListener(this);
        subtractButton.addActionListener(this);

        // Add the components to the third section
        JPanel section3Panel = new JPanel(new GridLayout(3, 2, 10, 10));
        section3Panel.add(number1Label);
        section3Panel.add(number1Field);
        section3Panel.add(number2Label);
        section3Panel.add(number2Field);
        section3Panel.add(resultLabel);
        section3Panel.add(resultField);

        JPanel section3ButtonPanel = new JPanel();
        section3ButtonPanel.add(addButton2);
        section3ButtonPanel.add(subtractButton);

        // Add the third section to the frame
        mainPanel.add(section3Panel);
        mainPanel.add(section3ButtonPanel);

        // Add the main panel to the frame
        add(mainPanel);

        // Set the properties of the frame
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        // Handle the button clicks for the first section
        if (e.getSource() == addButton) {
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            Person person = new Person(name, age);
            people.add(person);
            displayArea.setText("");
            for (Person p : people) {
                displayArea.append(p.toString() + "\n");
            }
            nameField.setText("");
            ageField.setText("");
        } else if (e.getSource() == clearButton) {
            nameField.setText("");
            ageField.setText("");
        }

        // Handle the button clicks for the second section
        if (e.getSource() == calculateButton) {
            double width = Double.parseDouble(widthField.getText());
            double height = Double.parseDouble(heightField.getText());
            double area = width * height;
            areaField.setText(String.valueOf(area));
        }

        // Handle the button clicks for the third section
        if (e.getSource() == addButton2) {
            double number1 = Double.parseDouble(number1Field.getText());
            double number2 = Double.parseDouble(number2Field.getText());
            double result = number1 + number2;
            resultField.setText(String.valueOf(result));
        } else if (e.getSource() == subtractButton) {
            double number1 = Double.parseDouble(number1Field.getText());
            double number2 = Double.parseDouble(number2Field.getText());
            double result = number1 - number2;
            resultField.setText(String.valueOf(result));
        }
    }

    public static void main(String[] args) {
        MultiSectionGUI gui = new MultiSectionGUI();
    }
}

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String toString() {
        return name + " - " + age;
    }
}



