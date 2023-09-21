package com.example.FoodDeliveryApp.transformer;

import com.example.FoodDeliveryApp.dto.response.FoodItemResponse;
import com.example.FoodDeliveryApp.model.FoodItem;

public class FoodItemTransformer {

    public static FoodItemResponse FoodItemToFoodResponse(FoodItem foodItem){
        return FoodItemResponse.builder()
                .dishName(foodItem.getDishName())
                .veg(foodItem.isVeg())
                .price(foodItem.getPrice())
                .build();
    }
}
