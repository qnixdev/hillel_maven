package com.hw16.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Book {
    private String title;
    private Author author;

    public Book() {}

    public Book(String title, Author author) {
        this.title = title;
        this.author = author;
    }
}