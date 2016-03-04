package com.valiaho.Domain;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by akivv on 2.3.2016.
 */
@Entity
public class PersistedUser {
    private String password;
    @Id
    private String username;

    protected PersistedUser() {
    }

    public PersistedUser(String password, String username) {
        this.password = password;
        this.username = username;
    }
}
