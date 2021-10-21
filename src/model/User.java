package model;

import java.io.Serializable;

public abstract class User implements LoginLogout, Serializable {
    private String userName;
    private String password = "1";
    private int money;

    public User(){}
    public User(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }
    public void setMoney(int cash){
        money = cash;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMoney() {
        return money;
    }

    public void addMoney(long money) {
        this.money += money;
    }
    public void setMoney(){
        money = 0;
    }

    @Override
    public boolean login(String userName, String password) {
        return this.userName.equals(userName) && this.password.equals(password);
    }
    @Override
    public void logout() {
    }

    @Override
    public String toString() {
        return "username: "+userName+"| Số tiền trong tài khoản: "+money;
    }
}
