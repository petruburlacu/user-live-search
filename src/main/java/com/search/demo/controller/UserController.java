package com.search.demo.controller;

import com.search.demo.exception.InputValidationException;
import com.search.demo.model.ResponseAPI;
import com.search.demo.model.User;
import com.search.demo.service.UserService;
import com.search.demo.util.InputHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    public static final String USERS_ENDPOINT = "/users";

    @Autowired
    private UserService userService;

    @GetMapping(USERS_ENDPOINT + "/{login}")
    public ResponseEntity<ResponseAPI> searchUsers(@PathVariable String login) throws InputValidationException {
        LOGGER.info("Searching for users HTTP Get /users/{}", login);
        InputHelper.validateUserSearchInput(login);
        return userService.searchUsers(login);
    }

    @GetMapping(USERS_ENDPOINT)
    public ResponseEntity<ResponseAPI> fetchUsers() {
        LOGGER.info("Requested to fetch all users.");
        return userService.fetchUsers();
    }

    @PostMapping(USERS_ENDPOINT)
    public ResponseEntity<ResponseAPI> addUser(@Valid @RequestBody User user) {
        LOGGER.info("Requested to add new user: " + user.toString());
        // binding validation here
        return userService.addUser(user);
    }

}
