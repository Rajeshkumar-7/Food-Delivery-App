package com.example.FoodDeliveryApp.exception;

public class RestaurantNotOpenedException extends RuntimeException{
    public RestaurantNotOpenedException(String message){
        super(message);
    }
}
