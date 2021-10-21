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
        if (severComputer.getId().equals(newCode)){
            return false;
        }
        severComputer.setId(newCode);
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
        if (isUserName(newUsername)){
            return false;
        }
        admin.setUserName(newUsername);
        fileAdmin.writeFile(admin);
        return true;
    }
    public boolean changePassWord(String newPassword) throws IOException {
        if (!isPassword(newPassword)){
            admin.setPassWord(newPassword);
            fileAdmin.writeFile(admin);
            return true;
        }
        return false;
    }
    public boolean isPassword(String newPassword){
        if (admin.getPassWord().equals(newPassword)){
            return true;
        }
        return false;
    }
    public long toTalCash(){
        return admin.getMoneyInPocket();
    }
    public long withDrawMoney() throws IOException {
        long cash = admin.withDrawMoney();
        fileAdmin.writeFile(admin);
        return cash;
    }
    public String toString(){
        return (severComputer.isStatus()?severComputer.getId() + " " + admin.toString(): severComputer.getId()+": Disnapble");
    }
    public void close() throws IOException {
        severComputer.close();
        fileSeverComputer.writeFile(severComputer);
    }
    public boolean login(String userName, String passWord) throws IOException {
        if (isUserName(userName) && isPassword(passWord)){
            severComputer.open();
            fileSeverComputer.writeFile(severComputer);
            return true;
        }
        return false;
    }
    public boolean isUserName(String userName){
        if (admin.getUserName().equals(userName)){
            return true;
        }
        return false;
    }
    public void addCash(long cash) throws IOException {
        admin.setMoneyInPocket(cash);
        fileAdmin.writeFile(admin);
    }
}