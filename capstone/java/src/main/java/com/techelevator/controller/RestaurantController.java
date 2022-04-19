package com.techelevator.controller;


import com.techelevator.Services.RestaurantServiceAPI;
import com.techelevator.dao.JdbcGroupDao;
import com.techelevator.model.Category;
import com.techelevator.model.Restaurant;
import com.techelevator.model.RestaurantCounts;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin
public class RestaurantController {
    private RestaurantServiceAPI restaurantServiceAPI;
    private JdbcGroupDao jdbcGroupDao;

    public RestaurantController(RestaurantServiceAPI restaurantServiceAPI, JdbcGroupDao jdbcGroupDao) {
        this.restaurantServiceAPI = restaurantServiceAPI;
        this.jdbcGroupDao = jdbcGroupDao;
    }

    @GetMapping("/restaurants")
    public List<Restaurant> getRestaurants(@RequestParam(value = "location") String location) {
        List<Restaurant> restaurantList = restaurantServiceAPI.getRestaurants(location);
        Collections.shuffle(restaurantList);
        return restaurantList;
    }

    @GetMapping("/categories")
    public List<String> getCategories() {
        List<String> stringList = new ArrayList<>(Arrays.asList("Acai Bowls", "Backshop", "Bagels", "Bakeries", "beer_and_wine", "Bento", "Beverage Store", "Breweries", "Brewpubs",
                "Bubble Tea", "Butcher", "CSA", "Chimney Cakes", "Churros", "Cideries", "Coffee & Tea", "Coffee & Tea Supplies", "Coffee Roasteries", "Convenience Stores", "Cupcakes"
        , "Custom Cakes", "Delicatessen", "Desserts", "Distilleries", "diyfood", "Donairs", "Donuts", "Empanadas", "Farmers Market", "Fishmonger", "Food Delivery Services", "Food Trucks",
                "Friterie", "Gelato", "Grocery", "Hawker Centre", "Honey", "icecream", "Imported Food", "International Grocery", "Internet Cafes", "juicebars", "Kiosk", "Kombucha", "Meaderies",
                "Milkshake Bars", "Organic Stores", "Cake Shop", "Piadina", "Poke", "Pretzels", "Shaved Ice", "Shaved Snow", "Smokehouse"));
        return stringList;
    }

    @PostMapping("/user/categories")
    public void addCategory(@RequestBody Category category, Principal principal) {
        System.out.println(category.getCategory());
        jdbcGroupDao.addCategory(principal.getName(), category.getCategory());
    }

    @GetMapping("/{username}/categories")
    public List<String> getUserCategories(@PathVariable() String username) {
        return jdbcGroupDao.getUserCategories(username);
    }

    @GetMapping("/create")
    //year-month-day
    public String createInvite(@RequestParam(value = "location") String location, @RequestParam(value = "expiration")Date date) {
        int id = jdbcGroupDao.createGroup(location, date);

        List<Restaurant> restaurants = getRestaurants(location);

        for (Restaurant restaurant : restaurants){
            jdbcGroupDao.addToFavorites(id, restaurant.getRestaurantId());
        }

        return "http://localhost:8080/join/" + id;
    }


    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/{id}")
    public void addRestaurantToGroupList(@PathVariable(value = "id") int id, @RequestBody Restaurant restaurant) {
//        jdbcGroupDao.addToFavorites(id, restaurant.getRestaurantId());
         jdbcGroupDao.addToCounter(id, restaurant.getRestaurantId());
    }
    //todo make sure i didnt put this in wrong spot


    @GetMapping("/join/{id}")
    public List<RestaurantCounts> getGroupRestaurants(@PathVariable() int id) throws Exception {
        return jdbcGroupDao.getRestaurantIdsByGroupId(id);
    }


}
