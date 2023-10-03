package com.example.FoodDeliveryApp.transformer;

import com.example.FoodDeliveryApp.dto.request.RestaurantRequest;
import com.example.FoodDeliveryApp.dto.response.MenuItemResponse;
import com.example.FoodDeliveryApp.dto.response.RestaurantResponse;
import com.example.FoodDeliveryApp.model.Restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RestaurantTransformer {

    public static Restaurant RestaurantRequestToRestaurant(RestaurantRequest restaurantRequest){
        return Restaurant.builder()
                .name(restaurantRequest.getName())
                .location(restaurantRequest.getLocation())
                .restaurantCategory(restaurantRequest.getRestaurantCategory())
                .contactNumber((restaurantRequest.getContactNumber()))
                .opened(true)
                .availableMenuItems(new ArrayList<>())
                .orders(new ArrayList<>())
                .build();
    }

    public static RestaurantResponse RestaurantToRestaurantResponse(Restaurant restaurant){

        List<MenuItemResponse> menuItemRespons = restaurant.getAvailableMenuItems()
                .stream()
                .map(foodItem -> MenuItemTransformer.MenuItemToMenuItemResponse(foodItem))
                .collect(Collectors.toList());

        return RestaurantResponse.builder()
                .name(restaurant.getName())
                .location(restaurant.getLocation())
                .opened(restaurant.isOpened())
                .contactNumber(restaurant.getContactNumber())
                .restaurantCategory(restaurant.getRestaurantCategory())
                .menuItemRespons(menuItemRespons)
                .build();
    }
}
