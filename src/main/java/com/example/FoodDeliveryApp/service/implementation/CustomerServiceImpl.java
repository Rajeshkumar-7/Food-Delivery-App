package com.example.FoodDeliveryApp.service.implementation;

import com.example.FoodDeliveryApp.Enum.Gender;
import com.example.FoodDeliveryApp.dto.request.CustomerRequest;
import com.example.FoodDeliveryApp.dto.response.CustomerResponse;
import com.example.FoodDeliveryApp.exception.CustomerNotFoundException;
import com.example.FoodDeliveryApp.model.Cart;
import com.example.FoodDeliveryApp.model.Customer;
import com.example.FoodDeliveryApp.repository.CustomerRepository;
import com.example.FoodDeliveryApp.service.CustomerService;
import com.example.FoodDeliveryApp.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        Customer customer = customerRepository.findByMobileNumber(mobileNumber);

        // If the customer is not found throw an Exception
        if(customer == null){
            throw new CustomerNotFoundException("Customer Not Found");
        }

        // Customer is Found and convert to customerResponse DTO
        return CustomerToCustomerResponse(customer);
    }

    @Override
    public CustomerResponse getCustomerWithMostOrders() {

        // Find the customer
        List<Customer> customers = customerRepository.findAll();
        int maxOrders = 0;
        Customer customer = null;
        for(Customer customer1 : customers){
            if(maxOrders < customer1.getOrders().size()){
                maxOrders = customer1.getOrders().size();
                customer = customer1;
            }
        }

        // Check if we have a customer or not
        if(customer == null){
            throw new CustomerNotFoundException("Customer is not found");
        }

        // Convert entity to DTO and return it
        return CustomerTransformer.CustomerToCustomerResponse(customer);
    }

    @Override
    public List<CustomerResponse> getCustomersByGender(Gender gender) {

        // Get all the Customers by gender
        List<Customer> customers = customerRepository.findByGender(gender);

        // Convert it to response DTO and return it
        return   customers
                .stream()
                .map(customer -> CustomerTransformer.CustomerToCustomerResponse(customer))
                .collect(Collectors.toList());

    }
}
