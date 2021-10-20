package model;

import java.io.Serializable;

public class Admin extends User implements Serializable {
    private long moneyInPocket = 0;
    public Admin(){}
    public Admin(String admin, String password){
        super(admin, password);
    }
    public long getMoneyInPocket() {
        return moneyInPocket;
    }

    public void setMoneyInPocket(long moneyInPocket) {
        this.moneyInPocket += moneyInPocket;
    }
    public long withDrawMoney(){
        long a = moneyInPocket;
        moneyInPocket = 0;
        return a;
    }
    public String toString(){
        return "Username: "+super.getUserName()+ " | Tiền: "+moneyInPocket;
    }
}
