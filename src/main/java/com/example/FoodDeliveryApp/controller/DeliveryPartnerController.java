package com.example.FoodDeliveryApp.controller;

import com.example.FoodDeliveryApp.dto.request.DeliveryPartnerRequest;
import com.example.FoodDeliveryApp.dto.response.DeliveryPartnerResponse;
import com.example.FoodDeliveryApp.exception.DeliveryPartnerNotFoundException;
import com.example.FoodDeliveryApp.service.implementation.DeliveryPartnerServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/deliveryPartner")
public class DeliveryPartnerController {

    final DeliveryPartnerServiceImpl deliveryPartnerService;

    public DeliveryPartnerController(DeliveryPartnerServiceImpl deliveryPartnerService) {
        this.deliveryPartnerService = deliveryPartnerService;
    }


    @PostMapping("/add")
    public ResponseEntity addDeliveryPartner(@RequestBody DeliveryPartnerRequest deliveryPartnerRequest){

        try{
            DeliveryPartnerResponse response = deliveryPartnerService.addDeliveryPartner(deliveryPartnerRequest);
            return new ResponseEntity<>(response , HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>("Registration for the Delivery Partner was unsuccessful !!" , HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/update/availability/mobile/{mobileNo}")
    public ResponseEntity updateAvailability(@PathVariable("mobileNo") String mobileNumber){
        try{
            String response = deliveryPartnerService.updateAvailability(mobileNumber);
            return new ResponseEntity<>(response , HttpStatus.ACCEPTED);
        }
        catch (DeliveryPartnerNotFoundException e){
            return new ResponseEntity<>(e.getMessage() , HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/find/deliveryPartner/most-deliveries")
    public ResponseEntity getDeliveryPartnerWithMostDeliveries(){
        try{
            DeliveryPartnerResponse deliveryPartnerResponse = deliveryPartnerService.getDeliveryPartnerWithMostDeliveries();
            return new ResponseEntity<>(deliveryPartnerResponse , HttpStatus.FOUND);
        }
        catch (DeliveryPartnerNotFoundException e){
            return new ResponseEntity<>(e.getMessage() , HttpStatus.NOT_FOUND);
        }
    }
}
