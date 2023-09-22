package com.example.FoodDeliveryApp.service;

import com.example.FoodDeliveryApp.dto.request.FoodItemRequest;
import com.example.FoodDeliveryApp.dto.request.RestaurantRequest;
import com.example.FoodDeliveryApp.dto.response.RestaurantResponse;

public interface RestaurantService {

    RestaurantResponse addRestaurant(RestaurantRequest restaurantRequest);

    String changeOpenedStatus(int id);

    RestaurantResponse addFoodItemToRestaurant(FoodItemRequest foodItemRequest);
}
