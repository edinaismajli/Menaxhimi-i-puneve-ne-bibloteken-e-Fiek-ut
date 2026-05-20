package library_fiek.database;

import library_fiek.services.DatabaseService;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class DatabaseInitializer {
    private static final List<String> MIGRATIONS = List.of(
            "/migrations/0001_create_library_tables.sql",
            "/migrations/0002_add_indexes_and_constraints.sql",
            "/migrations/0003_seeds.sql"
    );

    public static void initialize() {
        try (Connection connection = DatabaseService.getConnection()) {
            createMigrationsTable(connection);

            for (String migration : MIGRATIONS) {
                if (!isMigrationExecuted(connection, migration)) {
                    executeSqlFile(connection, migration);
                    markMigrationExecuted(connection, migration);
                }
            }
        } catch (Exception e) {
            System.out.println("Database initialization failed: " + e.getMessage());
        }
    }

    private static void createMigrationsTable(Connection connection) throws Exception {
        String sql = """
                CREATE TABLE IF NOT EXISTS schema_migrations (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    migration_name VARCHAR(150) NOT NULL UNIQUE,
                    executed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                )
                """;

        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    private static boolean isMigrationExecuted(Connection connection, String migration) throws Exception {
        String sql = "SELECT COUNT(*) FROM schema_migrations WHERE migration_name = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, migration);

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next() && resultSet.getInt(1) > 0;
            }
        }
    }

    private static void markMigrationExecuted(Connection connection, String migration) throws Exception {
        String sql = "INSERT INTO schema_migrations(migration_name) VALUES (?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, migration);
            statement.executeUpdate();
        }
    }