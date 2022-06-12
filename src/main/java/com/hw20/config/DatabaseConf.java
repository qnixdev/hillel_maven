package com.hw20.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConf {
    private final String username = "postgres";
    private final String password = "q1w2e3r4";
    private final String url = "jdbc:postgresql://localhost:49159/hillel_db";
    private final Connection connection;

    public DatabaseConf() {
        this.connection = createConnection();
    }

    public Connection getDBConnection() {
        return this.connection;
    }

    private Connection createConnection() {
        try {
            return DriverManager.getConnection(
                this.url,
                this.username,
                this.password
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}