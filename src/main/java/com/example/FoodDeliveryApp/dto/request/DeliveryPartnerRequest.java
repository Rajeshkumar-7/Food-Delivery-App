package com.example.FoodDeliveryApp.dto.request;

import com.example.FoodDeliveryApp.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeliveryPartnerRequest {

    String name;

    String mobileNumber;

    Gender gender;
}
