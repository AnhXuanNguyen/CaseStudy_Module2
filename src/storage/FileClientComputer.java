package storage;

import model.ClientComputer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileClientComputer {
    private final String FILE_PATH = "client-computer.txt";
    private static FileClientComputer fileClientComputer;
    private FileClientComputer(){
    }
    public static FileClientComputer getInstance(){
        if (fileClientComputer == null){
            return new FileClientComputer();
        }
        return fileClientComputer;
    }
    public void writeFile(List<ClientComputer> clientComputers) throws IOException {
        File file = new File(FILE_PATH);
        OutputStream os = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(clientComputers);
        oos.close();
        os.close();
    }
    public List<ClientComputer> readFile() throws IOException, ClassNotFoundException {
        List<ClientComputer> clientComputers = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (file.length() <= 0){
            return clientComputers;
        }
        InputStream is = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(is);
        clientComputers = (List<ClientComputer>) ois.readObject();
        return clientComputers;
    }
}
