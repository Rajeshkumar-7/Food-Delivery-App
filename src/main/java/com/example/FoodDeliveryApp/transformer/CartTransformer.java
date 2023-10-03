package com.example.FoodDeliveryApp.transformer;

import com.example.FoodDeliveryApp.dto.response.CartResponse;
import com.example.FoodDeliveryApp.dto.response.MenuItemResponse;
import com.example.FoodDeliveryApp.model.Cart;
import com.example.FoodDeliveryApp.model.MenuItem;

import java.util.ArrayList;
import java.util.List;

import static com.example.FoodDeliveryApp.transformer.MenuItemTransformer.MenuItemToMenuItemResponse;

public class CartTransformer {

    public static CartResponse CartToCartResponse(Cart cart){

        CartResponse cartResponse = CartResponse.builder()
                .cartTotal(cart.getCartTotal())
                .build();

        List<MenuItemResponse> menuItemResponse = new ArrayList<>();
        for(MenuItem menuItem : cart.getMenuItems()){
            menuItemResponse.add(MenuItemToMenuItemResponse(menuItem));
        }

        cartResponse.setFoodItems(menuItemResponse);

        return cartResponse;
    }
}
