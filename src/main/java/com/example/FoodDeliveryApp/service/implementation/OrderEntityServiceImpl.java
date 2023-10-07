package com.example.FoodDeliveryApp.service.implementation;

import com.example.FoodDeliveryApp.dto.response.OrderEntityResponse;
import com.example.FoodDeliveryApp.exception.CustomerNotFoundException;
import com.example.FoodDeliveryApp.exception.DeliveryPartnerNotFoundException;
import com.example.FoodDeliveryApp.exception.EmptyCartException;
import com.example.FoodDeliveryApp.model.*;
import com.example.FoodDeliveryApp.repository.CustomerRepository;
import com.example.FoodDeliveryApp.repository.DeliverPartnerRepository;
import com.example.FoodDeliveryApp.repository.OrderEntityRepository;
import com.example.FoodDeliveryApp.repository.RestaurantRepository;
import com.example.FoodDeliveryApp.service.OrderEntityService;
import com.example.FoodDeliveryApp.transformer.OrderEntityTransformer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OrderEntityServiceImpl implements OrderEntityService {

    final CustomerRepository customerRepository;
    final RestaurantRepository restaurantRepository;
    final OrderEntityRepository orderEntityRepository;
    final DeliverPartnerRepository deliverPartnerRepository;

    public OrderEntityServiceImpl(OrderEntityRepository orderEntityRepository, DeliverPartnerRepository deliverPartnerRepository, CustomerRepository customerRepository, RestaurantRepository restaurantRepository) {
        this.orderEntityRepository = orderEntityRepository;
        this.deliverPartnerRepository = deliverPartnerRepository;
        this.customerRepository = customerRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public OrderEntityResponse placeOrder(String customerMobileNumber) {

        // Check if the customer is found or not
        Customer customer = orderEntityRepository.findByMobileNumber(customerMobileNumber);
        if(customer == null){
            throw new CustomerNotFoundException("Invalid Mobile Number !!");
        }

        // Verify if the Cart is empty or not
        Cart cart = customer.getCart();
        if(cart.getFoodItems().isEmpty()){
            throw new EmptyCartException("Sorry! your cart is empty!!!");
        }

        // Check if there is any Delivery Partner available for delivery
        // If there are delivery partners available assign a random delivery Partner to the order
        DeliveryPartner deliveryPartner = deliverPartnerRepository.findRandomDeliveryPartner();
        if(deliveryPartner == null){
            throw new DeliveryPartnerNotFoundException("Sorry! Currently there are No delivery Partners available. So we cannot process your order.");
        }

        // Get the Respective restaurant Entity
        Restaurant restaurant = cart.getFoodItems().get(0).getMenuItem().getRestaurant();

        // Prepare the OrderEntity
        OrderEntity orderEntity = OrderEntityTransformer.prepareOrderEntity(cart);

        // Save the OrderEntity in DB
        OrderEntity savedOrderEntity = orderEntityRepository.save(orderEntity);

        // Set the respective Entity relations
        savedOrderEntity.setCustomer(customer);
        savedOrderEntity.setDeliveryPartner(deliveryPartner);
        savedOrderEntity.setRestaurant(restaurant);
        savedOrderEntity.setFoodItems(cart.getFoodItems());

        customer.getOrders().add(savedOrderEntity);
        deliveryPartner.getOrders().add(savedOrderEntity);
        restaurant.getOrders().add(savedOrderEntity);

        for(FoodItem foodItem : cart.getFoodItems()){
            foodItem.setCart(null);
            foodItem.setOrder(savedOrderEntity);
        }

        // Once the Order is placed. We have to clear the cart
        clearCart(cart);

        // Save the entities into DB
        customerRepository.save(customer);
        restaurantRepository.save(restaurant);
        deliverPartnerRepository.save(deliveryPartner);

        // Convert to OrderEntity DTO and return it
        return OrderEntityTransformer.OrderEntityToOrderEntityResponse(savedOrderEntity);

    }

    private void clearCart(Cart cart) {
        cart.setCartTotal(0);
        cart.setFoodItems(new ArrayList<>());
    }
}
