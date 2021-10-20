package controller;

import model.ClientComputer;
import model.Customer;
import model.User;

import java.io.IOException;
import java.util.List;

public class ControllerSeverComputer {
    private ComputerSeverManager computerSeverManager = new ComputerSeverManager();
    private CustomerManager customerManager = new CustomerManager();
    private ComputerClientManager computerClientManager = new ComputerClientManager();
    private static ControllerSeverComputer controllerSeverComputer;


    private ControllerSeverComputer() throws IOException, ClassNotFoundException {
    }
    public static ControllerSeverComputer getInstance() throws IOException, ClassNotFoundException {
        if (controllerSeverComputer == null){
            controllerSeverComputer = new ControllerSeverComputer();
        }
        return controllerSeverComputer;
    }
    public void open(){
        computerSeverManager.getSeverComputer().open();
    }
    public boolean registerNewUser(String userName, String passWord) throws IOException {
        if (customerManager.isUserName(userName)){
            return false;
        }
        Customer customer = new Customer(userName, passWord);
        customerManager.addCustomer(customer);
        return true;
    }
    public boolean addClientComputer(ClientComputer clientComputer) throws IOException {
        if (computerClientManager.addClientComputer(clientComputer)){
            return true;
        }
        return false;
    }
    public boolean loginAdmin(String userName, String passWord) throws IOException {
        if (computerSeverManager.login(userName,passWord)){
                return true;
            }
        return false;
    }
    public boolean changePasswordAdmin(String useName, String passWord, String newPassWord) throws IOException {
        if (loginAdmin(useName, passWord)){
            computerSeverManager.changePassWord(newPassWord);
            return true;
        }
        return false;
    }
    public boolean changePasswordCustomer(String useName, String newPassWord) throws IOException {
        if (customerManager.editPassword(useName,newPassWord)){
            return true;
        }
        return false;
    }
    public List<ClientComputer> getClientComputers(){
        return computerClientManager.getClientComputers();
    }
    public boolean deposit(String useName ,long cash) throws IOException {
        if (customerManager.deposit(useName, cash)){
            computerSeverManager.addCash(cash);
            return true;
        }
        computerSeverManager.getAdmin().setMoneyInPocket(cash);
        return true;
    }
    public void close() throws IOException {
        computerSeverManager.close();
    }
    public boolean delCustomer(String userName) throws IOException {
        if (customerManager.delUser(userName)){
            return true;
        }
        return false;
    }
    public String showAllUser(){
        String show = "";
        for (Customer customer: getListCustomer()){
            show += customer.toString();
        }
        return show;
    }
    public Customer searchAccout(String userName){
        return customerManager.searchCustomer(userName);
    }
    public String showAllClientComputer(){
        String show = "";
        for (int i = 0; i < getListClientComputer().size(); i++){
            show += i+"."+getClientComputers().get(i).getCode() + getClientComputers().get(i).toString()+"\n";
        }
        return show;
    }
    public List<ClientComputer> getListClientComputer(){
        return computerClientManager.getClientComputers();
    }
    public List<Customer> getListCustomer(){
        return customerManager.getCustomers();
    }
    public long showTotalCash(){
        return computerSeverManager.toTalCash();
    }
    public long widthDrawMoney() throws IOException {
        return computerSeverManager.withDrawMoney();
    }
    public boolean delClientComputer(String code) throws IOException {
        if (computerClientManager.delClientComputer(code)){
            return true;
        }
        return false;
    }
    public String showSeverComputer(){
        return computerSeverManager.toString();
    }
    public boolean changeUserName(String newUserName) throws IOException {
        if (computerSeverManager.changeUserName(newUserName)){
            return true;
        }
        return false;
    }
    public boolean userLogin(int codeComputer, String userName, String passWord) throws IOException {
        if (customerManager.login(userName,passWord)){
            String codeClientConputer = computerClientManager.getClientComputers().get(codeComputer).getCode();
            computerClientManager.open(codeClientConputer, customerManager.searchCustomer(userName));
            return true;
        }
        return false;
    }
    public void closeClientComputer(String code) throws IOException {
        computerClientManager.close(code);
    }
}