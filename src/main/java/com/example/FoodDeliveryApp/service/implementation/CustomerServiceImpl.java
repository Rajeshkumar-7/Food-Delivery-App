package com.example.FoodDeliveryApp.service.implementation;

import com.example.FoodDeliveryApp.dto.request.CustomerRequest;
import com.example.FoodDeliveryApp.dto.response.CustomerResponse;
import com.example.FoodDeliveryApp.model.Cart;
import com.example.FoodDeliveryApp.model.Customer;
import com.example.FoodDeliveryApp.service.CustomerService;
import org.springframework.stereotype.Service;
import static com.example.FoodDeliveryApp.transformer.CustomerTransformer.CustomerRequestToCustomer;
import static com.example.FoodDeliveryApp.transformer.CustomerTransformer.CustomerToCustomerResponse;

@Service
public class CustomerServiceImpl implements CustomerService {


    @Override
    public CustomerResponse addCustomer(CustomerRequest customerRequest) {

        // Convert DTO --> Entity
        Customer customer = CustomerRequestToCustomer(customerRequest);

        // Create a Cart Entity
        Cart cart = new Cart();
        cart.setCartTotal(0);
        cart.setCustomer(customer);

        // Set the customer in cart
        customer.setCart(cart);

        // Convert Entity --> DTO
        return   CustomerToCustomerResponse(customer);
    }
}
