package storage;

import model.Admin;

import java.io.*;

public class FileAdmin {
    private final String FILE_ADMIN = "admin.txt";
    private static FileAdmin fileAdmin;
    private FileAdmin(){}
    public static FileAdmin getInstance(){
        if (fileAdmin == null){
            fileAdmin = new FileAdmin();
        }
        return fileAdmin;
    }
    public void writeFile(Admin admin) throws IOException {
        File file = new File(FILE_ADMIN);
        if (!file.exists()){
            file.createNewFile();
        }
        OutputStream os = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(admin);
        oos.close();
        os.close();
    }
    public Admin readFile() throws IOException, ClassNotFoundException {
        File file = new File(FILE_ADMIN);
        Admin admin = new Admin("Admin", "1");
        if (file.exists()){
            file.createNewFile();
        }
        if (file.length() <= 0){
            return admin;
        }
        InputStream is = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(is);
        admin = (Admin) ois.readObject();
        return admin;
    }
}
