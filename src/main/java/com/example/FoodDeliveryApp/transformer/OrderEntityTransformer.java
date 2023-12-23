package com.example.FoodDeliveryApp.transformer;

import com.example.FoodDeliveryApp.dto.response.FoodResponse;
import com.example.FoodDeliveryApp.dto.response.OrderEntityResponse;
import com.example.FoodDeliveryApp.model.Cart;
import com.example.FoodDeliveryApp.model.OrderEntity;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

public class OrderEntityTransformer {

    public static OrderEntity prepareOrderEntity(Cart cart){
        return OrderEntity.builder()
                .orderTotal(cart.getCartTotal())
                .orderId(String.valueOf(UUID.randomUUID()))
                .build();
    }

    public static OrderEntityResponse OrderEntityToOrderEntityResponse(OrderEntity orderEntity){

        List<FoodResponse> foodResponses = orderEntity.getFoodItems()
                .stream()
                .map(foodItem -> FoodItemTransformer.FoodItemToFoodResponse(foodItem))
                .collect(Collectors.toList());

        return OrderEntityResponse.builder()
                .customerName(orderEntity.getCustomer().getName())
                .customerMobile(orderEntity.getCustomer().getMobileNumber())
                .orderId(orderEntity.getOrderId())
                .orderTotal(orderEntity.getOrderTotal())
                .orderTime(orderEntity.getOrderTime())
                .deliveryPartnerName(orderEntity.getDeliveryPartner().getName())
                .deliveryPartnerMobile(orderEntity.getDeliveryPartner().getMobileNumber())
                .restaurantName(orderEntity.getRestaurant().getName())
                .foodResponses(foodResponses)
                .build();
    }
}
