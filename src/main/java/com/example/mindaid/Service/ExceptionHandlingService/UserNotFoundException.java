package com.example.mindaid.Service.ExceptionHandlingService;

public class UserNotFoundException extends Exception{
    String message="null";
    public UserNotFoundException(UserNotFoundException ex){
        ex.message="null";
    }
    public UserNotFoundException(String ex){
        this.message=ex;
        System.out.println(ex);
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
