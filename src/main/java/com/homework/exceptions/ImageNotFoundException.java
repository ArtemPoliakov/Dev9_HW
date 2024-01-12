package com.homework.exceptions;

public class ImageNotFoundException extends Exception {
    public ImageNotFoundException(){}
    public ImageNotFoundException(String msg){
        super(msg);
    }
}
