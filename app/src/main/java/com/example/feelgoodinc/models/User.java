package com.example.feelgoodinc.models;

import java.util.Date;

/**
 * This Model contains general information about the user
 */
public class User {
    private String username;
    private Date lastLoginWhen;
    private String currentUserKey;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getLastLoginWhen() {
        return lastLoginWhen;
    }

    public void setLastLoginWhen(Date lastLoginWhen) {
        this.lastLoginWhen = lastLoginWhen;
    }


    /***
     *
     * @return the documentID of the current user
     */
    public String getCurrentUserKey() {
        return currentUserKey;
    }

    public void setCurrentUserKey(String key) {
        currentUserKey = key;
    }


    //TODO: get the currently logged in user's user key (which should probably be static)
}
