package controller;

import model.Admin;
import model.LoginLogout;
import model.SeverComputer;
import storage.FileAdmin;
import storage.FileSeverComputer;

import java.io.IOException;

public class ContactAdminAndSeverComputer implements LoginLogout {
    private final FileAdmin FILE_ADMIN = FileAdmin.getInstance();
    private final FileSeverComputer FILE_SEVER_COMPUTER = FileSeverComputer.getInstance();
    private Admin admin = FILE_ADMIN.readFile();
    private SeverComputer severComputer = FILE_SEVER_COMPUTER.readFile();
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
            FILE_ADMIN.writeFile(admin);
            return true;
        }
        return false;
    }
    public boolean changeUserName(String newUserName) throws IOException {
        if (admin.getUserName().equals(newUserName)){
            return false;
        }
        admin.setUserName(newUserName);
        FILE_ADMIN.writeFile(admin);
        return true;
    }
    public void addMoney(int cash) throws IOException {
        admin.addMoney(cash);
        FILE_ADMIN.writeFile(admin);
    }
    public void setSeverComputer(SeverComputer severComputer) {
        this.severComputer = severComputer;
    }
    public void withDraw() throws IOException {
        admin.setMoney();
        FILE_ADMIN.writeFile(admin);
    }
    public boolean isSeverComputer(){
        return severComputer.isStatus();
    }
    @Override
    public boolean login(String user, String password) throws IOException {
        if (admin.login(user,password)){
            severComputer.open();
            FILE_SEVER_COMPUTER.writeFile(severComputer);
            FILE_ADMIN.writeFile(admin);
            return true;
        }
        return false;
    }

    @Override
    public void logout() throws IOException {
        admin.logout();
        FILE_ADMIN.writeFile(admin);
        severComputer.close();
        FILE_SEVER_COMPUTER.writeFile(severComputer);
    }

    @Override
    public String toString() {
        return admin.toString() +" "+severComputer.toString();
    }
}
