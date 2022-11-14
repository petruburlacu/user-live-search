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

    /** Class Logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    /** The USERS_ENDPOINT constant */
    public static final String USERS_ENDPOINT = "/users";

    /**
     * The autowired user service
     */
    @Autowired
    private UserService userService;

    /**
     * Retrieves user(s) based on the provided string input
     * @param login string user input
     * @return Custom ResponseAPI in ResponseEntity
     * @throws InputValidationException custom validation exception
     */
    @GetMapping(USERS_ENDPOINT + "/{login}")
    public ResponseEntity<ResponseAPI> searchUsers(@PathVariable String login) throws InputValidationException {
        LOGGER.info("Searching for users HTTP Get /users/{}", login);
        InputHelper.validateUserSearchInput(login);
        return userService.searchUsers(login);
    }

    /**
     * Retrieves all available users
     * @return Custom ResponseAPI in ResponseEntity
     */
    @GetMapping(USERS_ENDPOINT)
    public ResponseEntity<ResponseAPI> fetchUsers() {
        LOGGER.info("Requested to fetch all users.");
        return userService.fetchUsers();
    }

    /**
     * Adds new user
     * Validates the User object on request
     * and throws custom MethodArgumentNotValidException on bad request payload
     * @param user User request payload
     * @return Custom ResponseAPI in ResponseEntity
     */
    @PostMapping(USERS_ENDPOINT)
    public ResponseEntity<ResponseAPI> addUser(@Valid @RequestBody User user) {
        LOGGER.info("Requested to add new user: {}",  user.toString());
        return userService.addUser(user);
    }

}
