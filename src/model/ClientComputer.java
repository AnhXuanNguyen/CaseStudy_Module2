package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ClientComputer extends Computer implements Serializable {
    private LocalDateTime openTime;

    public ClientComputer() {
    }

    public LocalDateTime getOpenTime() {
        return openTime;
    }

    public void setOpenTime(LocalDateTime openTime) {
        this.openTime = openTime;
    }
}