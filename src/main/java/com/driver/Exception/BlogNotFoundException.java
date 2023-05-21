package com.driver.Exception;

public class BlogNotFoundException extends RuntimeException{

    public BlogNotFoundException(String message){
        super(message);
    }
}

