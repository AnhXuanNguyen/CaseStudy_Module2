package model;

import java.io.Serializable;

public abstract class Computer implements Serializable {
    private boolean status = false;
    public Computer(){}

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    abstract void open();
    abstract void close();
}
