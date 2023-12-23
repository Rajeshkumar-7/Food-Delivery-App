package com.example.FoodDeliveryApp.dto.response;

import com.example.FoodDeliveryApp.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeliveryPartnerResponse {

    String name;

    String mobileNumber;

    Gender gender;

    boolean available;
}
