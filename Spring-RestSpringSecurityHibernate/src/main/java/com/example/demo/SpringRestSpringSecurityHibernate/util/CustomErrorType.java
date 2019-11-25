package com.example.demo.SpringRestSpringSecurityHibernate.util;

import org.springframework.http.HttpStatus;

public class CustomErrorType {

    private String errorMessage;

    public CustomErrorType(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
