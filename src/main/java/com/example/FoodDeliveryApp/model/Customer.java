package com.example.FoodDeliveryApp.model;

import com.example.FoodDeliveryApp.Enum.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
@Table(name = "customer")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Size(min = 2, message = "{validation.name.size.too_short}")
    @Size(max = 40, message = "{validation.name.size.too_long}")
    String name;

    @Email
    @Column(unique = true)
    String email;

    @Column(unique = true , nullable = false)
    @Size(min = 10 , max = 10)
    String mobileNumber;

    String address;

    Gender gender;

    @OneToOne(mappedBy = "customer" , cascade = CascadeType.ALL)
    Cart cart;

    @OneToMany(mappedBy = "customer" , cascade = CascadeType.ALL)
    List<OrderEntity> orders = new ArrayList<>();

}
