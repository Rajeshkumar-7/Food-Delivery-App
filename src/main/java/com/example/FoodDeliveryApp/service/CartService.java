package com.example.FoodDeliveryApp.service;

import com.example.FoodDeliveryApp.dto.request.FoodRequest;
import com.example.FoodDeliveryApp.dto.response.CartResponse;

public interface CartService {

    CartResponse addFoodItemToCart(FoodRequest foodRequest);
}
