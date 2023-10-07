package com.example.FoodDeliveryApp.service.implementation;

import com.example.FoodDeliveryApp.dto.request.FoodRequest;
import com.example.FoodDeliveryApp.dto.response.CartResponse;
import com.example.FoodDeliveryApp.exception.CustomerNotFoundException;
import com.example.FoodDeliveryApp.exception.MenuItemNotFoundException;
import com.example.FoodDeliveryApp.exception.OutOfStockException;
import com.example.FoodDeliveryApp.exception.RestaurantNotOpenedException;
import com.example.FoodDeliveryApp.model.Cart;
import com.example.FoodDeliveryApp.model.Customer;
import com.example.FoodDeliveryApp.model.FoodItem;
import com.example.FoodDeliveryApp.model.MenuItem;
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

        // Ready to add FoodItem in the cart
        FoodItem foodItem = FoodItem.builder()
                .requiredQuantity(foodRequest.getRequiredQuantity())
                .totalCost(foodRequest.getRequiredQuantity() * menuItem.getPrice())
                .menuItem(menuItem)
                .build();

        // Update the total Price in cart
        double cartTotal = 0;
        // Previous FoodItems
        for(FoodItem foodItem1 : cart.getFoodItems()){
            cartTotal += foodItem1.getTotalCost();
        }
        // Current FoodItem
        cartTotal += foodItem.getTotalCost();

        // Set the total Price in cart
        cart.setCartTotal(cartTotal);

        // Save the FoodItem to Db
        FoodItem savedFoodItem = foodItemRepository.save(foodItem);

        // add FoodItem to cart and MenuItem
        cart.getFoodItems().add(savedFoodItem);
        menuItem.getFoodItems().add(savedFoodItem);
        Cart savedCart = cartRepository.save(cart);
        MenuItem savedMenuItem = menuItemRepository.save(menuItem);

        return CartTransformer.CartToCartResponse(cart);
    }
}
