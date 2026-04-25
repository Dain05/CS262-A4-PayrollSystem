import java.io.Serializable;

public class CommissionEmployee extends Employee implements Serializable {

    protected double grossSales;
    protected double commissionRate;

    public CommissionEmployee(String firstName, String lastName, String socialSecurityNumber,
                              double grossSales, double commissionRate) throws InvalidSalaryException {
        super(firstName, lastName, socialSecurityNumber);

        if (grossSales < 0 || commissionRate < 0) {
            throw new InvalidSalaryException("Gross sales and commission rate must be positive.");
        }

        this.grossSales = grossSales;
        this.commissionRate = commissionRate;
    }

    public double getGrossSales() {
        return grossSales;
    }

    public double getCommissionRate() {
        return commissionRate;
    }

    @Override
    public double getPaymentAmount() {
        return grossSales * commissionRate;
    }
}