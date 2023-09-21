package com.example.FoodDeliveryApp.transformer;

import com.example.FoodDeliveryApp.dto.response.CartResponse;
import com.example.FoodDeliveryApp.model.Cart;

import java.util.ArrayList;

public class CartTransformer {

    public static CartResponse CartToCartResponse(Cart cart){

        return CartResponse.builder()
                .cartTotal(cart.getCartTotal())
                .foodItemResponseList(new ArrayList<>())
                .build();
    }
}
