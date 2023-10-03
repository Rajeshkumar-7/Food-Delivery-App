package com.example.FoodDeliveryApp.dto.response;

import com.example.FoodDeliveryApp.Enum.FoodCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FoodResponse {

    String dishName;

    FoodCategory category;

    boolean veg;

    double price;

    int quantityAdded;

    double totalCost;

}
