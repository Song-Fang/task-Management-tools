package com.songfang.taskmtool.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UsernameAlreadyExsitException extends RuntimeException{

    public UsernameAlreadyExsitException(String message) {
        super(message);
    }
}
