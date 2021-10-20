package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Customer extends User implements Serializable {
    private long depositMoney;
    private LocalDateTime loginDay;
    public Customer(String userName, String passWord) {
        super(userName, passWord);
    }

    public long getDepositMoney() {
        return depositMoney;
    }

    public LocalDateTime getLoginDay() {
        return loginDay;
    }

    public void setLoginDay(LocalDateTime loginDay) {
        this.loginDay = loginDay;
    }

    public void setDepositMoney(long depositMoney) {
        this.depositMoney = depositMoney;
    }
    public String toString(){
        return "Tên đăng nhập: "+getUserName()+"| Tiền trong tài khoản: "+depositMoney+"| Lần đăng nhập cuối cùng: "+loginDay;
    }
}
