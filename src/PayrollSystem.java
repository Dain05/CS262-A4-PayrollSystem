import java.util.ArrayList;

public class PayrollSystem {

    public static void main(String[] args) {

        ArrayList<Payable> payables;

        try {
            payables = PayrollDataManager.loadData();
            System.out.println("Data loaded successfully.");
        } catch (Exception e) {
            payables = new ArrayList<>();
            System.out.println("No previous data found. Starting fresh.");
        }

        try {
            if (payables.isEmpty()) {

                payables.add(new SalariedEmployee("Dain", "Thorpe", "111", 50000));
                payables.add(new HourlyEmployee("Joan", "Johnson-Brown", "222", 150, 300));
                payables.add(new CommissionEmployee("Shanique", "Wisdom", "333", 100000, 0.30));
                payables.add(new BasePlusCommissionEmployee("Pasha", "Pinnock", "444", 100000, 0.25, 10000));
                payables.add(new Invoice("P001", "Services - Dante Graham", 1, 75000));
            }

            for (Payable p : payables) {
                System.out.println("Payment: $" + String.format("%.2f", p.getPaymentAmount()));
                p.writeToFile();
            }

            PayrollDataManager.saveData(payables);
            System.out.println("Data saved successfully.");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}