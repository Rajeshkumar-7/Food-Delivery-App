package com.example.FoodDeliveryApp.dto.request;

import com.example.FoodDeliveryApp.Enum.FoodCategory;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "food_item")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FoodItemRequest {

    int restaurantId;

    String dishName;

    FoodCategory foodCategory;

    boolean veg;

    double price;

    boolean available;

}
