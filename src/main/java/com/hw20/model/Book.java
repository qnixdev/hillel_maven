package com.hw20.model;

import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
public class Book {
    private Integer id;
    private String title;
    private Author author;

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}