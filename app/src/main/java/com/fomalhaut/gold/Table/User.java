package com.fomalhaut.gold.Table;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

public class User extends LitePalSupport {
    @Column(unique = true)
    private String username;
    private String password;
    private String phone;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
