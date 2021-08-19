package com.tul.shopping.controller;

import com.tul.shopping.model.User;
import com.tul.shopping.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
public class UserController {
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/getUserDetails")
    public User getDetails(String username){
        User user = userRepository.findByUsername(username);
        if (user == null){
            LOG.error("Invalid username");
        }
        return user;
    }

    @RequestMapping("/hello")
    public String hello() {
        return "Hello World!!!!";
    }
}
