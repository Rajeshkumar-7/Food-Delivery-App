package com.example.FoodDeliveryApp.transformer;

import com.example.FoodDeliveryApp.dto.request.FoodItemRequest;
import com.example.FoodDeliveryApp.dto.response.FoodItemResponse;
import com.example.FoodDeliveryApp.model.FoodItem;

public class FoodItemTransformer {

    public static FoodItemResponse FoodItemToFoodResponse(FoodItem foodItem){
        return FoodItemResponse.builder()
                .dishName(foodItem.getDishName())
                .veg(foodItem.isVeg())
                .price(foodItem.getPrice())
                .available(foodItem.isAvailable())
                .build();
    }

    public static FoodItem FoodItemRequestToFoodItem(FoodItemRequest foodItemRequest){
        return FoodItem.builder()
                .dishName(foodItemRequest.getDishName())
                .foodCategory(foodItemRequest.getFoodCategory())
                .veg(foodItemRequest.isVeg())
                .available(foodItemRequest.isAvailable())
                .price(foodItemRequest.getPrice())
                .build();
    }
}
