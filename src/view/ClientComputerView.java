package view;

import controller.ControllerSeverComputer;

import java.io.IOException;
import java.util.Scanner;

public class ClientComputerView {
    private Scanner number = new Scanner(System.in);
    private Scanner line = new Scanner(System.in);
    private ControllerSeverComputer controllerSeverComputer = ControllerSeverComputer.getInstance();

    public ClientComputerView() throws IOException, ClassNotFoundException {
    }
    public void showClientComputer() throws IOException {
        System.out.println(controllerSeverComputer.showAllClientComputer());
        System.out.println("Nhập lựa chọn");
        int choice = number.nextInt();
        if (choice < 0 && choice > controllerSeverComputer.getListClientComputer().size()-1){
            System.out.println("Không hợp lệ");
        }
        else {
            System.out.println("Nhập username");
            String userName = line.nextLine();
            System.out.println("Nhập password");
            String password = line.nextLine();
            if (login(choice, userName, password)){
                homePage(choice,userName);
            }
            else System.out.println("Sai thông tin đăng nhập");
        }
    }
    public boolean login(int choice, String userName, String password) throws IOException {
        if (controllerSeverComputer.userLogin(choice, userName, password)){
            return true;
        }
        return false;
    }
    public void homePage(int idClient,String userName) throws IOException {
        System.out.println("Đăng nhập thành công");
        boolean check = true;
        while (check){
            System.out.println("1. Xem thông tin tài khoản");
            System.out.println("2. Đổi mật khẩu");
            System.out.println("3. Tắt máy");
            int choice = number.nextInt();
            switch (choice){
                case 1: showAccount(userName); break;
                case 2: changePassWord(userName); break;
                case 3: {
                    close(idClient);
                    check = false;
                }
            }
        }
    }
    public void showAccount(String userName){
        System.out.println(controllerSeverComputer.searchAccout(userName));
    }
    public void changePassWord(String userName) throws IOException {
        System.out.println("Nhập mật khẩu mới");
        String newPassWord = line.nextLine();
        if (controllerSeverComputer.changePasswordCustomer(userName, newPassWord)){
            System.out.println("Đổi mật khẩu thành công");
        }
        else System.out.println("Đổi mật khẩu không thành công");
    }
    public void close(int idClient) throws IOException {
        String code = controllerSeverComputer.getClientComputers().get(idClient).getCode();
        controllerSeverComputer.closeClientComputer(code);
    }
}
