package com.example.FoodDeliveryApp.transformer;

import com.example.FoodDeliveryApp.dto.response.CartResponse;
import com.example.FoodDeliveryApp.dto.response.FoodResponse;
import com.example.FoodDeliveryApp.dto.response.MenuItemResponse;
import com.example.FoodDeliveryApp.model.Cart;
import com.example.FoodDeliveryApp.model.FoodItem;
import com.example.FoodDeliveryApp.model.MenuItem;

import java.util.ArrayList;
import java.util.List;

import static com.example.FoodDeliveryApp.transformer.MenuItemTransformer.MenuItemToMenuItemResponse;

public class CartTransformer {

    public static CartResponse CartToCartResponse(Cart cart){


        CartResponse cartResponse = new CartResponse();
        cartResponse.setName(cart.getCustomer().getName());
        cartResponse.setAddress(cart.getCustomer().getAddress());
        cartResponse.setMobileNumber(cart.getCustomer().getMobileNumber());
        cartResponse.setCartTotal(cart.getCartTotal());



        if(cart.getFoodItems().isEmpty()){
            // If there is no foodItems in the cart
            cartResponse.setRestaurantName("");
            cartResponse.setFoodList(new ArrayList<>());
        }else{
            // If there is FoodItems in the cart
            cartResponse.setRestaurantName(cart.getFoodItems().get(0).getMenuItem().getRestaurant().getName());
            List<FoodResponse> foodResponses = new ArrayList<>();
            for(FoodItem foodItem : cart.getFoodItems()){
                foodResponses.add(FoodItemTransformer.FoodItemToFoodResponse(foodItem));
            }
            cartResponse.setFoodList(foodResponses);
        }
        return cartResponse;
    }


}
