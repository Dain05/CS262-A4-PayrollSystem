import java.io.*;
import java.util.ArrayList;

public class PayrollDataManager {

    public static void saveData(ArrayList<Payable> records) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("payroll_data.dat"));
        out.writeObject(records);
        out.close();
    }

    public static ArrayList<Payable> loadData() throws IOException, ClassNotFoundException {
        File file = new File("payroll_data.dat");

        if (!file.exists()) {
            return new ArrayList<>();
        }

        ObjectInputStream in = new ObjectInputStream(new FileInputStream("payroll_data.dat"));
        ArrayList<Payable> records = (ArrayList<Payable>) in.readObject();
        in.close();

        return records;
    }

    public static void exportToJSON(ArrayList<Payable> records) {
        try (PrintWriter out = new PrintWriter(new FileWriter("payroll_data.json"))) {

            out.println("[");

            for (int i = 0; i < records.size(); i++) {
                Payable p = records.get(i);

                String type = p.getClass().getSimpleName();
                String name = "";

                if (p instanceof Employee) {
                    Employee e = (Employee) p;
                    name = e.getFirstName() + " " + e.getLastName();
                } else if (p instanceof Invoice) {
                    Invoice invoice = (Invoice) p;
                    name = invoice.getPartDescription();
                }

                out.println("  {");
                out.println("    \"type\": \"" + type + "\",");
                out.println("    \"name\": \"" + name + "\",");
                out.println("    \"paymentAmount\": " + String.format("%.2f", p.getPaymentAmount()));
                out.print("  }");

                if (i < records.size() - 1) {
                    out.println(",");
                } else {
                    out.println();
                }
            }

            out.println("]");

            // JSON is preferred over Java serialization because it is human-readable,
            // easier to use with web systems, and more flexible across different platforms.

        } catch (IOException e) {
            System.out.println("Error exporting JSON file.");
        }
    }
}