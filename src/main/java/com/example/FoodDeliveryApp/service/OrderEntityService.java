package com.example.FoodDeliveryApp.service;


import com.example.FoodDeliveryApp.dto.response.OrderEntityResponse;

public interface OrderEntityService {
    OrderEntityResponse placeOrder(String customerMobileNumber);
}
