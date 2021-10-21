package storage;

import model.Customer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileCustomers {
    private final String FILE_CUSTOMERS = "customers.txt";
    private static FileCustomers fileCustomers;
    private FileCustomers(){}
    public static FileCustomers getInstance(){
        if (fileCustomers == null){
            fileCustomers = new FileCustomers();
        }
        return fileCustomers;
    }
    public void writeFile(List<Customer> customers) throws IOException {
        File file = new File(FILE_CUSTOMERS);
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
        File file = new File(FILE_CUSTOMERS);
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
