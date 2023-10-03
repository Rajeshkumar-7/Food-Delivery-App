package com.example.FoodDeliveryApp.repository;

import com.example.FoodDeliveryApp.Enum.FoodCategory;
import com.example.FoodDeliveryApp.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Integer> {

    List<MenuItem> findByFoodCategory(FoodCategory foodCategory);

    List<MenuItem> findByVegTrue();

    List<MenuItem> findByVegFalse();
}
