package storage;
import model.SeverComputer;

import java.io.*;

public class FileSeverComputer {
    private final String FILE_SERVER_COMPUTER = "sever-computer.txt";
    private FileSeverComputer(){}
    private static FileSeverComputer fileSeverComputer;
    public static FileSeverComputer getInstance(){
        if (fileSeverComputer == null){
            fileSeverComputer = new FileSeverComputer();
        }
        return fileSeverComputer;
    }
    public void writeFile(SeverComputer severComputer) throws IOException {
        File file = new File(FILE_SERVER_COMPUTER);
        if (!file.exists()){
            file.createNewFile();
        }
        OutputStream os = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(severComputer);
        oos.close();
        os.close();
    }
    public SeverComputer readFile() throws IOException, ClassNotFoundException {
        SeverComputer severComputer = new SeverComputer();
        File file = new File(FILE_SERVER_COMPUTER);
        if (!file.exists()) {
            file.createNewFile();
        }
        if (file.length() <= 0) {
            return (SeverComputer) severComputer;
        }
        InputStream is = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(is);
        severComputer = (SeverComputer) ois.readObject();
        ois.close();
        is.close();
        return severComputer;
    }
}
