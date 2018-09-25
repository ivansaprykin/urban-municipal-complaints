package com.tsn.util;


public class UserDetails {

    private final int userId;
    private final String userLogin;
    private final String userRole;
    private final String userName;

    public UserDetails(int userId, String userLogin, String userRole, String userName) {
        this.userId = userId;
        this.userLogin = userLogin;
        this.userRole = userRole;
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public String getUserRole() {
        return userRole;
    }

    public String getUserName() {
        return userName;
    }
}