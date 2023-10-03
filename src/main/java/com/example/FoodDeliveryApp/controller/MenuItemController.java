package com.example.FoodDeliveryApp.controller;

import com.example.FoodDeliveryApp.dto.response.MenuItemResponse;
import com.example.FoodDeliveryApp.service.implementation.MenuItemServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menuItem")
public class MenuItemController {

    final MenuItemServiceImpl menuItemService;

    public MenuItemController(MenuItemServiceImpl menuItemService) {
        this.menuItemService = menuItemService;
    }


    @GetMapping("/get/MenuItem/category/{category}")
    public ResponseEntity getMenuItemByCategory(@PathVariable("category") String foodCategory){
        try{
            List<MenuItemResponse> menuItemRespons = menuItemService.getMenuItemByCategory(foodCategory);
            return new ResponseEntity(menuItemRespons,HttpStatus.ACCEPTED);
        }
        catch (IllegalArgumentException e){
            return new ResponseEntity<>( "Given Category does not Exist " , HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/veg")
    public ResponseEntity getVegFoodItems(){

        List<MenuItemResponse> menuItemRespons = menuItemService.getVegFoodItems();
        return new ResponseEntity(menuItemRespons,HttpStatus.ACCEPTED);
    }

    @GetMapping("/get/nonVeg")
    public ResponseEntity getNonVegFoodItems(){

        List<MenuItemResponse> menuItemRespons = menuItemService.getNonVegFoodItems();
        return new ResponseEntity(menuItemRespons,HttpStatus.ACCEPTED);
    }
}
