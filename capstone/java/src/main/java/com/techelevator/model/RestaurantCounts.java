package com.techelevator.model;

public class RestaurantCounts {

    private String restaurantId;
    private int counter;

    public RestaurantCounts(String restaurantId, int counter){
        this.restaurantId = restaurantId;
        this.counter = counter;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
