import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.time.LocalDate;

public class Invoice implements Payable, Serializable {

    private String partNumber;
    private String partDescription;
    private int quantity;
    private double pricePerItem;

    public Invoice(String partNumber, String partDescription, int quantity, double pricePerItem)
            throws InvalidSalaryException {

        if (quantity < 0 || pricePerItem < 0) {
            throw new InvalidSalaryException("Quantity and price per item cannot be negative.");
        }

        this.partNumber = partNumber;
        this.partDescription = partDescription;
        this.quantity = quantity;
        this.pricePerItem = pricePerItem;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public String getPartDescription() {
        return partDescription;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPricePerItem() {
        return pricePerItem;
    }

    @Override
    public double getPaymentAmount() {
        return quantity * pricePerItem;
    }

    @Override
    public void writeToFile() throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter("paystub.txt", true));
        out.println("Invoice: " + partDescription);
        out.println("Payment Amount: $" + String.format("%.2f", getPaymentAmount()));
        out.println("Date: " + LocalDate.now());
        out.println("----------------------------------");
        out.close();
    }
}