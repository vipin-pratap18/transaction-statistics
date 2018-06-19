package com.tx.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.CONFLICT, reason="Duplicate Transaction")
public class DuplicateTransactionException extends RuntimeException{

    private static final long serialVersionUID = -674505462563241978L;
    private static final String DEFAULT_MESSAGE = "Duplicate Transaction";

    public DuplicateTransactionException(String message){
        super(message);
    }

    public DuplicateTransactionException(){
        super(DEFAULT_MESSAGE);
    }

    public DuplicateTransactionException(Throwable cause){
        super(cause);
    }
}
