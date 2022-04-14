package com.techelevator.dao;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.Random;

@Component
public class JdbcGroupDao implements GroupDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcGroupDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createGroup(String location, Date date){


        Random rnd = new Random();
        int groupId = 100000 + rnd.nextInt(900000);
        //todo make sure it works

        String insertGroup = "insert into groups (group_id, location, expiration_date) values (?,?,?)";

//        jdbcTemplate.update()




    }
}
