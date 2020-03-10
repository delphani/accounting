package com.example.demo.model;

public class MessageModel {
    private boolean hasError;
    private String message;

    public MessageModel(boolean hasError, String message) {
        this.hasError = hasError;
        this.message = message;
    }
    public boolean isHasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
