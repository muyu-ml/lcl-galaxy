package com.lcl.galaxy.springmvc.exception;

import lombok.Data;

@Data
public class MyException extends Exception {

    private String message;

    public MyException(String msg){
        super(msg);
        this.message = msg;
    }
}
