package com.example.FoodDeliveryApp.repository;

import com.example.FoodDeliveryApp.model.Customer;
import com.example.FoodDeliveryApp.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderEntityRepository extends JpaRepository<OrderEntity , Integer> {

    Customer findByMobileNumber(String customerMobileNumber);
}
