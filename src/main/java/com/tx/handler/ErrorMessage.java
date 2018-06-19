package com.tx.handler;

import java.io.Serializable;

public class ErrorMessage implements Serializable{

    private static final long serialVersionUID = -8320506714966460235L;

    private long timestamp;
    private int status;
    private String message;
    private String error;
    private String exception;
    private String path;

    public ErrorMessage() {
    }

    public ErrorMessage(long timestamp, int status, String message, String error, String exception, String path) {
        super();
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.error = error;
        this.exception = exception;
        this.path = path;
    }

    public ErrorMessage(long timestamp, String message, String error) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.error = error;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return String.format("ErrorMessage [timestamp=%d, status=%d, message=%s, error=%s, exception=%s, path=%s]",
                timestamp, status, message, error, exception, path);
    }
}