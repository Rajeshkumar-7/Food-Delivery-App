package com.example.FoodDeliveryApp.controller;

import com.example.FoodDeliveryApp.dto.request.FoodItemRequest;
import com.example.FoodDeliveryApp.dto.request.RestaurantRequest;
import com.example.FoodDeliveryApp.dto.response.RestaurantResponse;
import com.example.FoodDeliveryApp.service.implementation.RestaurantServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    final RestaurantServiceImpl restaurantService;

    public RestaurantController(RestaurantServiceImpl restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping("/add")
    public ResponseEntity addRestaurant(@RequestBody RestaurantRequest restaurantRequest){

        RestaurantResponse restaurantResponse = restaurantService.addRestaurant(restaurantRequest);
        return new ResponseEntity<>(restaurantResponse , HttpStatus.CREATED);

    }

    @PutMapping("/update/status")
    public ResponseEntity changeOpenedStatus(@RequestParam("id") int id){
        String message = restaurantService.changeOpenedStatus(id);
        return new ResponseEntity<>(message , HttpStatus.ACCEPTED);
    }

    @PostMapping("/add/food")
    public ResponseEntity addFoodItemToRestaurant(@RequestBody FoodItemRequest foodItemRequest){
        RestaurantResponse restaurantResponse = restaurantService.addFoodItemToRestaurant(foodItemRequest);
        return new ResponseEntity<>(restaurantResponse , HttpStatus.ACCEPTED);
    }
}
