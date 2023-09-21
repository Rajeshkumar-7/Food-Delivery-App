package com.example.FoodDeliveryApp.repository;

import com.example.FoodDeliveryApp.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Customer findbyMobileNumber(String mobileNumber);

}
