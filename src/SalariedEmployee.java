import java.io.Serializable;

public class SalariedEmployee extends Employee implements Serializable {

    private double weeklySalary;

    public SalariedEmployee(String firstName, String lastName, String socialSecurityNumber, double weeklySalary)
            throws InvalidSalaryException {
        super(firstName, lastName, socialSecurityNumber);

        if (weeklySalary < 0) {
            throw new InvalidSalaryException("Weekly salary cannot be negative.");
        }

        this.weeklySalary = weeklySalary;
    }

    public double getWeeklySalary() {
        return weeklySalary;
    }

    @Override
    public double getPaymentAmount() {
        return weeklySalary;
    }
}