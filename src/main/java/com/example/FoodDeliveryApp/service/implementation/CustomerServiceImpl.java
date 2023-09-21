package com.example.FoodDeliveryApp.service.implementation;

import com.example.FoodDeliveryApp.dto.request.CustomerRequest;
import com.example.FoodDeliveryApp.dto.response.CustomerResponse;
import com.example.FoodDeliveryApp.exception.CustomerNotFoundException;
import com.example.FoodDeliveryApp.model.Cart;
import com.example.FoodDeliveryApp.model.Customer;
import com.example.FoodDeliveryApp.repository.CustomerRepository;
import com.example.FoodDeliveryApp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static com.example.FoodDeliveryApp.transformer.CustomerTransformer.CustomerRequestToCustomer;
import static com.example.FoodDeliveryApp.transformer.CustomerTransformer.CustomerToCustomerResponse;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;


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

        // Save the Customer and the cart in DB
        Customer savedCustomer = customerRepository.save(customer);

        // Convert Entity --> DTO
        return   CustomerToCustomerResponse(savedCustomer);
    }

    @Override
    public CustomerResponse getCustomerByMobile(String mobileNumber) {

        // Find the Customer by Mobile Number
        Customer customer = customerRepository.findbyMobileNumber(mobileNumber);

        // If the customer is not found throw an Exception
        if(customer == null){
            throw new CustomerNotFoundException("Customer Not Found");
        }

        // Customer is Found and convert to customerResponse DTO
        return CustomerToCustomerResponse(customer);
    }
}
