package com.techelevator.dao;


import com.techelevator.model.Restaurant;
import com.techelevator.model.RestaurantCounts;
import com.techelevator.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

        jdbcTemplate.update(sql, group_id, restaurant_id);
    }

    public void addToCounter(int group_id, String restaurant_id){
        String sql = "UPDATE group_favorites " +
                     "SET counter = counter + 1 "+
                     "WHERE group_id = ? AND restaurant_id = ?;";
        jdbcTemplate.update(sql, group_id, restaurant_id);
    }

    public List<RestaurantCounts> getRestaurantIdsByGroupId(int group_id) throws Exception {
        DateTimeFormatter dt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        List<RestaurantCounts> listOfRestaurants= new ArrayList<>();
        Date today = Date.valueOf(now.format(dt));
        String sql = "select expiration_date from groups where group_id = ?";
        Date expiration = jdbcTemplate.queryForObject(sql, Date.class, group_id);

        if(today.after(expiration)) {
            throw new Exception("You can not access this group after the event date");
        }

        sql = "select restaurant_id, counter" +
                    " from group_favorites" +
                    " where group_favorites.group_id = ? and counter > 0;";

        SqlRowSet listOfIds = jdbcTemplate.queryForRowSet(sql, group_id);

        while (listOfIds.next()){
            listOfRestaurants.add(mapRowToRestaurant(listOfIds));
        }
        return listOfRestaurants;

    }


    private RestaurantCounts mapRowToRestaurant(SqlRowSet s){
        RestaurantCounts restaurant = new RestaurantCounts(s.getString("restaurant_id"), s.getInt("counter"));

        return restaurant;
    }

}


