package model;

import java.io.Serializable;

public abstract class Computer implements Serializable {
    private boolean status = false;
    private String id;

    public Computer(){}
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void turnOn(){
        this.status = true;
    }
    public void turnOff(){
        this.status = false;
    }
}
