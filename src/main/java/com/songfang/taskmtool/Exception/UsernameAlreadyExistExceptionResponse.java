package com.songfang.taskmtool.Exception;

public class UsernameAlreadyExistExceptionResponse {
    private String usernameAlreadyExists;

    public UsernameAlreadyExistExceptionResponse(String usernameAlreadyExists) {
        this.usernameAlreadyExists = usernameAlreadyExists;
    }

    public String getUsernameAlreadyExists() {
        return usernameAlreadyExists;
    }

    public void setUsernameAlreadyExists(String usernameAlreadyExists) {
        this.usernameAlreadyExists = usernameAlreadyExists;
    }
}
