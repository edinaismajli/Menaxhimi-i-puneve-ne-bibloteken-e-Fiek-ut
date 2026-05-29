package library_fiek.services;

import library_fiek.database.DatabaseConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseService {
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DatabaseConfig.DATABASE_URL);
    }
}
