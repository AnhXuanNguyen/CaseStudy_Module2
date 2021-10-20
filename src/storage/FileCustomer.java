package storage;
import model.Customer;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileCustomer {
    private final String FILE_PATH = "customer.txt";
    private static FileCustomer fileCustomer;
    private FileCustomer(){
    }
    public static FileCustomer getInstance(){
        if (fileCustomer == null){
            return new FileCustomer();
        }
        return fileCustomer;
    }
    public void writeFile(List<Customer> customers) throws IOException {
        File file = new File(FILE_PATH);
        if (!file.exists()){
            file.createNewFile();
        }
        OutputStream os = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(customers);
        oos.close();
        os.close();
    }
    public List<Customer> readFile() throws IOException, ClassNotFoundException {
        List<Customer> customers = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()){
            file.createNewFile();
        }
        if (file.length() <= 0){
            return customers;
        }
        InputStream is = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(is);
        customers = (List<Customer>) ois.readObject();
        ois.close();
        is.close();
        return customers;
    }
}