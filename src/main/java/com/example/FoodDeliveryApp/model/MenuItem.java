package com.example.FoodDeliveryApp.model;

import com.example.FoodDeliveryApp.Enum.FoodCategory;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "menu_item")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String dishName;

    @Enumerated(EnumType.STRING)
    FoodCategory foodCategory;

    boolean veg;

    boolean available;

    double price;

    @ManyToOne
    @JoinColumn
    Restaurant restaurant;

    @OneToMany(mappedBy = "menuItem" , cascade = CascadeType.ALL)
    List<FoodItem> foodItems = new ArrayList<>();
}
