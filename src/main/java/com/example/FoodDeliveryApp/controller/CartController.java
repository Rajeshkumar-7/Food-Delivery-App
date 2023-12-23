package com.example.FoodDeliveryApp.controller;

import com.example.FoodDeliveryApp.dto.request.FoodRequest;
import com.example.FoodDeliveryApp.dto.response.CartResponse;
import com.example.FoodDeliveryApp.exception.CustomerNotFoundException;
import com.example.FoodDeliveryApp.exception.MenuItemNotFoundException;
import com.example.FoodDeliveryApp.exception.OutOfStockException;
import com.example.FoodDeliveryApp.exception.RestaurantNotOpenedException;
import com.example.FoodDeliveryApp.service.implementation.CartServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {

    final CartServiceImpl cartService;

    public CartController(CartServiceImpl cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add/food")
    public ResponseEntity addFoodItemToCart(@RequestBody FoodRequest foodRequest){
        try {
            CartResponse cartResponse = cartService.addFoodItemToCart(foodRequest);
            return new ResponseEntity<>(cartResponse , HttpStatus.ACCEPTED);
        }
        catch (CustomerNotFoundException | OutOfStockException | MenuItemNotFoundException |
               RestaurantNotOpenedException e ){
            return new ResponseEntity<>(e.getMessage() , HttpStatus.BAD_REQUEST);
        }
    }
}
