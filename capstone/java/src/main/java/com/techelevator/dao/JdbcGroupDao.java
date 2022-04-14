package com.techelevator.dao;


import com.techelevator.model.Restaurant;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class JdbcGroupDao implements GroupDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcGroupDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int createGroup(String location, Date date) {


        Random rnd = new Random();
        int groupId = 100000 + rnd.nextInt(900000);
        //todo make sure it works

        String sql = "insert into groups (group_id, location, expiration_date)" +
                " values (?,?,?);";

        jdbcTemplate.update(sql,
                groupId,
                location,
                date);

        return groupId;
    }

    public void addToFavorites(int group_id, String restaurant_id) {

        String sql = "insert into favorites (group_id, restaurant_id)" +
                " values (?,?);";

        jdbcTemplate.update(sql,
                group_id,
                restaurant_id);
    }

    public List<Restaurant> getRestaurantIdsByGroupId(int group_id){
        List<Restaurant> listOfRestaurants= new ArrayList<>();

        String sql = "select restaurant_id" +
                    " from favorites" +
                    " where favorites.group_id = ?;";

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


