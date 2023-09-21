package com.example.FoodDeliveryApp.dto.request;

import com.example.FoodDeliveryApp.Enum.Gender;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerRequest {

    String name;

    String email;

    String mobileNumber;

    String address;

    Gender gender;
}
