package com.sa.clothingstore.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordException extends RuntimeException{

    public PasswordException(String message){
        super(message);
    }
}
