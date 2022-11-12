package com.search.demo.model;

import com.search.demo.constants.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseAPI {
    @NonNull
    private RequestStatus status;
    @NonNull
    private String message;
    private Object responseObject;
}
