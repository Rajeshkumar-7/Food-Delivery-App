package com.example.FoodDeliveryApp.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartResponse {

    String name;

    String mobileNumber;

    String address;

    double cartTotal;

    String restaurantName;

    List<FoodResponse> foodList;
}
