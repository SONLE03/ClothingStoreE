package com.sa.clothingstore.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ObjectAlreadyExistsException extends RuntimeException{
    public ObjectAlreadyExistsException(String message){
        super(message);
    }
}
