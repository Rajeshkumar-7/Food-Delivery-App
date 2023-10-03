package com.example.FoodDeliveryApp.service.implementation;

import com.example.FoodDeliveryApp.Enum.FoodCategory;
import com.example.FoodDeliveryApp.dto.response.MenuItemResponse;
import com.example.FoodDeliveryApp.model.MenuItem;
import com.example.FoodDeliveryApp.repository.MenuItemRepository;
import com.example.FoodDeliveryApp.service.MenuItemService;
import com.example.FoodDeliveryApp.transformer.MenuItemTransformer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuItemServiceImpl implements MenuItemService {

    final MenuItemRepository menuItemRepository;

    public MenuItemServiceImpl(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    @Override
    public List<MenuItemResponse> getMenuItemByCategory(String category) {

        // Get the respective enum value
        FoodCategory foodCategory = FoodCategory.valueOf(category);

        // Get all Entities with given category
        List<MenuItem> menuItems = menuItemRepository.findByFoodCategory(foodCategory);

        // Convert Entities to DTO
        List<MenuItemResponse> menuItemRespons = menuItems.stream()
                .map(foodItem -> MenuItemTransformer.MenuItemToMenuItemResponse(foodItem))
                .collect(Collectors.toList());

        return menuItemRespons;
    }

    @Override
    public List<MenuItemResponse> getVegFoodItems() {

        // Get all Veg FoodItems
        List<MenuItem> menuItems = menuItemRepository.findByVegTrue();

        // Convert Entities to DTO
        List<MenuItemResponse> menuItemRespons = menuItems.stream()
                .map(foodItem -> MenuItemTransformer.MenuItemToMenuItemResponse(foodItem))
                .collect(Collectors.toList());

        return menuItemRespons;

    }

    @Override
    public List<MenuItemResponse> getNonVegFoodItems() {

        // Get all NonVeg FoodItems
        List<MenuItem> menuItems = menuItemRepository.findByVegFalse();

        // Convert Entities to DTO
        List<MenuItemResponse> menuItemRespons = menuItems.stream()
                .map(foodItem -> MenuItemTransformer.MenuItemToMenuItemResponse(foodItem))
                .collect(Collectors.toList());

        return menuItemRespons;
    }
}
