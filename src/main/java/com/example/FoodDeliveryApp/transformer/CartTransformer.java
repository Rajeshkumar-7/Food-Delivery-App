package com.example.FoodDeliveryApp.transformer;

import com.example.FoodDeliveryApp.dto.response.CartResponse;
import com.example.FoodDeliveryApp.dto.response.FoodItemResponse;
import com.example.FoodDeliveryApp.model.Cart;
import com.example.FoodDeliveryApp.model.FoodItem;

import java.util.ArrayList;
import java.util.List;

import static com.example.FoodDeliveryApp.transformer.FoodItemTransformer.FoodItemToFoodResponse;

public class CartTransformer {

    public static CartResponse CartToCartResponse(Cart cart){

        CartResponse cartResponse = CartResponse.builder()
                .cartTotal(cart.getCartTotal())
                .build();

        List<FoodItemResponse> foodItemResponse = new ArrayList<>();
        for(FoodItem foodItem : cart.getFoodItems()){
            foodItemResponse.add(FoodItemToFoodResponse(foodItem));
        }

        cartResponse.setFoodItems(foodItemResponse);

        return cartResponse;
    }
}
