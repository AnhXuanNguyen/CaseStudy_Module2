package view;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            SeverComputerView severComputerView =  new SeverComputerView();
            ClientComputerView clientComputerView = new ClientComputerView();
            boolean checkLoop = true;
            while (checkLoop){
                System.out.println("1. Máy chủ");
                System.out.println("2. Máy khách");
                System.out.println("0. Exit");
                int choice = scanner.nextInt();
                switch (choice){
                    case 1:
                        if (severComputerView.isSeverComputer()) {
                                severComputerView.homePage();
                        } else {
                            severComputerView.login();
                        }
                        break;
                    case 2:{
                        if (severComputerView.isSeverComputer()){
                            clientComputerView.showAllClientComputer();
                        }
                        else System.out.println("Chưa bật máy chủ");
                    }break;
                    case 0: checkLoop = false;break;
                    default:
                        System.out.println("Không có lựa chọn này");break;
                }
            }
        }catch (IOException | ClassNotFoundException | InputMismatchException e) {
            System.out.println("Sai định dạng nhập vào");
        }
    }
}
