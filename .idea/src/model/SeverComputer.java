package model;

import java.io.Serializable;

public class SeverComputer extends Computer implements Serializable {
    private String id = "MÁY CHỦ";
    public SeverComputer() {
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void open() {
        super.setStatus(true);
    }
    @Override
    public void close() {
        super.setStatus(false);
    }

    @Override
    public String toString() {
        return "1."+(!super.isStatus()?id+": Disnable":id+": Active");
    }
}
