package com.search.demo.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Document
@Getter
@Setter
@AllArgsConstructor
@ToString
public class User {

    @Id
    private int id;
    @Size(min = 2, max = 8, message = "User login must be between 2 and 8 characters.")
    @NotBlank(message = "User login is required.")
    @NonNull
    private String login;
    private String firstName;
    private String lastName;
}
