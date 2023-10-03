package com.example.FoodDeliveryApp.dto.response;

import com.example.FoodDeliveryApp.Enum.FoodCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MenuItemResponse {

    String dishName;

    FoodCategory foodCategory;

    boolean veg;

    boolean available;

    double price;

}
