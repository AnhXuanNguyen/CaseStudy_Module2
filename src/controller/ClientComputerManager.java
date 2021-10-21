package controller;
import model.ClientComputer;
import storage.FileClientComputers;
import java.io.IOException;
import java.util.List;

public class ClientComputerManager {
    private final FileClientComputers FILE_CLIENT_COMPUTERS = FileClientComputers.getInstance();
    private List<ClientComputer> clientComputers;

    private static ClientComputerManager clientComputerManager;
    private ClientComputerManager() throws IOException, ClassNotFoundException {
        clientComputers = FILE_CLIENT_COMPUTERS.readFile();
    }
    public static ClientComputerManager getInstance() throws IOException, ClassNotFoundException {
        if (clientComputerManager == null){
            clientComputerManager = new ClientComputerManager();
        }
        return clientComputerManager;
    }
    public List<ClientComputer> getClientComputers() {
        return clientComputers;
    }

    public void setClientComputers(List<ClientComputer> clientComputers) throws IOException {
        this.clientComputers = clientComputers;
        FILE_CLIENT_COMPUTERS.writeFile(clientComputers);
    }
    public boolean isClientComputer(ClientComputer otherClientComputer){
        for (ClientComputer clientComputer: clientComputers){
            if (clientComputer.equals(otherClientComputer)){
                return true;
            }
        }
        return false;
    }
    public boolean isIdClientComputer(String id){
        for (ClientComputer clientComputer: clientComputers){
            if (clientComputer.getId().equals(id)){
                return true;
            }
        }
        return false;
    }
    public ClientComputer searchClientComputer(String id){
        for (ClientComputer clientComputer:clientComputers){
            if (clientComputer.getId().equals(id)){
                return clientComputer;
            }
        }
        return null;
    }
    public boolean addClientComputer(ClientComputer clientComputer) throws IOException {
        if (!isClientComputer(clientComputer)){
            clientComputers.add(clientComputer);
            FILE_CLIENT_COMPUTERS.writeFile(clientComputers);
            return true;
        }
        return false;
    }
    public boolean delClientComputer(String id) throws IOException {
        if (isIdClientComputer(id)){
            clientComputers.remove(searchClientComputer(id));
            FILE_CLIENT_COMPUTERS.writeFile(clientComputers);
            return true;
        }
        return false;
    }
    public void open(int index) throws IOException {
        searchClientComputer(index).open();
        FILE_CLIENT_COMPUTERS.writeFile(clientComputers);
    }
    public void close(int index) throws IOException {
        searchClientComputer(index).close();
        FILE_CLIENT_COMPUTERS.writeFile(clientComputers);
    }
    public ClientComputer searchClientComputer(int index){
        if (index >= 0 && index < clientComputers.size()){
            return clientComputers.get(index);
        }
        return null;
    }
    public boolean delClientComputer(int index) throws IOException {
        if (searchClientComputer(index) == null){
            return false;
        }
        clientComputers.remove(searchClientComputer(index));
        FILE_CLIENT_COMPUTERS.writeFile(clientComputers);
        return true;
    }
    @Override
    public String toString() {
        String show = "";
        for (ClientComputer clientComputer: clientComputers){
            show += clientComputer.toString()+"\n";
        }
        return show;
    }
}