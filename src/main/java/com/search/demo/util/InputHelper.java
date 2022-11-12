package com.search.demo.util;

import com.search.demo.exception.InputValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InputHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(InputHelper.class);

    private static final String VALIDATION_PATTERN = "^[a-zA-Z\\d-_]+$";

    /**
     * Validates user search input and throws custom exception when not valid
     * @param input user search input
     * @throws InputValidationException custom project exception for input validation,
     * returns ResponseEntity<ResponseAPI>
     */
    public static void validateUserSearchInput(String input) throws InputValidationException {
        LOGGER.info("Validating user input [{}]",  input);
        if (!input.matches(VALIDATION_PATTERN)) {
            LOGGER.warn("User input did not pass validation. Throwing new InputValidationException.");
            throw new InputValidationException("User search input must be alphanumeric and can contain underscore [ _ ] or hyphen [ - ].");
        }
        LOGGER.info("User input passed validation. Continuing with request.");
    }
}
