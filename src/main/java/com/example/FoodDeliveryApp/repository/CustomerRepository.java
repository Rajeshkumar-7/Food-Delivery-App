package com.example.FoodDeliveryApp.repository;

import com.example.FoodDeliveryApp.Enum.Gender;
import com.example.FoodDeliveryApp.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    public Customer findByMobileNumber(String mobileNumber);

    public List<Customer> findByGender(Gender gender);
}
