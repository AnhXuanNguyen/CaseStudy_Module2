package view;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        SeverComputerView severComputerView = null;
        try {
            severComputerView = new SeverComputerView();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ClientComputerView clientComputerView = null;
        try {
            clientComputerView = new ClientComputerView();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        boolean checkLoop = true;
        while (checkLoop){
            System.out.println("1. Máy chủ");
            System.out.println("2. Máy khách");
            System.out.println("0. Exit");
            int choice = scanner.nextInt();
            switch (choice){
                case 1:
                    if (severComputerView.isSeverComputer()) {
                        try {
                            severComputerView.homePage();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
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
    }
}
