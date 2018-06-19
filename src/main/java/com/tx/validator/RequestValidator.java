package com.tx.validator;

import com.tx.model.Transaction;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class RequestValidator {

    public String validateTransaction(Transaction transaction) {
        long currentTimestamp = new Date().getTime();
        long lastSixtySecondsTimestamp = currentTimestamp - 60000;
        if(transaction.getTimestamp() < lastSixtySecondsTimestamp){
            return "Transaction timestamp is from past";
        }

        return  null;
    }
}
