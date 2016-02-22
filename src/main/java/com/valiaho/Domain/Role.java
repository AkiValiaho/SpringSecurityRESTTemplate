package com.valiaho.Domain;

/**
 * Created by akivv on 16.2.2016.
 */
public enum Role {
    ADMIN,
    MODERATOR,
    USER;
    public String getStringRepresentation(Role role) {
        return role.toString();
    }
}
