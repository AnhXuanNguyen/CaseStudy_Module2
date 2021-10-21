package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ClientComputer extends Computer implements Serializable {
    private String id = "MÁY";
    private LocalDateTime openTime;
    private Customer customer;
    public ClientComputer() {
    }

    public ClientComputer(int id) {
        this.id = this.id+id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getOpenTime() {
        return openTime;
    }

    public void setOpenTime(LocalDateTime openTime) {
        this.openTime = openTime;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public void open() {
        setStatus(true);
    }

    @Override
    public void close() {
        setStatus(false);
    }

    @Override
    public String toString() {
        return (!isStatus()?id+": Disnapble":id+": "+ customer.toString()+"| Giờ mở máy: "+openTime);
    }
}