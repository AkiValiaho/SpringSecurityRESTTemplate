package com.valiaho.Domain;

/**
 * Created by akivv on 2.3.2016.
 */
public class PersistedUser {
    private String password;
    private String username;

    protected PersistedUser() {
    }

    public PersistedUser(String password, String username) {
        this.password = password;
        this.username = username;
    }
}
