package view;

import controller.ControllerClientComputer;

import java.io.IOException;
import java.util.Scanner;

public class ClientComputerView {
    private ControllerClientComputer controllerClientComputer = new ControllerClientComputer();

    public ClientComputerView() throws IOException, ClassNotFoundException {
    }

    public void showAllClientComputer() throws IOException {
    Scanner number = new Scanner(System.in);
    Scanner line = new Scanner(System.in);
        System.out.println(controllerClientComputer.showAllClientComputer());
        System.out.println("Chọn máy");
        int choice = number.nextInt();
        if (controllerClientComputer.searchClientComputer(choice).isStatus()){
            System.out.println("Nhập username");
            String userName = line.nextLine();
            if (userName.equals(controllerClientComputer.searchClientComputer(choice).getCustomer().getUserName())){
                homePage(choice, userName);
            }
            else System.out.println("vui lòng chọn máy khác");
        }
        else {
            login(choice);
        }
    }
    public void login(int index) throws IOException {
        Scanner number = new Scanner(System.in);
        Scanner line = new Scanner(System.in);
        System.out.println("Nhập username");
        String userName = line.nextLine();
        System.out.println("Nhập password");
        String pasword = line.nextLine();
        if (controllerClientComputer.login(index, userName, pasword)){
            homePage(index, userName);
        }
    }
    public void homePage(int index, String userName) throws IOException {
        Scanner number = new Scanner(System.in);
        Scanner line = new Scanner(System.in);
        boolean checkLoop = true;
        while (checkLoop){
            System.out.println("Hello "+userName);
            System.out.println("1. Show thông tin người chơi");
            System.out.println("2. Đổi mật khẩu");
            System.out.println("3. Đăng xuất");
            System.out.println("0. Thoát");
            int choice = number.nextInt();
            switch (choice){
                case 1: showClientComputer(index); break;
                case 2: changePasswordCustomerAccout(userName); break;
                case 3: checkLoop = false; logOut(index, userName); break;
                case 0: checkLoop = false;break;
                default:
                    System.out.println("Không có lựa chọn này");break;
            }
        }
    }
    public void showClientComputer(int index){
        Scanner number = new Scanner(System.in);
        Scanner line = new Scanner(System.in);
        System.out.println(controllerClientComputer.showSevic(index));
    }
    public void changePasswordCustomerAccout(String userName) throws IOException {
        Scanner number = new Scanner(System.in);
        Scanner line = new Scanner(System.in);
        System.out.println("Nhập mật khẩu hiện tại");
        String password = line.nextLine();
        System.out.println("Nhập mật khẩu mới");
        String newPassword = line.nextLine();
        if (controllerClientComputer.changePassword(userName, password, newPassword)){
            System.out.println("Đổi mật khẩu thành công");
        }
        else System.out.println("Đổi mật khẩu không thành công");
    }
    public void logOut(int index, String userName) throws IOException {
        controllerClientComputer.close(index, userName);
        System.out.println("Đăng xuất thành công");
    }
}
