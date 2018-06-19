package com.tx.handler;

import com.tx.exception.DuplicateTransactionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(DuplicateTransactionException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorMessage vesselNotFoundException(HttpServletRequest request, Exception exception) {
        return getResponseFromException(request, exception);
    }


    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage globalException(HttpServletRequest request, Exception exception) {
        ErrorMessage message = getResponseFromException(request, exception);
        message.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        return message;
    }

    private ErrorMessage getResponseFromException(HttpServletRequest request, Exception e) {
        ErrorMessage msg = new ErrorMessage();
        msg.setMessage(e.getMessage());
        msg.setException(e.getClass().getName());
        msg.setPath(request.getRequestURI());
        msg.setTimestamp(System.currentTimeMillis());

        LOG.error("Exception occured for URL:{}", request.getRequestURI(), e);
        return msg;
    }
}