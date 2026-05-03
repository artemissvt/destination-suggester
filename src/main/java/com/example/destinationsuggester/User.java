package com.example.destinationsuggester;

public class User {

    private String username;
    private String user_password;
    private String user_id;

    // getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return user_password;
    }


    public String getUserId() {
        return user_id;
    }

    // setters
    public void setUsername(String username) {
        this.username = username;
    }


    public void setPassword(String user_password) {
        this.user_password = user_password;
    }


    public void setUserId(String user_id) {
        this.user_id = user_id;
    }
}