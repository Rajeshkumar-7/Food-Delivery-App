package com.example.FoodDeliveryApp.controller;

import com.example.FoodDeliveryApp.dto.request.MenuItemRequest;
import com.example.FoodDeliveryApp.dto.request.RestaurantRequest;
import com.example.FoodDeliveryApp.dto.response.MenuItemResponse;
import com.example.FoodDeliveryApp.dto.response.RestaurantResponse;
import com.example.FoodDeliveryApp.exception.RestaurantNotFoundException;
import com.example.FoodDeliveryApp.service.implementation.RestaurantServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity addMenuItemToRestaurant(@RequestBody MenuItemRequest menuItemRequest){
        try{
            RestaurantResponse restaurantResponse = restaurantService.addMenuItemToRestaurant(menuItemRequest);
            return new ResponseEntity<>(restaurantResponse , HttpStatus.ACCEPTED);
        }
        catch (RestaurantNotFoundException e){
            return new ResponseEntity<>(e.getMessage() , HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/menu")
    public ResponseEntity getMenuFromRestaurant(@RequestParam("id") int id){
        try{
            List<MenuItemResponse> menuItemRespons = restaurantService.getMenuFromRestaurant(id);
            return new ResponseEntity(menuItemRespons, HttpStatus.ACCEPTED);
        }
        catch (RestaurantNotFoundException e){
            return new ResponseEntity<>(e.getMessage() , HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/more-than-x-orders")
    public ResponseEntity getRestaurantWithMoreThanXOrders(@RequestParam("x") int x){
        List<RestaurantResponse> restaurantResponses = restaurantService.getRestaurantWithMoreThanXOrders(x);
        return new ResponseEntity<>(restaurantResponses , HttpStatus.FOUND);
    }
}
