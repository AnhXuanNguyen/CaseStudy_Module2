package controller;

import model.Customer;
import storage.FileCustomers;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class CustomerManager {
    private final FileCustomers FILE_CUSTOMER = FileCustomers.getInstance();
    private List<Customer> customers;
    private static CustomerManager customerManager;
    private CustomerManager() throws IOException, ClassNotFoundException {
        customers = FILE_CUSTOMER.readFile();
    }
    public static CustomerManager getInstance() throws IOException, ClassNotFoundException {
        if (customerManager == null){
            customerManager = new CustomerManager();
        }
        return customerManager;
    }
    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) throws IOException {
        this.customers = customers;
        FILE_CUSTOMER.writeFile(customers);
    }
    public boolean isUserName(String userName){
        for (Customer customer: customers){
            if (customer.getUserName().equals(userName)){
                return true;
            }
        }
        return false;
    }
    public boolean isCustomer(Customer otherCustomer){
        for (Customer customer: customers){
            if (customer.equals(otherCustomer)){
                return true;
            }
        }
        return false;
    }
    public boolean isPassword(String passWord){
        for (Customer customer: customers){
            if (customer.getPassword().equals(passWord)){
                return true;
            }
        }
        return false;
    }
    public Customer searchCutomer(String userName){
        for (Customer customer: customers){
            if (customer.getUserName().equals(userName)){
                return customer;
            }
        }
        return null;
    }
    public boolean changePassword(String userName, String newPassword) throws IOException {
        if (isUserName(userName)){
            searchCutomer(userName).setPassword(newPassword);
            FILE_CUSTOMER.writeFile(customers);
            return true;
        }
        return false;
    }
    public boolean deposit(String userName, long cash) throws IOException {
        if (isUserName(userName)){
            searchCutomer(userName).addMoney(cash);
            FILE_CUSTOMER.writeFile(customers);
            return true;
        }
        return false;
    }
    public LocalDateTime getLastLogin(String userName){
        if (isUserName(userName)){
            return searchCutomer(userName).getLastLogin();
        }
        return null;
    }
    public boolean login(String userName, String passWord) throws IOException {
        if (searchCutomer(userName).login(userName, passWord)){
            FILE_CUSTOMER.writeFile(customers);
            return true;
        }
        return false;
    }
    public boolean addCustomer(Customer customer) throws IOException {
        if (!isCustomer(customer)){
            customers.add(customer);
            FILE_CUSTOMER.writeFile(customers);
            return true;
        }
        return false;
    }
    public boolean delCustomer(String userName) throws IOException {
        if (isUserName(userName)){
            customers.remove(searchCutomer(userName));
            FILE_CUSTOMER.writeFile(customers);
            return true;
        }
        return false;
    }
    public boolean register(String user) throws IOException {
        if (isUserName(user)){
            return false;
        }
        Customer customer = new Customer(user);
        customers.add(customer);
        FILE_CUSTOMER.writeFile(customers);
        return true;
    }
    public boolean changePassword(String userName, String password, String newPassword) throws IOException {
        if (login(userName,password)){
            searchCutomer(userName).setPassword(newPassword);
            FILE_CUSTOMER.writeFile(customers);
            return true;
        }
        return false;
    }
    @Override
    public String toString() {
        String show = "";
        for (Customer customer: customers){
            show += customer.toString()+"\n";
        }
        return show;
    }
}
