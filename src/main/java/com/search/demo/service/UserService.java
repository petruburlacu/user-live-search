package com.search.demo.service;

import com.search.demo.constants.RequestStatus;
import com.search.demo.exception.InputValidationException;
import com.search.demo.model.ResponseAPI;
import com.search.demo.model.User;
import com.search.demo.repository.UserRepository;
import com.search.demo.util.RequestHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    /** Class Logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    /**
     * The autowired user repository
     */
    @Autowired
    UserRepository userRepository;

    /**
     * Retrieves user(s) based on the provided string input
     * @param login string user input
     * @return Custom ResponseAPI in ResponseEntity
     */
    public ResponseEntity<ResponseAPI> searchUsers(String login) {
        LOGGER.info("Inside searchUsers({})", login);
        return new ResponseEntity<>(
                new ResponseAPI(
                        RequestStatus.SUCCESS,
                        "Successfully retrieved users.",
                        userRepository.findByLogin(login)
                ),
                HttpStatus.OK
        );
    }

    /**
     * Retrieves all available users
     * @return Custom ResponseAPI in ResponseEntity with HttpStatus.OK on success and HttpStatus.INTERNAL_SERVER_ERROR
     */
    public ResponseEntity<ResponseAPI> fetchUsers() {
        LOGGER.info("Inside fetchUsers()");
        try {
            return new ResponseEntity<>(
                    new ResponseAPI(
                            RequestStatus.SUCCESS,
                            "Successfully retrieved users.",
                            userRepository.findAll()
                    ),
                    HttpStatus.OK
            );
        } catch (Exception exception) {
            LOGGER.error("Exception occurred while fetching all users.", exception);
            return RequestHelper.responseEntityHelper("Failed to retrieve users.", exception);
        }
    }

    /**
     * Inserts new user to the database
     * @param user User object from payload
     * @return Custom ResponseAPI in ResponseEntity with HttpStatus.OK on success and HttpStatus.INTERNAL_SERVER_ERROR
     */
    public ResponseEntity<ResponseAPI> addUser(User user) {
        LOGGER.info("Inside addUser()");
        try {
            return new ResponseEntity<>(
                    new ResponseAPI(
                            RequestStatus.SUCCESS,
                            "Successfully added new user.",
                            userRepository.insert(user)
                    ),
                    HttpStatus.OK
            );
        } catch (Exception exception) {
            LOGGER.error("Exception occurred while fetching all users.", exception);
            return RequestHelper.responseEntityHelper("Failed to insert user. Please contact an administrator.", exception);
        }
    }
}
