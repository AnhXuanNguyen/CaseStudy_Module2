package controller;
import model.Customer;
import storage.FileCustomer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class CustomerManager {
    private FileCustomer fileCustomer = FileCustomer.getInstance();
    private List<Customer> customers = fileCustomer.readFile();
    public CustomerManager() throws IOException, ClassNotFoundException {
    }
    public boolean addCustomer(Customer customer) throws IOException {
        if (isCustomer(customer)){
            return false;
        }
        customers.add(customer);
        fileCustomer.writeFile(customers);
        return true;
    }
    public boolean isCustomer(Customer customer){
        for (Customer customer1: customers){
            if (customer1.equals(customer)){
                return true;
            }
        }
        return false;
    }

    public FileCustomer getFileCustomer() {
        return fileCustomer;
    }

    public void setFileCustomer(FileCustomer fileCustomer) {
        this.fileCustomer = fileCustomer;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) throws IOException {
        this.customers = customers;
        fileCustomer.writeFile(customers);
    }

    public Customer searchCustomer(String userName){
        if (isUserName(userName)){
            for (Customer customer: customers){
                if (customer.getUserName().equals(userName)){
                    return customer;
                }
            }
        }
        return null;
    }
    public boolean isUserName(String userName){
        for (Customer customer: customers){
            if (customer.getUserName().equals(userName)){
                return true;
            }
        }
        return false;
    }
    public boolean editPassword(String userName, String newPass) throws IOException {
        if (isUserName(userName)){
            searchCustomer(userName).setPassWord(newPass);
            fileCustomer.writeFile(customers);
            return true;
        }
        return false;
    }
    public boolean delUser(String userName) throws IOException {
        if (isUserName(userName)){
            customers.remove(searchCustomer(userName));
            fileCustomer.writeFile(customers);
            return true;
        }
        return false;
    }
    public void autoDel() throws IOException {
        LocalDateTime time = LocalDateTime.now();
        for (Customer customer: customers){
            if (customer.getLoginDay().plusMonths(1).equals(time.getMonth())){
                delUser(customer.getUserName());
            }
        }
    }
    public boolean deposit(String userName, long cash) throws IOException {
        if (isUserName(userName)){
            searchCustomer(userName).setDepositMoney(cash);
            fileCustomer.writeFile(customers);
            return true;
        }
        return false;
    }
    public boolean login(String userName, String passWord){
        if (isUserName(userName) && isPassWord(passWord)){
            return true;
        }
        return false;
    }
    public boolean isPassWord(String passWord){
        for (Customer customer: customers){
            if (customer.getPassWord().equals(passWord)){
                return true;
            }
        }
        return false;
    }
}
