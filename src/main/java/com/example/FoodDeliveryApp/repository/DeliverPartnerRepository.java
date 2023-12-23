package com.example.FoodDeliveryApp.repository;

import com.example.FoodDeliveryApp.model.DeliveryPartner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliverPartnerRepository extends JpaRepository<DeliveryPartner , Integer> {

    String query = "select p from DeliveryPartner p where p.available = true order by RAND() LIMIT 1";

    @Query(value = query)
    DeliveryPartner findRandomDeliveryPartner();

    DeliveryPartner findByMobileNumber(String mobileNumber);
}
