package com.example.FoodDeliveryApp.service.implementation;

import com.example.FoodDeliveryApp.dto.request.DeliveryPartnerRequest;
import com.example.FoodDeliveryApp.model.DeliveryPartner;
import com.example.FoodDeliveryApp.repository.DeliverPartnerRepository;
import com.example.FoodDeliveryApp.service.DeliveryPartnerService;
import org.springframework.stereotype.Service;

@Service
public class DeliveryPartnerServiceImpl implements DeliveryPartnerService {

    final DeliverPartnerRepository deliverPartnerRepository;

    public DeliveryPartnerServiceImpl(DeliverPartnerRepository deliverPartnerRepository) {
        this.deliverPartnerRepository = deliverPartnerRepository;
    }

    @Override
    public String addDeliveryPartner(DeliveryPartnerRequest deliveryPartnerRequest) {

        // Create a new Delivery partner and set its value
        DeliveryPartner deliveryPartner = new DeliveryPartner();
        deliveryPartner.setName(deliveryPartnerRequest.getName());
        deliveryPartner.setMobileNumber(deliveryPartnerRequest.getMobileNumber());
        deliveryPartner.setAvailable(true);
        deliveryPartner.setGender(deliveryPartnerRequest.getGender());

        // Save the Entity in DB
        DeliveryPartner savedDeliveryPartner = deliverPartnerRepository.save(deliveryPartner);

        // return the Message
        return deliveryPartner.getName() + " " + "has been registered as Delivery partner";
    }
}
