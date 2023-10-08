package org.one.alura_hotel_g5.model;

import org.one.alura_hotel_g5.security.Security;

public class Usuarios {
    private Integer id;
    private String user;
    private String password;
    private boolean isAdmin;
    private boolean isActive;

    public Usuarios(String user, String password, boolean isAdmin, boolean isActive) {
        var psswrd = Security.getSHA256SecurePassword(password);
        this.user = user;
        this.password = psswrd;
        this.isAdmin = isAdmin;
        this.isActive = isActive;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public boolean isActive() {
        return isActive;
    }
}
