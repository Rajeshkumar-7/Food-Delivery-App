package com.example.FoodDeliveryApp.service.implementation;

import com.example.FoodDeliveryApp.dto.request.DeliveryPartnerRequest;
import com.example.FoodDeliveryApp.dto.response.DeliveryPartnerResponse;
import com.example.FoodDeliveryApp.exception.DeliveryPartnerNotFoundException;
import com.example.FoodDeliveryApp.model.DeliveryPartner;
import com.example.FoodDeliveryApp.repository.DeliverPartnerRepository;
import com.example.FoodDeliveryApp.service.DeliveryPartnerService;
import com.example.FoodDeliveryApp.transformer.DeliveryPartnerTransformer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryPartnerServiceImpl implements DeliveryPartnerService {

    final DeliverPartnerRepository deliverPartnerRepository;

    public DeliveryPartnerServiceImpl(DeliverPartnerRepository deliverPartnerRepository) {
        this.deliverPartnerRepository = deliverPartnerRepository;
    }

    @Override
    public DeliveryPartnerResponse addDeliveryPartner(DeliveryPartnerRequest deliveryPartnerRequest) {

        // Create a new Delivery partner and set its value
        DeliveryPartner deliveryPartner = DeliveryPartnerTransformer.DeliveryPartnerRequestToDeliveryPartner(deliveryPartnerRequest);

        // Save the Entity in DB
        DeliveryPartner savedDeliveryPartner = deliverPartnerRepository.save(deliveryPartner);

        // Convert the saved entity to response DTO and return it
        return DeliveryPartnerTransformer.DeliveryPartnerToDeliveryPartnerResponse(savedDeliveryPartner);
    }

    @Override
    public String updateAvailability(String mobileNumber) {

        // Check if the mobile Number is correct or not
        DeliveryPartner deliveryPartner = deliverPartnerRepository.findByMobileNumber(mobileNumber);
        if(deliveryPartner == null){
            throw new DeliveryPartnerNotFoundException("Invalid Mobile Number !!!");
        }

        // Update the availability
        deliveryPartner.setAvailable(!deliveryPartner.isAvailable());

        // Save to DB and return
        deliverPartnerRepository.save(deliveryPartner);
        if(deliveryPartner.isAvailable()){
            return deliveryPartner.getName() + " is available for delivery";
        }else{
            return deliveryPartner.getName() + " is not available for delivery";
        }
    }

    @Override
    public DeliveryPartnerResponse getDeliveryPartnerWithMostDeliveries() {

        // Get all the Delivery Partners
        List<DeliveryPartner> deliveryPartnerList = deliverPartnerRepository.findAll();

        // Get the DeliveryPartner with most deliveries
        DeliveryPartner deliveryPartner = null;
        int maxOrders = 0;
        for (DeliveryPartner deliveryPartner1 : deliveryPartnerList){
            if(deliveryPartner1.getOrders().size() > maxOrders){
                maxOrders = deliveryPartner1.getOrders().size();
                deliveryPartner = deliveryPartner1;
            }
        }

        // Check if we have an Delivery Partner or not
        if(deliveryPartner == null){
            throw new DeliveryPartnerNotFoundException("Delivery Partner is not found");
        }

        // Convert to DTO and return it
        return DeliveryPartnerTransformer.DeliveryPartnerToDeliveryPartnerResponse(deliveryPartner);
    }
}
