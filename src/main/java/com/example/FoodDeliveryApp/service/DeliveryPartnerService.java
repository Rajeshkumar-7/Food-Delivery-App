package com.example.FoodDeliveryApp.service;

import com.example.FoodDeliveryApp.dto.request.DeliveryPartnerRequest;
import com.example.FoodDeliveryApp.dto.response.DeliveryPartnerResponse;

public interface DeliveryPartnerService {

    DeliveryPartnerResponse addDeliveryPartner(DeliveryPartnerRequest deliveryPartnerRequest);

    String updateAvailability(String mobileNumber);

    DeliveryPartnerResponse getDeliveryPartnerWithMostDeliveries();
}
