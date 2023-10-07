package com.example.FoodDeliveryApp.exception;

public class MenuItemNotFoundException extends RuntimeException{

    public MenuItemNotFoundException(String message){
        super(message);
    }
}
