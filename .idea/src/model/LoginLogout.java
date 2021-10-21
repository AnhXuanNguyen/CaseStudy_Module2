package model;

import java.io.IOException;

public interface LoginLogout {
    boolean login(String user, String password) throws IOException;
    void logout() throws IOException;
}
