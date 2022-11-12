package com.search.demo.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum RequestStatus {
    SUCCESS("success"),
    WARNING("warning"),
    ERROR("error");

    @Getter
    private final String status;
}
