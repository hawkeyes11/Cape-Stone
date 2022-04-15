package com.techelevator.dao;

import com.techelevator.model.Restaurant;

import java.sql.Date;
import java.util.List;

public interface GroupDao {

    int createGroup(String location, Date date);

    void addToFavorites(int group_id, String restaurant_id);

    List<Restaurant> getRestaurantIdsByGroupId(int group_id);

    void addCategory(String username, String category);

    List<String> getUserCategories(String username);
}
