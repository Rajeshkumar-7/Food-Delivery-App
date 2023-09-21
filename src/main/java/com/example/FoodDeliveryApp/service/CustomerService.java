package com.example.FoodDeliveryApp.service;


import com.example.FoodDeliveryApp.dto.request.CustomerRequest;
import com.example.FoodDeliveryApp.dto.response.CustomerResponse;

public interface CustomerService {

    public CustomerResponse addCustomer(CustomerRequest customerRequest);
}
