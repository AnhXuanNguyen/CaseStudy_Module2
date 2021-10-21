package view;

import controller.ControllerClientComputer;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ClientComputerView {
    private final ControllerClientComputer CONTROLLER_CLIENT_COMPUTER = new ControllerClientComputer();
    private final Scanner NUMBER = new Scanner(System.in);
    private final Scanner LINE = new Scanner(System.in);

    public ClientComputerView() throws IOException, ClassNotFoundException, InputMismatchException {
    }

    public void showAllClientComputer() throws IOException {
        if (CONTROLLER_CLIENT_COMPUTER.getClientComputerManager().getClientComputers().size() == 0){
            System.out.println("Không có máy");
        }
        else {
            Scanner number = new Scanner(System.in);
            Scanner line = new Scanner(System.in);
            System.out.println(CONTROLLER_CLIENT_COMPUTER.showAllClientComputer());
            System.out.println("Chọn máy");
            int choice = number.nextInt();
            if (CONTROLLER_CLIENT_COMPUTER.searchClientComputer(choice).isStatus()){
                System.out.println("Nhập username");
                String userName = line.nextLine();
                if (userName.equals(CONTROLLER_CLIENT_COMPUTER.searchClientComputer(choice).getCustomer().getUserName())){
                    homePage(choice, userName);
                }
                else System.out.println("vui lòng chọn máy khác");
            }
            else {
                login(choice);
            }
        }
    }
    public void login(int index) throws IOException , InputMismatchException{
        System.out.println("Nhập username");
        String userName = LINE.nextLine();
        System.out.println("Nhập password");
        String pasword = LINE.nextLine();
        if (CONTROLLER_CLIENT_COMPUTER.login(index, userName, pasword)){
            homePage(index, userName);
        }
    }
    public void homePage(int index, String userName) throws IOException, InputMismatchException {
        boolean checkLoop = true;
        while (checkLoop){
            System.out.println("Hello "+userName);
            System.out.println("1. Show thông tin người chơi");
            System.out.println("2. Đổi mật khẩu");
            System.out.println("3. Đăng xuất");
            System.out.println("0. Thoát");
            int choice = NUMBER.nextInt();
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
    public void showClientComputer(int index) throws InputMismatchException{
        System.out.println(CONTROLLER_CLIENT_COMPUTER.showInfoClientComputer(index));
    }
    public void changePasswordCustomerAccout(String userName) throws IOException, InputMismatchException {
        System.out.println("Nhập mật khẩu hiện tại");
        String password = LINE.nextLine();
        System.out.println("Nhập mật khẩu mới");
        String newPassword = LINE.nextLine();
        if (CONTROLLER_CLIENT_COMPUTER.changePassword(userName, password, newPassword)){
            System.out.println("Đổi mật khẩu thành công");
        }
        else System.out.println("Đổi mật khẩu không thành công");
    }
    public void logOut(int index, String userName) throws IOException {
        CONTROLLER_CLIENT_COMPUTER.close(index, userName);
        System.out.println("Đăng xuất thành công");
    }
}
