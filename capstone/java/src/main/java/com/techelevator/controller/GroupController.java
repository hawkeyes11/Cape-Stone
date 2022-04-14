package com.techelevator.controller;

import com.techelevator.dao.GroupDao;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class GroupController {

    private GroupDao groupDao;

    public GroupController(GroupDao groupDao){
        this.groupDao = groupDao;
    }

//    @RequestMapping(value = "/group", method = RequestMethod.POST)



}
