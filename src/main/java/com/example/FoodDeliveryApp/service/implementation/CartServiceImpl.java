package com.example.FoodDeliveryApp.service.implementation;

import com.example.FoodDeliveryApp.dto.request.FoodRequest;
import com.example.FoodDeliveryApp.dto.response.CartResponse;
import com.example.FoodDeliveryApp.exception.CustomerNotFoundException;
import com.example.FoodDeliveryApp.exception.MenuItemNotFoundException;
import com.example.FoodDeliveryApp.exception.OutOfStockException;
import com.example.FoodDeliveryApp.exception.RestaurantNotOpenedException;
import com.example.FoodDeliveryApp.model.*;
import com.example.FoodDeliveryApp.repository.CartRepository;
import com.example.FoodDeliveryApp.repository.CustomerRepository;
import com.example.FoodDeliveryApp.repository.FoodItemRepository;
import com.example.FoodDeliveryApp.repository.MenuItemRepository;
import com.example.FoodDeliveryApp.service.CartService;
import com.example.FoodDeliveryApp.transformer.CartTransformer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    final CartRepository cartRepository;
    final CustomerRepository customerRepository;
    final MenuItemRepository menuItemRepository;
    final FoodItemRepository foodItemRepository;

    public CartServiceImpl(CustomerRepository customerRepository, CartRepository cartRepository, MenuItemRepository menuItemRepository, FoodItemRepository foodItemRepository) {
        this.cartRepository = cartRepository;
        this.customerRepository = customerRepository;
        this.menuItemRepository = menuItemRepository;
        this.foodItemRepository = foodItemRepository;
    }

    @Override
    public CartResponse addFoodItemToCart(FoodRequest foodRequest) {

        // Check if there is a customer with given mobile number
        Customer customer = customerRepository.findByMobileNumber(foodRequest.getMobileNumber());
        if(customer == null){
            throw new CustomerNotFoundException("Customer doesn't exist");
        }

        // Check if the menu item is present
        Optional<MenuItem> optionalMenuItem = menuItemRepository.findById(foodRequest.getMenuItemId());
        if(optionalMenuItem.isEmpty()){
            throw new MenuItemNotFoundException("Given menuItem does not exist");
        }

        MenuItem menuItem = optionalMenuItem.get();

        // Check if the Restaurant is Opened
        if(!menuItem.getRestaurant().isOpened()){
            throw new RestaurantNotOpenedException("Restaurant is not opened yet. Come back later");
        }

        // Check if the MenuItem is available in that restaurant
        if(!menuItem.isAvailable()){
            throw new OutOfStockException("Given dish is out of stock for now!!");
        }

        Cart cart = customer.getCart();

        // Check if the cart has items from different restaurant
        if(!cart.getFoodItems().isEmpty()){
            Restaurant currRestaurant = cart.getFoodItems().get(0).getMenuItem().getRestaurant();
            Restaurant newRestaurant = menuItem.getRestaurant();

            if(!currRestaurant.equals(newRestaurant)){
                // Delete all the food items in the cart and clear the cart
                List<FoodItem> foodItems = cart.getFoodItems();
                for (FoodItem foodItem : foodItems){
                    foodItem.setCart(null);
                    foodItem.setMenuItem(null);
                    foodItem.setOrder(null);
                }
                cart.setCartTotal(0);
                cart.getFoodItems().clear();
                for(FoodItem foodItem : foodItems){
                  foodItemRepository.delete(foodItem);
                }
            }
        }

        // Now check if the foodItem is already present in the cart
        boolean alreadyExist = false;
        FoodItem savedFoodItem;
        if(!cart.getFoodItems().isEmpty()){
            for(FoodItem foodItem : cart.getFoodItems()){
                if(foodItem.getMenuItem().getId() == menuItem.getId()){
                    savedFoodItem = foodItem;
                    foodItem.setRequiredQuantity(foodItem.getRequiredQuantity() + foodRequest.getRequiredQuantity());
                    foodItem.setTotalCost(foodItem.getMenuItem().getPrice() * foodItem.getRequiredQuantity());
                    alreadyExist = true;
                    break;
                }
            }
        }

        // If the food item is not there then create a new food item and save it in the DB
        if(!alreadyExist){
            FoodItem foodItem = FoodItem.builder()
                    .requiredQuantity(foodRequest.getRequiredQuantity())
                    .totalCost(foodRequest.getRequiredQuantity() * menuItem.getPrice())
                    .menuItem(menuItem)
                    .build();

            savedFoodItem = foodItemRepository.save(foodItem);
            cart.getFoodItems().add(savedFoodItem);
            menuItem.getFoodItems().add(savedFoodItem);
            savedFoodItem.setCart(cart);
        }

        // Calculate the total Price in cart
        double cartTotal = 0;
        // Previous FoodItems
        for(FoodItem foodItem : cart.getFoodItems()){
            cartTotal += foodItem.getTotalCost();
        }

        // Set the total Price in cart
        cart.setCartTotal(cartTotal);

        // Save the cart to Db
        Cart savedCart = cartRepository.save(cart);
        MenuItem savedMenuItem = menuItemRepository.save(menuItem);


        // Convert Cart to Cart DTO and return it
        return CartTransformer.CartToCartResponse(cart);
    }
}
