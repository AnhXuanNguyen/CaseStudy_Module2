package controller;
import model.ClientComputer;
import model.Customer;
import storage.FileClientComputers;
import storage.FileCustomers;
import java.io.IOException;
import java.time.LocalDateTime;

public class ControllerClientComputer {
    private FileCustomers fileCustomers = FileCustomers.getInstance();
    private FileClientComputers fileClientComputers = FileClientComputers.getInstance();
    private final int CASH_ON_TIME = 5000;
    private ClientComputerManager clientComputerManager = ClientComputerManager.getInstance();
    private CustomerManager customerManager = CustomerManager.getInstance();

    public ControllerClientComputer() throws IOException, ClassNotFoundException {
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
    public String showAllClientComputer(){
        return clientComputerManager.toString();
    }
    public boolean login(int index, String user, String password) throws IOException {
        if (customerManager.login(user, password)){
            LocalDateTime openTime = LocalDateTime.now();
            ClientComputer clientComputer = searchClientComputer(index);
            Customer customer = customerManager.searchCutomer(user);
            clientComputer.setCustomer(customer);
            clientComputer.setOpenTime(openTime);
            clientComputer.open();
            fileClientComputers.writeFile(clientComputerManager.getClientComputers());
            fileCustomers.writeFile(customerManager.getCustomers());
            return true;
        }
        return false;
    }
    public ClientComputer searchClientComputer(int index){
        return clientComputerManager.searchClientComputer(index);
    }
    public boolean changePassword(String user, String password, String newPassword) throws IOException {
        return customerManager.changePassword(user, password, newPassword);
    }
    public String showSevic(int index){
        return clientComputerManager.searchClientComputer(index).toString();
    }
    public void close(int index, String userName) throws IOException {
        Customer customer = customerManager.searchCutomer(userName);
        ClientComputer clientComputer = searchClientComputer(index);
        LocalDateTime endTime = LocalDateTime.now();
        customer.setLastLogin(endTime);
        int cash = (endTime.getHour() - clientComputer.getOpenTime().getHour())*CASH_ON_TIME + 2000;
        cash = customer.getMoney() - cash;
        customer.setMoney(cash);
        clientComputer.close();
        fileClientComputers.writeFile(clientComputerManager.getClientComputers());
        fileCustomers.writeFile(customerManager.getCustomers());
    }
}
