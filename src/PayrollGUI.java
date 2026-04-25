import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class PayrollGUI extends JFrame {

    private JTable table;
    private DefaultTableModel model;
    private ArrayList<Payable> records;

    public PayrollGUI() {
        setTitle("Payroll Management System");
        setSize(850, 420);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        try {
            records = PayrollDataManager.loadData();
        } catch (Exception e) {
            records = new ArrayList<>();
        }

        model = new DefaultTableModel();
        model.addColumn("Type");
        model.addColumn("Name / Description");
        model.addColumn("Payment Amount");

        table = new JTable(model);
        table.setRowHeight(25);
        table.setFillsViewportHeight(true);
        table.getTableHeader().setReorderingAllowed(false);

        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton addBtn = new JButton("Add Record");
        JButton removeBtn = new JButton("Remove Selected");
        JButton payStubBtn = new JButton("Generate Pay Stubs");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addBtn);
        buttonPanel.add(removeBtn);
        buttonPanel.add(payStubBtn);

        add(buttonPanel, BorderLayout.SOUTH);

        loadTableData();

        addBtn.addActionListener(e -> addRecord());

        removeBtn.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();

            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a record to remove.");
                return;
            }

            records.remove(selectedRow);
            loadTableData();
            saveRecords();

            JOptionPane.showMessageDialog(this, "Record removed successfully.");
        });

        payStubBtn.addActionListener(e -> {
            try {
                for (Payable p : records) {
                    p.writeToFile();
                }

                JOptionPane.showMessageDialog(this, "Pay stubs generated successfully.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error generating pay stubs.");
            }
        });

        setVisible(true);
    }

    private void addRecord() {
        String[] options = {
                "Salaried Employee",
                "Hourly Employee",
                "Commission Employee",
                "Base Plus Commission Employee",
                "Invoice"
        };

        String choice = (String) JOptionPane.showInputDialog(
                this,
                "Select record type:",
                "Add Record",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        if (choice == null) {
            return;
        }

        try {
            if (choice.equals("Salaried Employee")) {
                String firstName = JOptionPane.showInputDialog(this, "Enter first name:");
                String lastName = JOptionPane.showInputDialog(this, "Enter last name:");
                String ssn = JOptionPane.showInputDialog(this, "Enter ID / SSN:");
                double salary = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter weekly salary:"));

                records.add(new SalariedEmployee(firstName, lastName, ssn, salary));
            }

            else if (choice.equals("Hourly Employee")) {
                String firstName = JOptionPane.showInputDialog(this, "Enter first name:");
                String lastName = JOptionPane.showInputDialog(this, "Enter last name:");
                String ssn = JOptionPane.showInputDialog(this, "Enter ID / SSN:");
                double hours = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter hours worked:"));
                double rate = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter hourly rate:"));

                records.add(new HourlyEmployee(firstName, lastName, ssn, hours, rate));
            }

            else if (choice.equals("Commission Employee")) {
                String firstName = JOptionPane.showInputDialog(this, "Enter first name:");
                String lastName = JOptionPane.showInputDialog(this, "Enter last name:");
                String ssn = JOptionPane.showInputDialog(this, "Enter ID / SSN:");
                double sales = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter gross sales:"));
                double rate = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter commission rate (example 0.10):"));

                records.add(new CommissionEmployee(firstName, lastName, ssn, sales, rate));
            }

            else if (choice.equals("Base Plus Commission Employee")) {
                String firstName = JOptionPane.showInputDialog(this, "Enter first name:");
                String lastName = JOptionPane.showInputDialog(this, "Enter last name:");
                String ssn = JOptionPane.showInputDialog(this, "Enter ID / SSN:");
                double sales = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter gross sales:"));
                double rate = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter commission rate (example 0.10):"));
                double baseSalary = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter base salary:"));

                records.add(new BasePlusCommissionEmployee(firstName, lastName, ssn, sales, rate, baseSalary));
            }

            else if (choice.equals("Invoice")) {
                String partNumber = JOptionPane.showInputDialog(this, "Enter invoice number:");
                String description = JOptionPane.showInputDialog(this, "Enter invoice description:");
                int quantity = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter quantity:"));
                double price = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter price per item:"));

                records.add(new Invoice(partNumber, description, quantity, price));
            }

            loadTableData();
            saveRecords();

            JOptionPane.showMessageDialog(this, "Record added successfully.");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers.");
        } catch (InvalidSalaryException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error adding record.");
        }
    }

    private void loadTableData() {
        model.setRowCount(0);

        for (Payable p : records) {
            String type = p.getClass().getSimpleName();
            String name = "";

            if (p instanceof Employee) {
                Employee e = (Employee) p;
                name = e.getFirstName() + " " + e.getLastName();
            } else if (p instanceof Invoice) {
                Invoice i = (Invoice) p;
                name = i.getPartDescription();
            }

            String amount = "$" + String.format("%.2f", p.getPaymentAmount());

            model.addRow(new Object[]{type, name, amount});
        }
    }

    private void saveRecords() {
        try {
            PayrollDataManager.saveData(records);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error saving records.");
        }
    }

    public static void main(String[] args) {
        new PayrollGUI();
    }
}