package com.hw20.dao;

import com.hw20.model.Author;
import com.hw20.model.Book;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAO extends AbstractDAO implements FindDAO<Book> {
    private static final String SQL_INSERT = "INSERT INTO book (id, author_id, title) VALUES (nextval('book_id_seq'), ?, ?)";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM book WHERE id = ?";
    private static final String SQL_SELECT_BY_AUTHOR = "SELECT * FROM book AS b INNER JOIN author a ON a.id = b.author_id WHERE author_id = ?";
    private static final String SQL_SELECT_ALL = "SELECT * FROM book";
    private final AuthorDAO authorDAO;

    public BookDAO() {
        this.authorDAO = new AuthorDAO();
    }

    public void save(Book book) {
        try (PreparedStatement statement = this.connection.prepareStatement(SQL_INSERT)) {
            statement.setObject(1, book.getAuthor());
            statement.setString(2, book.getTitle());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Book find(int id) {
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
    public Book findOneBy(String param, String criteria) {
        try (PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM book WHERE " + param + " = ?")) {
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
    public List<Book> findAll() {
        try (PreparedStatement statement = this.connection.prepareStatement(SQL_SELECT_ALL)) {
            ResultSet result = this.execute(statement);
            List<Book> list = new ArrayList<>();

            while (result.next()) {
                list.add(this.build(result));
            }

            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Book> findByAuthor(Author author) {
        try (PreparedStatement statement = this.connection.prepareStatement(SQL_SELECT_BY_AUTHOR)) {
            statement.setInt(1, author.getId());
            ResultSet result = this.execute(statement);
            List<Book> list = new ArrayList<>();

            while (result.next()) {
                list.add(this.build(result));
            }

            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Book build(ResultSet result) throws SQLException {
        return Book.builder()
            .id(result.getInt("id"))
            .author(this.authorDAO.find(result.getInt("author_id")))
            .title(result.getString("title"))
            .build()
        ;
    }
}