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
}