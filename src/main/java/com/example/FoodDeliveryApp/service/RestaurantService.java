package com.example.FoodDeliveryApp.service;

import com.example.FoodDeliveryApp.dto.request.MenuItemRequest;
import com.example.FoodDeliveryApp.dto.request.RestaurantRequest;
import com.example.FoodDeliveryApp.dto.response.MenuItemResponse;
import com.example.FoodDeliveryApp.dto.response.RestaurantResponse;

import java.util.List;

public interface RestaurantService {

    RestaurantResponse addRestaurant(RestaurantRequest restaurantRequest);

    String changeOpenedStatus(int id);

    RestaurantResponse addMenuItemToRestaurant(MenuItemRequest menuItemRequest);

    List<MenuItemResponse> getMenuFromRestaurant(int id);
}
