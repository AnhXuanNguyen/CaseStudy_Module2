package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Customer extends User implements Serializable {
    private LocalDateTime lastLogin;
    public Customer() {
    }

    public Customer(String userName) {
        super(userName);
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    @Override
    public String toString() {
        return super.toString()+"| Lần đăng nhập cuối cùng: "+lastLogin;
    }
}
