package com.example.FoodDeliveryApp.service.implementation;

import com.example.FoodDeliveryApp.dto.request.MenuItemRequest;
import com.example.FoodDeliveryApp.dto.request.RestaurantRequest;
import com.example.FoodDeliveryApp.dto.response.MenuItemResponse;
import com.example.FoodDeliveryApp.dto.response.RestaurantResponse;
import com.example.FoodDeliveryApp.exception.RestaurantNotFoundException;
import com.example.FoodDeliveryApp.model.MenuItem;
import com.example.FoodDeliveryApp.model.Restaurant;
import com.example.FoodDeliveryApp.repository.RestaurantRepository;
import com.example.FoodDeliveryApp.service.RestaurantService;
import com.example.FoodDeliveryApp.transformer.MenuItemTransformer;
import com.example.FoodDeliveryApp.transformer.RestaurantTransformer;
import com.example.FoodDeliveryApp.utils.ValidationUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public RestaurantResponse addMenuItemToRestaurant(MenuItemRequest menuItemRequest) {

        // Check if the Restaurant is Present or Not
        if(!validationUtils.validateRestaurantId(menuItemRequest.getRestaurantId())){
            throw new RestaurantNotFoundException("Restaurant Does Not Exist");
        }

        Restaurant restaurant = restaurantRepository.findById(menuItemRequest.getRestaurantId()).get();

        // Make the Food Entity
        MenuItem menuItem = MenuItemTransformer.MenuItemRequestToMenuItem(menuItemRequest);

        // Add the Restaurant to FoodItem and FoodItem to the restaurant
        menuItem.setRestaurant(restaurant);
        restaurant.getAvailableMenuItems().add(menuItem);

        // Save the Restaurant and FoodItem to DB
        restaurantRepository.save(restaurant);

        return RestaurantTransformer.RestaurantToRestaurantResponse(restaurant);
    }

    @Override
    public List<MenuItemResponse> getMenuFromRestaurant(int id) {
        // Check if the Restaurant is Present or Not
        if(!validationUtils.validateRestaurantId(id)){
            throw new RestaurantNotFoundException("Restaurant Does Not Exist");
        }

        Restaurant restaurant = restaurantRepository.findById(id).get();

        List<MenuItemResponse> menuItemRespons = restaurant.getAvailableMenuItems()
                .stream()
                .map(foodItem -> MenuItemTransformer.MenuItemToMenuItemResponse(foodItem))
                .collect(Collectors.toList());

        return menuItemRespons;
    }
}
