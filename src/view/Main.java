package view;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SeverComputerView severComputerView = null;
        ClientComputerView clientComputerView = null;
        try {
             clientComputerView = new ClientComputerView();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            severComputerView = new SeverComputerView();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        boolean check = true;
        while (check){
            System.out.println("1.Máy chủ");
            System.out.println("2.Máy khách");
            System.out.println("0.Exit");
            int choice = scanner.nextInt();
            switch (choice){
                case 1:
                    try {
                        severComputerView.login();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    try {
                        clientComputerView.showClientComputer();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                case 0: check = false;
            }
        }
    }
}
