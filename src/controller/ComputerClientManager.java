package controller;
import model.ClientComputer;
import model.Customer;
import storage.FileClientComputer;
import java.io.IOException;
import java.util.List;

public class ComputerClientManager {
    private FileClientComputer fileClientComputer = FileClientComputer.getInstance();
    private List<ClientComputer> clientComputers;
    public ComputerClientManager() throws IOException, ClassNotFoundException {
        clientComputers = fileClientComputer.readFile();
    }
    public boolean addClientComputer(ClientComputer clientComputer) throws IOException {
        if (isClientComputer(clientComputer)){
            return false;
        }
        clientComputers.add(clientComputer);
        fileClientComputer.writeFile(clientComputers);
        return true;
    }

    public List<ClientComputer> getClientComputers() {
        return clientComputers;
    }

    public void setClientComputers(List<ClientComputer> clientComputers) throws IOException {
        this.clientComputers = clientComputers;
        fileClientComputer.writeFile(clientComputers);
    }

    public boolean isCode(String id){
        for (ClientComputer clientComputer: clientComputers){
            if (clientComputer.getCode().equals(id)){
                return true;
            }
        }
        return false;
    }
    public boolean isClientComputer(ClientComputer clientComputer){
        for (ClientComputer clientComputer1: clientComputers){
            if (clientComputer1.equals(clientComputer)){
                return true;
            }
        }
        return false;
    }
    public ClientComputer searchClient(String code){
        for (ClientComputer clientComputer: clientComputers){
            if (clientComputer.getCode().equals(code)){
                return clientComputer;
            }
        }
        return null;
    }
    public boolean editCode(String code, String newCode) throws IOException {
        if (isCode(code)){
            searchClient(code).setCode(newCode);
            fileClientComputer.writeFile(clientComputers);
            return true;
        }
        return false;
    }
    public void open(String code,Customer customer) throws IOException {
        ClientComputer clientComputer = searchClient(code);
        clientComputer.setCustomer(customer);
        clientComputer.open();
        fileClientComputer.writeFile(clientComputers);
    }
    public void close(String code) throws IOException {
        searchClient(code).close();
        fileClientComputer.writeFile(clientComputers);
    }
    public boolean delClientComputer(String code) throws IOException {
        if (isCode(code)){
            clientComputers.remove(searchClientComputer(code));
            fileClientComputer.writeFile(clientComputers);
            return true;
        }
        return false;
    }
    public ClientComputer searchClientComputer(String code){
        for (ClientComputer clientComputer: clientComputers){
            if (clientComputer.getCode().equals(code)){
                return clientComputer;
            }
        }
        return null;
    }
}