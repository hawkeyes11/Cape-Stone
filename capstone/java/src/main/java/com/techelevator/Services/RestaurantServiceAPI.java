package com.techelevator.Services;

import com.techelevator.model.Restaurant;

import java.util.List;

public interface RestaurantServiceAPI {


    public List<Restaurant> getRestaurants(String zipcode);

    public Restaurant getRestaurantByRestaurantId(String id);

}
