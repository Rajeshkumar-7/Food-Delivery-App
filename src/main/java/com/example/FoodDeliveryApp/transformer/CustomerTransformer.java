package com.example.FoodDeliveryApp.transformer;

import com.example.FoodDeliveryApp.dto.request.CustomerRequest;
import com.example.FoodDeliveryApp.dto.response.CustomerResponse;
import com.example.FoodDeliveryApp.model.Customer;
import com.example.FoodDeliveryApp.model.FoodItem;

import static com.example.FoodDeliveryApp.transformer.CartTransformer.CartToCartResponse;

public class CustomerTransformer {


    public static Customer CustomerRequestToCustomer(CustomerRequest customerRequest){

        return Customer.builder()
                .name(customerRequest.getName())
                .email(customerRequest.getEmail())
                .mobileNumber(customerRequest.getMobileNumber())
                .address(customerRequest.getAddress())
                .gender(customerRequest.getGender())
                .build();
    }

    public static CustomerResponse CustomerToCustomerResponse(Customer customer){

        CustomerResponse customerResponse =  CustomerResponse.builder()
                .name(customer.getName())
                .mobileNumber(customer.getMobileNumber())
                .address(customer.getAddress())
                .cartTotal(customer.getCart().getCartTotal())
                .build();

        for(FoodItem foodItem : customer.getCart().getFoodItems()){

        }
    }
}
