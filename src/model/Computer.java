package model;

import java.io.Serializable;

public abstract class Computer implements Serializable {
    private boolean status = false;
    private String code;

    public Computer(){}
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
