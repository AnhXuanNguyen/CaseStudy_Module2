package view;

import controller.ControllerSeverManager;
import model.ClientComputer;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SeverComputerView {
    private final ControllerSeverManager CONTROLLER_SEVER_MANAGER = new ControllerSeverManager();
    private final Scanner NUMBER = new Scanner(System.in);
    private final Scanner LINE = new Scanner(System.in);

    public SeverComputerView() throws IOException, ClassNotFoundException, InputMismatchException {
    }

    public void login() throws IOException {
        System.out.println("Nhập admin account");
        String userName = LINE.nextLine();
        System.out.println("Nhập password");
        String password = LINE.nextLine();
        if (CONTROLLER_SEVER_MANAGER.adminLogin(userName, password)){
            homePage();
        }
        else {
            System.out.println("Sai thông tin đăng nhập");
        }
    }
    public void homePage() throws IOException , InputMismatchException{
        boolean checkLoop = true;
        while (checkLoop){
            System.out.println("1.Tạo tài khoản mới");
            System.out.println("2.Nạp tiền vào tài khoản");
            System.out.println("3.Đổi mật khẩu tài khoản khách");
            System.out.println("4.Xóa tài khoản");
            System.out.println("5.Show toàn bộ khách hàng");
            System.out.println("6.Show toàn bộ máy trạm");
            System.out.println("7.Trang cá nhân");
            System.out.println("8.Đăng xuât");
            System.out.println("0.Thoát");
            int choice = NUMBER.nextInt();
            switch (choice){
                case 1: registerAccountCustomer(); break;
                case 2: depositAccountCustomer(); break;
                case 3: changePasswordAccoutCustomer(); break;
                case 4: delAccountCustomer(); break;
                case 5: showAllAccoutCustomer(); break;
                case 6: showAllClientComputer(); break;
                case 7: peronalPage(); break;
                case 8: checkLoop = false; logOut(); break;
                case 0: checkLoop = false;break;
                default:
                    System.out.println("Không có lựa chọn này"); break;
            }
        }
    }
    public void registerAccountCustomer() throws IOException, InputMismatchException {
        AccountExample accountExample = new AccountExample();
        boolean checkLoop = true;
        while (checkLoop){
            System.out.println("Nhập tài khoản mới");
            String userName = LINE.nextLine();
            if (accountExample.validate(userName)){
                if (CONTROLLER_SEVER_MANAGER.registerAccoutCustomer(userName)){
                    System.out.println("Tạo tài khoản thành công");
                    checkLoop = false;
                }
                else {
                    System.out.println("Tài khoản đã tồn tại");
                }
            }
            else System.out.println("Tên tài khoản không chứa ký tự đặc biệt");
        }
    }
    public void depositAccountCustomer() throws IOException , InputMismatchException{
        System.out.println("Nhập username khách hàng");
        String userName = LINE.nextLine();
        System.out.println("Nhập số tiền cần nạp");
        int cash = NUMBER.nextInt();
        if (CONTROLLER_SEVER_MANAGER.depositAccoutCustomer(userName, cash)){
            System.out.println("Nạp tiền thành công");
        }
        else {
            System.out.println("Nạp tiền không thành công");
        }
    }
    public void changePasswordAccoutCustomer() throws IOException , InputMismatchException{
        System.out.println("Nhập username");
        String userName = LINE.nextLine();
        System.out.println("Nhập mật khẩu mới");
        String newPassword = LINE.nextLine();
        if (CONTROLLER_SEVER_MANAGER.changePasswordAccountCustomer(userName, newPassword)){
            System.out.println("Đổi mật khẩu thành công");
        }
        else {
            System.out.println("User không tồn tại");
        }
    }
    public void delAccountCustomer() throws IOException, InputMismatchException {
        System.out.println("Nhập tài khoản muốn xóa");
        String userName = LINE.nextLine();
        if (CONTROLLER_SEVER_MANAGER.delAccoutCustomer(userName)){
            System.out.println("Đã xóa "+userName);
        }
        else {
            System.out.println("Tài khoản không tồn tại");
        }
    }
    public void showAllAccoutCustomer() throws InputMismatchException{
        if (CONTROLLER_SEVER_MANAGER.customerSize() == 0){
            System.out.println("Chưa có khách hàng nào");
        }
        else {
            System.out.println(CONTROLLER_SEVER_MANAGER.showAllAccoutnCustomer());
        }
    }
    public void showAllClientComputer() throws InputMismatchException{
        if (CONTROLLER_SEVER_MANAGER.customerSize() == 0){
            System.out.println("Quán mới, chưa có tiền mua máy");
        }
        else {
            System.out.println(CONTROLLER_SEVER_MANAGER.showAllClientComputer());
        }
    }
    public void peronalPage() throws IOException, InputMismatchException {
        boolean checkLoop = true;
        while (checkLoop){
            System.out.println("Hello Admin");
            System.out.println(CONTROLLER_SEVER_MANAGER.showAdminAndSeverComputer());
            System.out.println("1.Đổi tài khoản");
            System.out.println("2.Đổi mật khẩu");
            System.out.println("3.Thêm máy trạm");
            System.out.println("4.Bàn giao ca");
            System.out.println("5.Xóa máy trạm");
            System.out.println("0.Thoát");
            int choice = NUMBER.nextInt();
            switch (choice){
                case 1: changeAccoutAdmin(); break;
                case 2: changePasswordAdmin(); break;
                case 3: addClientcomputer(); break;
                case 4: withDrawMoney(); break;
                case 5: delClientComputer(); break;
                case 0: checkLoop = false; break;
                default:
                    System.out.println("Không có lựa chọn này"); break;
            }
        }
    }
    public void changeAccoutAdmin() throws IOException, InputMismatchException {
        System.out.println("Nhập tài khoản mới");
        String userName = LINE.nextLine();
        if (CONTROLLER_SEVER_MANAGER.changeUserNameAccountAdmin(userName)){
            System.out.println("Đổi tài khoản thành công");
        }
        else {
            System.out.println("Tài khoản đã tồn tại");
        }
    }
    public void changePasswordAdmin() throws IOException, InputMismatchException {
        System.out.println("Nhập username");
        String userName = LINE.nextLine();
        System.out.println("Nhập password");
        String password = LINE.nextLine();
        System.out.println("Nhập mật khẩu mới");
        String newPassword = LINE.nextLine();
        if (CONTROLLER_SEVER_MANAGER.changePasswordAccoutAdmin(userName, password, newPassword)){
            System.out.println("Đổi mật khẩu thành công");
        }
        else {
            System.out.println("Đổi mật khẩu không thành công");
        }
    }
    public void addClientcomputer() throws IOException, InputMismatchException {
        System.out.println("Nhập số máy trạm muốn thêm");
        int numberAddClientComputer = NUMBER.nextInt();
        numberAddClientComputer += CONTROLLER_SEVER_MANAGER.clientComputerSize();
        for (int i = CONTROLLER_SEVER_MANAGER.clientComputerSize(); i < numberAddClientComputer; i++){
            ClientComputer clientComputer = new ClientComputer(i);
            if (CONTROLLER_SEVER_MANAGER.createClientComputer(clientComputer)){
                System.out.println("Lắp đặt thành công MÁY"+i);
            }
            else {
                System.out.println("Đã có máy này");
            }
        }
    }
    public void withDrawMoney() throws IOException, InputMismatchException {
        CONTROLLER_SEVER_MANAGER.withDraw();
    }
    public void delClientComputer() throws IOException {
        System.out.println(CONTROLLER_SEVER_MANAGER.showAllClientComputer());
        System.out.println("Chọn máy muốn xóa");
        int choice = NUMBER.nextInt();
        if (CONTROLLER_SEVER_MANAGER.delClientComputer(choice)){
            System.out.println("Xóa thành công MÁY"+choice);
        }
        else {
            System.out.println("Không có máy này");
        }
    }
    public void logOut() throws IOException {
        CONTROLLER_SEVER_MANAGER.logOutAdmin();
    }
    public boolean isSeverComputer(){
        return CONTROLLER_SEVER_MANAGER.isSeverComputer();
    }
}
