package com.example.feelgoodinc.models;

import java.util.Date;

/**
 * This Model contains general information about the user
 */
public class User {
    private String username;
    private String password; //TODO: needs to be encrypted somehow
    private Date lastLoginWhen;

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

    public Date getLastLoginWhen() {
        return lastLoginWhen;
    }

    public void setLastLoginWhen(Date lastLoginWhen) {
        this.lastLoginWhen = lastLoginWhen;
    }
}
