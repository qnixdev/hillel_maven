package com.hw20.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Author {
    private String firstName;
    private String lastName;

    public Author() {}

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}