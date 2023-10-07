package com.example.FoodDeliveryApp.controller;

import com.example.FoodDeliveryApp.dto.request.DeliveryPartnerRequest;
import com.example.FoodDeliveryApp.service.implementation.DeliveryPartnerServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/deliveryPartner")
public class DeliveryPartnerController {

    final DeliveryPartnerServiceImpl deliveryPartnerService;

    public DeliveryPartnerController(DeliveryPartnerServiceImpl deliveryPartnerService) {
        this.deliveryPartnerService = deliveryPartnerService;
    }


    public ResponseEntity addDeliveryPartner(@RequestBody DeliveryPartnerRequest deliveryPartnerRequest){

        try{
            String response = deliveryPartnerService.addDeliveryPartner(deliveryPartnerRequest);
            return new ResponseEntity<>(response , HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity<>("Registration for the Delivery Partner was unsuccessful !!" , HttpStatus.BAD_REQUEST);
        }

    }


}
