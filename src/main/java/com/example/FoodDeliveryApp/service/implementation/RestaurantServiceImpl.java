package com.example.FoodDeliveryApp.service.implementation;

import com.example.FoodDeliveryApp.dto.request.FoodItemRequest;
import com.example.FoodDeliveryApp.dto.request.RestaurantRequest;
import com.example.FoodDeliveryApp.dto.response.RestaurantResponse;
import com.example.FoodDeliveryApp.exception.RestaurantNotFoundException;
import com.example.FoodDeliveryApp.model.FoodItem;
import com.example.FoodDeliveryApp.model.Restaurant;
import com.example.FoodDeliveryApp.repository.RestaurantRepository;
import com.example.FoodDeliveryApp.service.RestaurantService;
import com.example.FoodDeliveryApp.transformer.FoodItemTransformer;
import com.example.FoodDeliveryApp.transformer.RestaurantTransformer;
import com.example.FoodDeliveryApp.utils.ValidationUtils;
import org.springframework.stereotype.Service;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    final RestaurantRepository restaurantRepository;
    final ValidationUtils validationUtils;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, ValidationUtils validationUtils) {
        this.restaurantRepository = restaurantRepository;
        this.validationUtils = validationUtils;
    }


    @Override
    public RestaurantResponse addRestaurant(RestaurantRequest restaurantRequest) {

        // DTO --> Model
        Restaurant restaurant = RestaurantTransformer.RestaurantRequestToRestaurant(restaurantRequest);
        // Save Model in DB
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);
        // Saved Model --> DTO
        return RestaurantTransformer.RestaurantToRestaurantResponse(savedRestaurant);
    }

    public String changeOpenedStatus(int id) {

        // Check if the Restaurant is present
        if(!validationUtils.validateRestaurantId(id)){
            throw new RestaurantNotFoundException("Restaurant is Not Found");
        }

        // Get the Restaurant and change the status
        Restaurant restaurant = restaurantRepository.findById(id).get();
        restaurant.setOpened(!restaurant.isOpened());

        // Save the Restaurant to DB
        restaurantRepository.save(restaurant);

        // Return the Status as Message to client
        if(restaurant.isOpened()){
            return "Restaurant is now Opened";
        }
        return "Restaurant is now Closed";
    }

    @Override
    public RestaurantResponse addFoodItemToRestaurant(FoodItemRequest foodItemRequest) {

        // Check if the Restaurant is Present or Not
        if(!validationUtils.validateRestaurantId(foodItemRequest.getRestaurantId())){
            throw new RestaurantNotFoundException("Restaurant Does Not Exist");
        }

        Restaurant restaurant = restaurantRepository.findById(foodItemRequest.getRestaurantId()).get();

        // Make the Food Entity
        FoodItem foodItem = FoodItemTransformer.FoodItemRequestToFoodItem(foodItemRequest);

        // Add the Restaurant to FoodItem and FoodItem to the restaurant
        foodItem.setRestaurant(restaurant);
        restaurant.getAvailableFoodItems().add(foodItem);

        // Save the Restaurant and FoodItem to DB
        restaurantRepository.save(restaurant);

        return RestaurantTransformer.RestaurantToRestaurantResponse(restaurant);
    }
}
