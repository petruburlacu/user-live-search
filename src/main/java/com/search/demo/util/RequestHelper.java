package com.search.demo.util;

import com.search.demo.constants.RequestStatus;
import com.search.demo.model.ResponseAPI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class RequestHelper {

    public static ResponseEntity<ResponseAPI> responseEntityHelper(String message, Exception ex) {
        return new ResponseEntity<>(new ResponseAPI(RequestStatus.ERROR, message, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
