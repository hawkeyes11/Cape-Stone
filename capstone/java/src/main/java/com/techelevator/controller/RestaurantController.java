package com.techelevator.controller;


import com.techelevator.Services.RestaurantServiceAPI;
import com.techelevator.model.Restaurant;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class RestaurantController {
    private RestaurantServiceAPI restaurantServiceAPI;

    public RestaurantController(RestaurantServiceAPI restaurantServiceAPI) {
        this.restaurantServiceAPI = restaurantServiceAPI;
    }

    @GetMapping("/restaurants")
    public List<Restaurant> getRestaurants(@RequestParam(value = "location") String location) {
        return restaurantServiceAPI.getRestaurants(location);
    }


//    @GetMapping("/create/{id}")
//    public String createInvite(@PathVariable int id, @RequestParam(value = "location") String location) {
//        //database logic to create a group room for finding restaurants needs: expiration date, restaurant id of liked restaurants tied to user ids, room id
//        //have backend service that checks every 2 days for expired rooms and deletes them
//        return "http://localhost:8080/join/" + id;
//    }

//    @GetMapping("/join/{id}")
//    public List<Restaurant> getJoin(@PathVariable int id) {
//        // get location from database where id == db group id
//        return restaurantServiceAPI.getRestaurants(location);
//    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/{id}")
    public void addRestaurantToGroupList(@RequestBody Restaurant restaurant) {
        //add data from restaurant to database
    }

//    @GetMapping("/{id}")
//    public List<Restaurant> getGroupRestaurants(@RequestParam(value = "location") String location) {
//        //get all restaurants in the group's database of liked restaurants
//    }


}
