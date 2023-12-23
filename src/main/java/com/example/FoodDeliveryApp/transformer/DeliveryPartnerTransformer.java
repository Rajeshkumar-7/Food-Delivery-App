package com.example.FoodDeliveryApp.transformer;

import com.example.FoodDeliveryApp.dto.request.DeliveryPartnerRequest;
import com.example.FoodDeliveryApp.dto.response.DeliveryPartnerResponse;
import com.example.FoodDeliveryApp.model.DeliveryPartner;

public class DeliveryPartnerTransformer {

    public static DeliveryPartner DeliveryPartnerRequestToDeliveryPartner(DeliveryPartnerRequest deliveryPartnerRequest){
        return DeliveryPartner.builder()
                .name(deliveryPartnerRequest.getName())
                .mobileNumber(deliveryPartnerRequest.getMobileNumber())
                .gender(deliveryPartnerRequest.getGender())
                .available(true)
                .build();
    }

    public static DeliveryPartnerResponse DeliveryPartnerToDeliveryPartnerResponse(DeliveryPartner deliveryPartner){
        return DeliveryPartnerResponse.builder()
                .name(deliveryPartner.getName())
                .mobileNumber(deliveryPartner.getMobileNumber())
                .gender(deliveryPartner.getGender())
                .available(deliveryPartner.isAvailable())
                .build();
    }
}
