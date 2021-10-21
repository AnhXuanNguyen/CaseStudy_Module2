package model;

import java.io.Serializable;

public class Admin extends User implements Serializable {
    public Admin(){}
    public void widthDraw(){
        setMoney(0);
    }
}
