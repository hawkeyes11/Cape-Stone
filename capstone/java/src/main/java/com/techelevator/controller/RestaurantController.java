package com.techelevator.controller;


import com.techelevator.Services.RestaurantServiceAPI;
import com.techelevator.model.Restaurant;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class RestaurantController {
    private RestaurantServiceAPI restaurantServiceAPI;

    public RestaurantController(RestaurantServiceAPI restaurantServiceAPI) {
        this.restaurantServiceAPI = restaurantServiceAPI;
    }


    @GetMapping("/test")
    public List<Restaurant> test() {
        return restaurantServiceAPI.getRestaurants("44260");
    }


}
