package com.search.demo.util;

import com.search.demo.constants.RequestStatus;
import com.search.demo.model.ResponseAPI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class RequestHelper {

    /**
     * Generic reusable helper method to generate ResponseEntity with custom ResponseAPI object for ERROR handling
     * @param message custom message
     * @param ex custom exception
     * @return Bad HTTP request ResponseEntity object with custom ResponseAPI
     */
    public static ResponseEntity<ResponseAPI> responseEntityHelper(String message, Exception ex) {
        return new ResponseEntity<>(new ResponseAPI(RequestStatus.ERROR, message, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
