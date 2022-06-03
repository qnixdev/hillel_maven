package com.hw16.repository;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.hw16.model.Author;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class AuthorRepository {
    private final File repository = new File("src/main/resources/hw16/author.json");
    private final JsonMapper mapper = new JsonMapper();

    public AuthorRepository() {}

    public void save(Author author) {
        List<Author> authors = this.findAll();
        authors.add(author);

        try {
            this.mapper.writeValue(this.repository, authors);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public Optional<Author> findOneByName(String name) {
        return this.findAll()
            .stream()
            .filter(a -> Objects.equals(a.getLastName(), name))
            .findFirst()
        ;
    }

    public List<Author> findAll() {
        CollectionType type = this.mapper.getTypeFactory()
            .constructCollectionType(List.class, Author.class)
        ;

        try {
            List<Author> list = this.mapper.readValue(this.repository, type);

            if (list.size() == 0) {
                return List.of();
            }

            return list;
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return List.of();
    }

    public void remove(Author author) {
        List<Author> newList = this.findAll()
            .stream()
            .filter(a -> !a.equals(author))
            .toList()
        ;

        try {
            this.mapper.writeValue(this.repository, newList);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}