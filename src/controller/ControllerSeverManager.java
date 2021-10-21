package controller;
import model.ClientComputer;

import java.io.IOException;

public class ControllerSeverManager {
    private ClientComputerManager clientComputerManager = ClientComputerManager.getInstance();
    private CustomerManager customerManager = CustomerManager.getInstance();
    private ContactAdminAndSeverComputer contactAdminAndSeverComputer = ContactAdminAndSeverComputer.getInstance();

    public ControllerSeverManager() throws IOException, ClassNotFoundException {
    }

    public ControllerSeverManager(ClientComputerManager clientComputerManager, CustomerManager customerManager, ContactAdminAndSeverComputer contactAdminAndSeverComputer) throws IOException, ClassNotFoundException {
        this.clientComputerManager = clientComputerManager;
        this.customerManager = customerManager;
        this.contactAdminAndSeverComputer = contactAdminAndSeverComputer;
    }

    public ClientComputerManager getClientComputerManager() {
        return clientComputerManager;
    }

    public void setClientComputerManager(ClientComputerManager clientComputerManager) {
        this.clientComputerManager = clientComputerManager;
    }

    public CustomerManager getCustomerManager() {
        return customerManager;
    }

    public void setCustomerManager(CustomerManager customerManager) {
        this.customerManager = customerManager;
    }

    public ContactAdminAndSeverComputer getContactAdminAndSeverComputer() {
        return contactAdminAndSeverComputer;
    }

    public void setContactAdminAndSeverComputer(ContactAdminAndSeverComputer contactAdminAndSeverComputer) {
        this.contactAdminAndSeverComputer = contactAdminAndSeverComputer;
    }
    public boolean adminLogin(String user, String password) throws IOException {
        if (contactAdminAndSeverComputer.login(user, password)){
            return true;
        }
        return false;
    }
    public boolean registerAccoutCustomer(String userName) throws IOException {
        if (customerManager.register(userName)){
            return true;
        }
        return false;
    }
    public boolean changePasswordAccountCustomer(String user, String newPasswor) throws IOException {
        if (customerManager.changePassword(user, newPasswor)){
            return true;
        }
        return false;
    }
    public boolean depositAccoutCustomer(String userName, int cash) throws IOException {
        if (customerManager.deposit(userName, cash)){
            contactAdminAndSeverComputer.addMoney(cash);
            return true;
        }
        return false;
    }
    public boolean createClientComputer(ClientComputer clientComputer) throws IOException {
        if (clientComputerManager.addClientComputer(clientComputer)){
            return true;
        }
        return false;
    }
    public boolean changePasswordAccoutAdmin(String userName, String password, String newPassword) throws IOException {
        if (contactAdminAndSeverComputer.changePassword(userName, password, newPassword)){
            return true;
        }
        return false;
    }
    public boolean changeUserNameAccountAdmin(String newUserName) throws IOException {
        if (contactAdminAndSeverComputer.changeUserName(newUserName)){
            return true;
        }
        return false;
    }
    public boolean delAccoutCustomer(String userName) throws IOException {
        if (customerManager.delCustomer(userName)){
            return true;
        }
        return false;
    }
    public String showAllAccoutnCustomer(){
        return customerManager.toString();
    }
    public int customerSize(){
        return customerManager.getCustomers().size();
    }
    public int clientComputerSize(){
        return clientComputerManager.getClientComputers().size();
    }
    public String showAllClientComputer(){
        return clientComputerManager.toString();
    }
    public String showAdminAndSeverComputer(){
        return contactAdminAndSeverComputer.toString();
    }
    public void withDraw() throws IOException {
        contactAdminAndSeverComputer.withDraw();
    }
    public boolean delClientComputer(String id) throws IOException {
        if (clientComputerManager.delClientComputer(id)){
            return true;
        }
        return false;
    }
    public boolean delClientComputer(int index) throws IOException {
        if (clientComputerManager.delClientComputer(index)){
            return true;
        }
        return false;
    }
    public void logOutAdmin() throws IOException {
        contactAdminAndSeverComputer.logout();
    }
    public boolean isSeverComputer(){
        return contactAdminAndSeverComputer.isSeverComputer();
    }
}
