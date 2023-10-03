package com.example.FoodDeliveryApp.transformer;

import com.example.FoodDeliveryApp.dto.request.MenuItemRequest;
import com.example.FoodDeliveryApp.dto.response.MenuItemResponse;
import com.example.FoodDeliveryApp.model.MenuItem;

public class MenuItemTransformer {

    public static MenuItemResponse MenuItemToMenuItemResponse(MenuItem menuItem){
        return MenuItemResponse.builder()
                .dishName(menuItem.getDishName())
                .foodCategory(menuItem.getFoodCategory())
                .veg(menuItem.isVeg())
                .price(menuItem.getPrice())
                .available(menuItem.isAvailable())
                .build();
    }

    public static MenuItem MenuItemRequestToMenuItem(MenuItemRequest menuItemRequest){
        return MenuItem.builder()
                .dishName(menuItemRequest.getDishName())
                .foodCategory(menuItemRequest.getFoodCategory())
                .veg(menuItemRequest.isVeg())
                .available(menuItemRequest.isAvailable())
                .price(menuItemRequest.getPrice())
                .build();
    }
}
