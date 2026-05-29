package library_fiek.repositories;

import library_fiek.models.User;
import library_fiek.services.DatabaseService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {
    public User findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";

        try (
                Connection connection = DatabaseService.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new User(
                            resultSet.getInt("id"),
                            resultSet.getString("username"),
                            resultSet.getString("password"),
                            resultSet.getString("role")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Find user failed: " + e.getMessage());
        }

        return null;
    }
}
