package com.songfang.taskmtool.Exception;

public class InvalidLoginResponse {
    String username;
    String password;

    public InvalidLoginResponse() {
        this.username = "Invalid UserName";
        this.password = "Invalid PassWord";
    }

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
}
