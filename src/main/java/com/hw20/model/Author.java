package com.hw20.model;

import lombok.Builder;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collection;

@Builder
@ToString
public class Author {
    private Integer id;
    private String firstName;
    private String lastName;
    private Collection<Book> books;

    public Author() {
        this.books = new ArrayList<Book>();
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Collection<Book> getBooks() {
        return this.books;
    }

    public void addBook(Book book) {
        if (!this.books.contains(book)) {
            this.books.add(book);
            book.setAuthor(this);
        }
    }

    public void removeBook(Book book) {
        if (this.books.remove(book)) {
            if (book.getAuthor() == this) {
                book.setAuthor(null);
            }
        }
    }
}