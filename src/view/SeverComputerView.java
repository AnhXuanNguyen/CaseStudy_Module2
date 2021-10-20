package view;

import controller.ControllerSeverComputer;
import model.ClientComputer;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Scanner;

public class SeverComputerView implements Serializable {
    private Scanner number = new Scanner(System.in);
    private Scanner line = new Scanner(System.in);
    private ControllerSeverComputer controllerSeverComputer = new ControllerSeverComputer();

    public SeverComputerView() throws IOException, ClassNotFoundException {
    }

    public void login() throws IOException {
        controllerSeverComputer.open();
        System.out.println("Hello Admin");
        System.out.println("Nhập tên đăng nhập");
        String userName = line.nextLine();
        System.out.println("Nhập mật khẩu");
        String passWord = line.nextLine();
        homePage(userName, passWord);
    }
    public void homePage(String userName, String passWord) throws IOException {
        if (controllerSeverComputer.login(userName, passWord)){
            boolean check = true;
            while (check){
                System.out.println("1.Thêm tài khoản");
                System.out.println("2.Nạp tiền vào tài khoản");
                System.out.println("3.Xóa tài khoản");
                System.out.println("4.Đổi mật khẩu tài khoản");
                System.out.println("5.Show toàn bộ khách hàng");
                System.out.println("6.Show toàn bộ máy trạm");
                System.out.println("7.Show tổng doanh thu");
                System.out.println("8.Bàn giao ca mới");
                System.out.println("9.Trang cá nhân");
                System.out.println("10.Thêm máy trạm");
                System.out.println("11.Xóa máy trạm");
                System.out.println("12.Tắt máy chủ");
                System.out.println("0.Thoát");
                int choice = number.nextInt();
                switch (choice){
                    case 1: addCustomer(); break;
                    case 2: depositCash(); break;
                    case 3: delUserName(); break;
                    case 4: changePassUser(); break;
                    case 5: showAllUser(); break;
                    case 6: showAllClientComputer(); break;
                    case 7: showTotalCash(); break;
                    case 8: withDrawMoney(); break;
                    case 9: personalPage(); break;
                    case 10: addClientComputer(); break;
                    case 11: delClientComputer(); break;
                    case 12: controllerSeverComputer.close(); check = false; break;
                    case 0: {
                        check = false;
                    }break;
                }
            }
        }
        else System.out.println("Sai thông tin đăng nhập");
    }
    public void addCustomer() throws IOException {
        System.out.println("Nhập userName đăng ký");
        String userName = line.nextLine();
        System.out.println("Nhập password");
        String passWord = line.nextLine();
        controllerSeverComputer.registerNewUser(userName, passWord);
    }
    public void depositCash(){
        System.out.println("Nhập username");
        String userName = line.nextLine();
        System.out.println("Nhập số tiền nạp");
        long numberCash = number.nextLong();
        if (controllerSeverComputer.deposit(userName, numberCash)){
            System.out.println("Nạp tiền thành công");
        }else System.out.println("Nạp tiền không thành công");
    }
    public void delUserName() throws IOException {
        System.out.println("Nhập username muốn xóa");
        String userName = line.nextLine();
        if (controllerSeverComputer.delCustomer(userName)){
            System.out.println("Xóa thành công tài khoản "+userName);
        }
        else System.out.println("Tài khoản không tồn tại");
    }
    public void changePassUser(){
        System.out.println("Nhập vào username");
        String userName = line.nextLine();
        System.out.println("Nhập vào mật khẩu mới");
        String passWord = line.nextLine();
        if (controllerSeverComputer.changeUserPassword(userName, passWord)){
            System.out.println("Đổi pass thành công");
        }
        else System.out.println("Đổi pass không thành công");
    }
    public void showAllUser(){
        System.out.println(controllerSeverComputer.showAllUser());
    }
    public void showAllClientComputer(){
        System.out.println(controllerSeverComputer.showAllClientComputer());
    }
    public void showTotalCash(){
        System.out.println("Tổng doang thu hiện tại: "+controllerSeverComputer.showTotalCash());
    }
    public void withDrawMoney(){
        System.out.println("Đã thực rút số tiền "+controllerSeverComputer.widthDrawMoney()+" lúc "+ LocalDateTime.now());
    }
    public void personalPage() throws IOException {
        System.out.println("Hello admin");
        boolean check = true;
        while (check){
            System.out.println("1. Show thông tin máy chủ");
            System.out.println("2. Đổi tên tài khoản");
            System.out.println("3. Đổi mật khẩu");
            System.out.println("0. Thoát");
            int choice = number.nextInt();
            switch (choice){
                case 1: showSeverComputer(); break;
                case 2: changeUserName(); break;
                case 3: changePassword(); break;
                case 0: check = false;
            }
        }
    }
    public void addClientComputer(){
        System.out.println("Nhập vào số máy muốn thêm");
        int n = number.nextInt();
        n = n + controllerSeverComputer.getClientComputers().size();
        for (int i = controllerSeverComputer.getClientComputers().size(); i < n; i++){
            ClientComputer clientComputer = new ClientComputer(i);
            if (controllerSeverComputer.addClientComputer(clientComputer)){
                System.out.println("Thêm thành công máy "+clientComputer.getCode());
            }
            else System.out.println("Trùng id");
        }
    }
    public void delClientComputer(){
        System.out.println("Nhập vào id máy");
        String code = line.nextLine();
        if (controllerSeverComputer.delClientComputer(code)){
            System.out.println("Xóa máy trạm thành công");
        }
        else System.out.println("Không có máy này");
    }
    public void showSeverComputer(){
        System.out.println(controllerSeverComputer.showSeverComputer());
    }
    public void changeUserName() throws IOException {
        System.out.println("Nhập tên đăng nhập mới");
        String userName = line.nextLine();
        if (controllerSeverComputer.changeUserName(userName)){
            System.out.println("Đổi tên tài khoản thành công");
        }
        else System.out.println("Tên tài khoản đã tồn tại");
    }
    public void changePassword(){
        System.out.println("Nhập vào tên tài khoản");
        String userName = line.nextLine();
        System.out.println("Nhập vào mật khẩu hiện tại");
        String passWord = line.nextLine();
        System.out.println("Nhập vào mật khẩu mới");
        String newPassWord = line.nextLine();
        if (controllerSeverComputer.changePassword(userName, passWord, newPassWord)){
            System.out.println("Đổi mật khẩu thành công");
        }
        else System.out.println("Đổi mật khẩu không thành công");
    }
}
