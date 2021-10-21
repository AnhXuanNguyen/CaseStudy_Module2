package storage;

import model.ClientComputer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileClientComputers {
    private final String FILE_CLIENT_COMPUTER = "client-computer.txt";
    private static FileClientComputers fileClientComputers;
    private FileClientComputers(){}
    public static FileClientComputers getInstance(){
        if (fileClientComputers == null){
            fileClientComputers = new FileClientComputers();
        }
        return fileClientComputers;
    }
    public void writeFile(List<ClientComputer> clientComputers) throws IOException {
        File file = new File(FILE_CLIENT_COMPUTER);
        if (!file.exists()){
            file.createNewFile();
        }
        OutputStream os = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(clientComputers);
        oos.close();
        os.close();
    }
    public List<ClientComputer> readFile() throws IOException, ClassNotFoundException {
        List<ClientComputer> clientComputers = new ArrayList<>();
        File file = new File(FILE_CLIENT_COMPUTER);
        if (!file.exists()){
            file.createNewFile();
        }
        if (file.length() <= 0){
            return clientComputers;
        }
        InputStream is = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(is);
        clientComputers = (List<ClientComputer>) ois.readObject();
        ois.close();
        is.close();
        return clientComputers;
    }
}
