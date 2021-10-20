package controller;

import model.ClientComputer;
import model.Customer;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public class ControllerSeverComputer implements Login {
    private ComputerSeverManager computerSeverManager = new ComputerSeverManager();
    private CustomerManager customerManager = new CustomerManager();
    private ComputerClientManager computerClientManager = new ComputerClientManager();

    public ControllerSeverComputer() throws IOException, ClassNotFoundException {
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
    public boolean addClientComputer(ClientComputer clientComputer){
        if (isClientComputer(clientComputer)){
            computerClientManager.getClientComputers().add(clientComputer);
            return true;
        }
        return false;
    }
    public boolean isClientComputer(ClientComputer clientComputer){
        for (ClientComputer clientComputer1: computerClientManager.getClientComputers()){
            if (clientComputer1.getCode().equals(clientComputer.getCode())){
                return false;
            }
        }
        return true;
    }
    public boolean login(String userName, String passWord){
        if (!computerSeverManager.getSeverComputer().isStatus()){
            return false;
        }
        if (computerSeverManager.getAdmin().getUserName().equals(userName)){
            if (computerSeverManager.getAdmin().getPassWord().equals(passWord)){
                open();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean changePassword(String useName, String passWord, String newPassWord) {
        if (login(useName, passWord)){
            computerSeverManager.getAdmin().setPassWord(newPassWord);
            return true;
        }
        return false;
    }
    public boolean changeUserPassword(String useName, String newPassWord){
        if (customerManager.isUserName(useName)){
            customerManager.searchCustomer(useName).setPassWord(newPassWord);
            return true;
        }
        return false;
    }
    public List<ClientComputer> getClientComputers(){
        return computerClientManager.getClientComputers();
    }
    public boolean deposit(String useName ,long cash){
        if (!customerManager.isUserName(useName)){
            return false;
        }
        customerManager.searchCustomer(useName).setDepositMoney(cash);
        computerSeverManager.getAdmin().setMoneyInPocket(cash);
        return true;
    }
    public long getTotalCash(){
        return computerSeverManager.getAdmin().getMoneyInPocket();
    }
    public long withDraw(){
        return computerSeverManager.getAdmin().withDrawMoney();
    }
    public void close(){
        computerSeverManager.getSeverComputer().close();
    }
    public boolean delCustomer(String userName) throws IOException {
        if (customerManager.isUserName(userName)){
            customerManager.delUser(userName);
            return true;
        }
        return false;
    }
    public String showAllUser(){
        String show = "";
        for (Customer customer: customerManager.getCustomers()){
            show += customer.toString();
        }
        return show;
    }
    public Customer searchAccout(String userName){
        return customerManager.searchCustomer(userName);
    }
    public String showAllClientComputer(){
        String show = "";
        for (int i = 0; i < computerClientManager.getClientComputers().size(); i++){
            show += i+"."+computerClientManager.getClientComputers().get(i).getCode()+"\n" + computerClientManager.getClientComputers().get(i).toString();
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
    public long widthDrawMoney(){
        return computerSeverManager.withDrawMoney();
    }
    public boolean isClientComputer(String code){
        for (ClientComputer clientComputer: computerClientManager.getClientComputers()){
            if (clientComputer.getCode().equals(code)){
                return true;
            }
        }
        return false;
    }
    public boolean delClientComputer(String code){
        if (isClientComputer(code)){
            computerClientManager.getClientComputers().remove(searchClient(code));
            return true;
        }
        return false;
    }
    public ClientComputer searchClient(String code){
        for (ClientComputer clientComputer: computerClientManager.getClientComputers()){
            if (clientComputer.getCode().equals(code)){
                return clientComputer;
            }
        }
        return null;
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
        for (Customer customer: getListCustomer()){
            if (customer.getUserName().equals(userName)){
                if (customer.getPassWord().equals(passWord)){
                    String clientComputer = computerClientManager.getClientComputers().get(codeComputer).getCode();
                    computerClientManager.open(clientComputer, customer);
                    return true;
                }
            }
        }
        return false;
    }
    public void closeClientComputer(String code) throws IOException {
        computerClientManager.close(code);
    }
}