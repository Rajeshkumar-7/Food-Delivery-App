package com.example.FoodDeliveryApp.repository;

import com.example.FoodDeliveryApp.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
