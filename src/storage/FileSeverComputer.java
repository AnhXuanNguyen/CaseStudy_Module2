package storage;

import model.SeverComputer;

import java.io.*;

public class FileSeverComputer{
    private final String FILE_SEVERCOMPUTER = "severcomputer.txt";
    private static FileSeverComputer fileSeverComputer;
    private FileSeverComputer(){}
    public static FileSeverComputer getInstance(){
        if (fileSeverComputer == null){
            fileSeverComputer = new FileSeverComputer();
        }
        return fileSeverComputer;
    }
    public void writeFile(SeverComputer severComputer) throws IOException {
        File file = new File(FILE_SEVERCOMPUTER);
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
        File file = new File(FILE_SEVERCOMPUTER);
        if (file.length() <= 0){
            return severComputer;
        }
        InputStream is = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(is);
        severComputer = (SeverComputer) ois.readObject();
        return severComputer;
    }
}
