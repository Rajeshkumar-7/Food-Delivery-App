package com.example.FoodDeliveryApp.service;


import com.example.FoodDeliveryApp.Enum.Gender;
import com.example.FoodDeliveryApp.dto.request.CustomerRequest;
import com.example.FoodDeliveryApp.dto.response.CustomerResponse;

import java.util.List;

public interface CustomerService {

    public CustomerResponse addCustomer(CustomerRequest customerRequest);

    CustomerResponse getCustomerByMobile(String mobileNumber);

    CustomerResponse getCustomerWithMostOrders();

    List<CustomerResponse> getCustomersByGender(Gender gender);
}
