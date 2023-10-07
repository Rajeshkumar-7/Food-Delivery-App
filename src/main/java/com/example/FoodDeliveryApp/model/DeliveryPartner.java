package com.example.FoodDeliveryApp.model;

import com.example.FoodDeliveryApp.Enum.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "delivery_partner")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeliveryPartner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;

    @Column(unique = true , nullable = false)
    @Size(min = 10 , max = 10)
    String mobileNumber;

    boolean available;

    @Enumerated(EnumType.STRING)
    Gender gender;

    @OneToMany(mappedBy = "deliveryPartner" , cascade = CascadeType.ALL)
    List<OrderEntity> orders = new ArrayList<>();
}
