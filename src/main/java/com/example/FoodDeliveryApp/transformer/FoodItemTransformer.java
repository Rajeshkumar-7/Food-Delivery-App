package com.example.FoodDeliveryApp.transformer;

import com.example.FoodDeliveryApp.dto.request.FoodRequest;
import com.example.FoodDeliveryApp.dto.response.FoodResponse;
import com.example.FoodDeliveryApp.model.FoodItem;

public class FoodItemTransformer {

    public static FoodResponse FoodItemToFoodResponse(FoodItem foodItem){

        return FoodResponse.builder()
                .dishName(foodItem.getMenuItem().getDishName())
                .category(foodItem.getMenuItem().getFoodCategory())
                .price(foodItem.getMenuItem().getPrice())
                .quantityAdded(foodItem.getRequiredQuantity())
                .totalCost(foodItem.getTotalCost())
                .veg(foodItem.getMenuItem().isVeg())
                .build();
    }


}
