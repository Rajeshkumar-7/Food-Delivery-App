package com.example.FoodDeliveryApp.repository;

import com.example.FoodDeliveryApp.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant , Integer> {
}
