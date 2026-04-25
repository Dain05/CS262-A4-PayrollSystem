import java.io.Serializable;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

public abstract class Employee implements Payable, Serializable {

    protected String firstName;
    protected String lastName;
    protected String socialSecurityNumber;

    public Employee(String firstName, String lastName, String socialSecurityNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public void writeToFile() throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter("paystub.txt", true));
        out.println("Name: " + getFullName());
        out.println("Payment Amount: $" + String.format("%.2f", getPaymentAmount()));
        out.println("Date: " + LocalDate.now());
        out.println("----------------------------------");
        out.close();
    }
}