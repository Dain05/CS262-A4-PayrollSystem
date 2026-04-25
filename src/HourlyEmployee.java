import java.io.Serializable;

public class HourlyEmployee extends Employee implements Serializable {

    private double hoursWorked;
    private double hourlyRate;

    public HourlyEmployee(String firstName, String lastName, String socialSecurityNumber,
                          double hoursWorked, double hourlyRate) throws InvalidSalaryException {
        super(firstName, lastName, socialSecurityNumber);

        if (hoursWorked < 0 || hourlyRate < 0) {
            throw new InvalidSalaryException("Hours and rate must be positive.");
        }

        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    @Override
    public double getPaymentAmount() {
        if (hoursWorked <= 40) {
            return hoursWorked * hourlyRate;
        } else {
            return (40 * hourlyRate) + ((hoursWorked - 40) * hourlyRate * 1.5);
        }
    }
}