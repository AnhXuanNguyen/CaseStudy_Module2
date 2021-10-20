package controller;


import java.io.IOException;

public interface Login {
    boolean login(String userName, String passWord);
    boolean changePassword(String useName, String passWord, String newPassWord) throws IOException;
}
