package com.example.FoodDeliveryApp.controller;

import com.example.FoodDeliveryApp.dto.response.OrderEntityResponse;
import com.example.FoodDeliveryApp.service.implementation.OrderEntityServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orderEntity")
public class OrderEntityController {

    final OrderEntityServiceImpl orderEntityService;

    public OrderEntityController(OrderEntityServiceImpl orderEntityService) {
        this.orderEntityService = orderEntityService;
    }


    @PostMapping("/placeOrder/mobileNo/{mobileNo}")
    public ResponseEntity placeOrder(@PathVariable("mobileNo") String customerMobileNumber){
        try {
            OrderEntityResponse orderEntityResponse = orderEntityService.placeOrder(customerMobileNumber);
            return new ResponseEntity<>(orderEntityResponse , HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage() , HttpStatus.BAD_REQUEST);
        }
    }
}
