package com.example.luv.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class BusinessException extends RuntimeException{
    HttpStatus status;
    public BusinessException(HttpStatus status,String message) {

        super(message);
        this.status = status;
    }
}
