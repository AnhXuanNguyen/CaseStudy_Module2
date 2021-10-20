package controller;

import model.Admin;
import model.SeverComputer;
import storage.FileAdmin;
import storage.FileSeverComputer;

import java.io.IOException;

public class ComputerSeverManager {
    private FileSeverComputer fileSeverComputer = FileSeverComputer.getInstance();
    private FileAdmin fileAdmin = FileAdmin.getInstance();
    private SeverComputer severComputer;
    private Admin admin;
    public ComputerSeverManager() throws IOException, ClassNotFoundException {
        severComputer = fileSeverComputer.readFile();
        admin = fileAdmin.readFile();
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) throws IOException {
        this.admin = admin;
        fileAdmin.writeFile(admin);
    }

    public boolean changeCode(String newCode) throws IOException {
        if (severComputer.getCode().equals(newCode)){
            return false;
        }
        severComputer.setCode(newCode);
        fileSeverComputer.writeFile(severComputer);
        return true;
    }

    public SeverComputer getSeverComputer() {
        return severComputer;
    }

    public void setSeverComputer(SeverComputer severComputer) throws IOException {
        this.severComputer = severComputer;
        fileSeverComputer.writeFile(severComputer);
    }

    public boolean changeUserName(String newUsername) throws IOException {
        if (admin.getUserName().equals(newUsername)){
            return false;
        }
        admin.setUserName(newUsername);
        fileAdmin.writeFile(admin);
        return true;
    }
    public boolean changePassWord(String newPassword) throws IOException {
        if (isPassword(newPassword)){
            admin.setPassWord(newPassword);
            fileAdmin.writeFile(admin);
            return true;
        }
        return false;
    }
    public boolean isPassword(String newPassword){
        if (admin.getPassWord().equals(newPassword)){
            return false;
        }
        return true;
    }
    public long toTalCash(){
        return admin.getMoneyInPocket();
    }
    public long withDrawMoney(){
        return admin.withDrawMoney();
    }
    public String toString(){
        return admin.toString()+" "+severComputer.toString();
    }
}