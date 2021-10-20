package model;

import java.io.Serializable;

public class SeverComputer extends Computer implements ComputerCando, Serializable {
    private static final String ID = "MÁY CHỦ";
    public SeverComputer() {
        super.setCode(ID);
    }
    @Override
    public void open() {
        setStatus(true);
    }
    @Override
    public void close() {
        setStatus(false);
    }
    public String toString(){
        return ID;
    }
}
