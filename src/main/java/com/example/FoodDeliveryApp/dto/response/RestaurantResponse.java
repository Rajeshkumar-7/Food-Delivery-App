package com.example.FoodDeliveryApp.dto.response;

import com.example.FoodDeliveryApp.Enum.RestaurantCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RestaurantResponse {

    String name;

    String location;

    boolean opened;

    String contactNumber;

    RestaurantCategory restaurantCategory;

    List<FoodItemResponse> foodItemResponses;
}
