package com.valiaho.Domain;

/**
 * Created by akivv on 18.2.2016.
 */
public class LoginStatus {
    private final String name;
    private boolean loginStatus;

    public String getX_AUTH_HEADER() {
        return X_AUTH_HEADER;
    }

    public void setX_AUTH_HEADER(String x_AUTH_HEADER) {
        X_AUTH_HEADER = x_AUTH_HEADER;
    }

    private String X_AUTH_HEADER;
    public String getName() {
        return name;
    }

    public boolean isLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(boolean loginStatus) {
        this.loginStatus = loginStatus;
    }

    public LoginStatus(boolean b, String name, String s) {
        this.loginStatus = b;
        this.name = name;
        this.X_AUTH_HEADER = s;
    }
}
