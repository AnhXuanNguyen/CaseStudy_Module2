package controller;


public interface Login {
    boolean login(String userName, String passWord);
    boolean changePassword(String useName, String passWord, String newPassWord);
}
