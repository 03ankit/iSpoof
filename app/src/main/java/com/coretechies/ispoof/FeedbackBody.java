package com.coretechies.ispoof;


public class FeedbackBody {

    private final String token;
    private final String username;
    private final String phone_number;
    private final String callerid;
    private final String password;

    public FeedbackBody(String token, String username,String phone_number,String callerid,String password) {
        this.token = token;
        this.username = username;
        this.phone_number = phone_number;
        this.callerid = callerid;
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public String setToken() {
        return token;
    }

    public String getUsername() {
        return username;
    }

    public String setUsername() {
        return username;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String setPhone_number() {
        return phone_number;
    }
    public String getCallerid() {
        return callerid;
    }

    public String setCallerid() {
        return callerid;
    }
    public String getPassword() {
        return password;
    }
    public String setPassword() {
        return password;
    }
}

