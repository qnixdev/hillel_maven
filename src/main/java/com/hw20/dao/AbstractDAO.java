package com.hw20.dao;

import com.hw20.config.DatabaseConf;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

abstract public class AbstractDAO {
    protected final Connection connection;

    public AbstractDAO() {
        this.connection = new DatabaseConf().getDBConnection();
    }

    public ResultSet execute(PreparedStatement statement) {
        try {
            return statement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                this.connection.close();
            } catch (SQLException e) {
                System.out.println("Unable to close database connection!");
            }
        }
    }
}