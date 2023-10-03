package com.example.FoodDeliveryApp.service;

import com.example.FoodDeliveryApp.dto.response.MenuItemResponse;

import java.util.List;

public interface MenuItemService {
    List<MenuItemResponse> getMenuItemByCategory(String foodCategory);

    List<MenuItemResponse> getVegFoodItems();

    List<MenuItemResponse> getNonVegFoodItems();
}
