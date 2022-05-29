package com.hw16.repository;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.hw16.model.Author;
import com.hw16.model.Book;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class BookRepository {
    private final File repository = new File("src/main/resources/hw16/book.json");
    private final JsonMapper mapper = new JsonMapper();

    public BookRepository() {}

    public void save(Book book) {
        List<Book> books = this.findAll();
        books.add(book);

        try {
            this.mapper.writeValue(this.repository, books);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public Optional<Book> findOneByTitle(String title) {
        return this.findAll()
            .stream()
            .filter(b -> Objects.equals(b.getTitle(), title))
            .findFirst()
        ;
    }

    public List<Book> findByAuthor(Author author) {
        List<Book> list = this.findAll();
        List<Book> books = list
            .stream()
            .filter(b -> Objects.equals(b.getAuthor(), author))
            .toList()
        ;

        if (books.size() == 0) {
            return List.of();
        }

        return books;
    }

    public List<Book> findAll() {
        CollectionType type = this.mapper.getTypeFactory()
            .constructCollectionType(List.class, Book.class)
        ;

        try {
            List<Book> list = this.mapper.readValue(this.repository, type);

            if (list.size() == 0) {
                return List.of();
            }

            return list;
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return List.of();
    }
}