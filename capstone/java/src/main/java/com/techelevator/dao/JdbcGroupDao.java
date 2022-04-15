package com.techelevator.dao;


import com.techelevator.model.Restaurant;
import com.techelevator.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class JdbcGroupDao implements GroupDao {

    private final JdbcTemplate jdbcTemplate;
    private UserDao userDao;

    public JdbcGroupDao(JdbcTemplate jdbcTemplate, UserDao userDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.userDao = userDao;
    }

    public int createGroup(String location, Date date) {
        Random rnd = new Random();
        int groupId = 100000 + rnd.nextInt(900000);
        String sql = "insert into groups (group_id, location, expiration_date)" +
                " values (?,?,?);";
        jdbcTemplate.update(sql,
                groupId,
                location,
                date);
        return groupId;
    }

    public void addCategory(String username, String category) {
        int id = userDao.findIdByUsername(username);
        String sql = "insert into user_categories (user_id, category)" +
                " values (?,?);";

        jdbcTemplate.update(sql,
                id,
                category);
    }

    public List<String> getUserCategories(String username) {
        List<String> categoryList = new ArrayList<>();
        String sql = "select category" +
                " from user_categories" +
                " where user_id = ?;";

        int id = userDao.findIdByUsername(username);

        SqlRowSet listOfCategories = jdbcTemplate.queryForRowSet(sql, id);

        while (listOfCategories.next()){
            categoryList.add(listOfCategories.getString("category"));
        }
        return categoryList;
    }

    public void addToFavorites(int group_id, String restaurant_id) {

        String sql = "insert into group_favorites (group_id, restaurant_id)" +
                " values (?,?);";

        jdbcTemplate.update(sql,
                group_id,
                restaurant_id);
    }

    public List<Restaurant> getRestaurantIdsByGroupId(int group_id){
        List<Restaurant> listOfRestaurants= new ArrayList<>();

        String sql = "select restaurant_id" +
                    " from group_favorites" +
                    " where group_favorites.group_id = ?;";

        SqlRowSet listOfIds = jdbcTemplate.queryForRowSet(sql, group_id);

        while (listOfIds.next()){
            listOfRestaurants.add(mapRowToRestaurant(listOfIds));
        }
        return listOfRestaurants;

    }

    private Restaurant mapRowToRestaurant(SqlRowSet s){
        Restaurant restaurant = new Restaurant(s.getString("restaurant_id"));
        return restaurant;
    }

}


