package model;

import java.io.Serializable;

public class Admin extends User implements Serializable {
    public Admin() {
    }

    public Admin(String userName) {
        super(userName);
    }
}
