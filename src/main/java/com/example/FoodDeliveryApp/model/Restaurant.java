package com.example.FoodDeliveryApp.model;

import com.example.FoodDeliveryApp.Enum.RestarauntCategory;
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
@Table(name = "restaurant")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;

    String location;

    RestarauntCategory restarauntCategory;

    @Column(unique = true,nullable = false)
    @Size(min = 10, max = 10)
    String contactNumber;

    boolean opened;

    @OneToMany(mappedBy = "restaurant" , cascade = CascadeType.ALL)
    List<FoodItem> availableFoodItems = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant" , cascade = CascadeType.ALL)
    List<OrderEntity> orders = new ArrayList<>();
}
