package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Customer extends User implements Serializable {
    private LocalDateTime loginDay;
    public Customer(){}
    public Customer(String userName) {
        super(userName);
    }

    public LocalDateTime getLoginDay() {
        return loginDay;
    }

    public void setLoginDay(LocalDateTime loginDay) {
        this.loginDay = loginDay;
    }
}
