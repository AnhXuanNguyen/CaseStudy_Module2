package controller;

import model.Admin;
import model.LoginLogout;
import model.SeverComputer;
import storage.FileAdmin;
import storage.FileSeverComputer;

import java.io.IOException;

public class ContactAdminAndSeverComputer implements LoginLogout {
    private FileAdmin fileAdmin = FileAdmin.getInstance();
    private FileSeverComputer fileSeverComputer = FileSeverComputer.getInstance();
    private Admin admin = fileAdmin.readFile();
    private SeverComputer severComputer = fileSeverComputer.readFile();
    private static ContactAdminAndSeverComputer contactAdminAndSeverComputer;

    private ContactAdminAndSeverComputer() throws IOException, ClassNotFoundException {
    }
    public static ContactAdminAndSeverComputer getInstance() throws IOException, ClassNotFoundException {
        if (contactAdminAndSeverComputer == null){
            contactAdminAndSeverComputer = new ContactAdminAndSeverComputer();
        }
        return contactAdminAndSeverComputer;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public SeverComputer getSeverComputer() {
        return severComputer;
    }
    public boolean changePassword(String userName, String password, String newPassword) throws IOException {
        if (admin.login(userName, password)){
            admin.setPassword(newPassword);
            fileAdmin.writeFile(admin);
            return true;
        }
        return false;
    }
    public boolean changeUserName(String newUserName) throws IOException {
        if (admin.getUserName().equals(newUserName)){
            return false;
        }
        admin.setUserName(newUserName);
        fileAdmin.writeFile(admin);
        return true;
    }
    public void addMoney(int cash) throws IOException {
        admin.addMoney(cash);
        fileAdmin.writeFile(admin);
    }
    public void setSeverComputer(SeverComputer severComputer) {
        this.severComputer = severComputer;
    }
    public void withDraw() throws IOException {
        admin.setMoney();
        fileAdmin.writeFile(admin);
    }
    public boolean isSeverComputer(){
        return severComputer.isStatus();
    }
    @Override
    public boolean login(String user, String password) throws IOException {
        if (admin.login(user,password)){
            severComputer.open();
            fileSeverComputer.writeFile(severComputer);
            fileAdmin.writeFile(admin);
            return true;
        }
        return false;
    }

    @Override
    public void logout() throws IOException {
        admin.logout();
        fileAdmin.writeFile(admin);
        severComputer.close();
        fileSeverComputer.writeFile(severComputer);
    }

    @Override
    public String toString() {
        return admin.toString() +" "+severComputer.toString();
    }
}
