package com.example.FoodDeliveryApp.controller;

import com.example.FoodDeliveryApp.Enum.Gender;
import com.example.FoodDeliveryApp.dto.request.CustomerRequest;
import com.example.FoodDeliveryApp.dto.response.CustomerResponse;
import com.example.FoodDeliveryApp.exception.CustomerNotFoundException;
import com.example.FoodDeliveryApp.service.implementation.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerServiceImpl customerService;

    @PostMapping("/add")
    public ResponseEntity addCustomer(@RequestBody CustomerRequest customerRequest){

        CustomerResponse customerResponse = customerService.addCustomer(customerRequest);
        return new ResponseEntity<>(customerResponse , HttpStatus.CREATED);

    }

    @GetMapping("/find/mobile/{mobile}")
    public ResponseEntity getCustomerByMobile(@PathVariable("mobile") String mobileNumber){

        try {
            CustomerResponse customerResponse = customerService.getCustomerByMobile(mobileNumber);
            return new ResponseEntity<>(customerResponse , HttpStatus.FOUND);
        }
        catch (CustomerNotFoundException e){
            return new ResponseEntity<>(e.getMessage() , HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/find/customer-with-most-orders")
    public ResponseEntity getCustomerWithMostOrders(){
        try {
            CustomerResponse customerResponse = customerService.getCustomerWithMostOrders();
            return new ResponseEntity<>(customerResponse , HttpStatus.FOUND);
        }
        catch (CustomerNotFoundException e){
            return new ResponseEntity<>(e.getMessage() , HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/find/customers/gender/{gender}")
    public ResponseEntity getCustomersByGender(@PathVariable("gender")Gender gender){
        List<CustomerResponse> customerResponses = customerService.getCustomersByGender(gender);
        return new ResponseEntity<>(customerResponses , HttpStatus.ACCEPTED);
    }

}
