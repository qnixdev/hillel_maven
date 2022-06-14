package com.hw20.dao;

import com.hw20.model.Author;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDAO extends AbstractDAO implements FindDAO<Author> {
    private static final String SQL_INSERT = "INSERT INTO author (id, first_name, last_name) VALUES (nextval('author_id_seq'), ?, ?)";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM author WHERE id = ?";
    private static final String SQL_SELECT_ALL = "SELECT * FROM author";

    public void save(Author author) {
        try (PreparedStatement statement = this.connection.prepareStatement(SQL_INSERT)) {
            statement.setString(1, author.getFirstName());
            statement.setString(2, author.getLastName());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Author find(int id) {
        try (PreparedStatement statement = this.connection.prepareStatement(SQL_SELECT_BY_ID)) {
            statement.setInt(1, id);
            ResultSet result = this.execute(statement);

            if (result.next()) {
                return this.build(result);
            }

            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Author findOneBy(String param, String criteria) {
        try (PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM author WHERE " + param + " = ?")) {
            statement.setString(1, criteria);
            ResultSet result = this.execute(statement);

            if (result.next()) {
                return this.build(result);
            }

            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Author> findAll() {
        try (PreparedStatement statement = this.connection.prepareStatement(SQL_SELECT_ALL)) {
            ResultSet result = this.execute(statement);
            List<Author> list = new ArrayList<>();

            while (result.next()) {
                list.add(this.build(result));
            }

            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Author build(ResultSet result) throws SQLException {
        return Author.builder()
            .id(result.getInt("id"))
            .firstName(result.getString("first_name"))
            .lastName(result.getString("last_name"))
            .build()
        ;
    }
}