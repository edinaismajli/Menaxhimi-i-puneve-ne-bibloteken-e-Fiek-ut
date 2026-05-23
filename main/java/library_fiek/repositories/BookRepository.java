package library_fiek.repositories;

import library_fiek.models.Book;
import library_fiek.services.DatabaseService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {
    public List<Book> findAll() {
        String sql = "SELECT * FROM books ORDER BY id DESC";
        List<Book> books = new ArrayList<>();

        try (
                Connection connection = DatabaseService.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                books.add(mapBook(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("Find books failed: " + e.getMessage());
        }

        return books;
    }

    public List<Book> search(String keyword) {
        String sql = """
                SELECT * FROM books
                WHERE title LIKE ? OR author LIKE ? OR category LIKE ? OR isbn LIKE ?
                ORDER BY id DESC
                """;

        List<Book> books = new ArrayList<>();

        try (
                Connection connection = DatabaseService.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            String value = "%" + keyword + "%";

            statement.setString(1, value);
            statement.setString(2, value);
            statement.setString(3, value);
            statement.setString(4, value);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    books.add(mapBook(resultSet));
                }
            }
        } catch (SQLException e) {
            System.out.println("Search books failed: " + e.getMessage());
        }

        return books;
    }

    public void insert(Book book) {
        String sql = """
                INSERT INTO books(title, author, category, isbn, quantity, available_quantity)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (
                Connection connection = DatabaseService.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getCategory());
            statement.setString(4, book.getIsbn());
            statement.setInt(5, book.getQuantity());
            statement.setInt(6, book.getAvailableQuantity());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Insert book failed: " + e.getMessage());
        }
    }

    public void update(Book book) {
        String sql = """
                UPDATE books
                SET title = ?, author = ?, category = ?, isbn = ?, quantity = ?, available_quantity = ?
                WHERE id = ?
                """;

        try (
                Connection connection = DatabaseService.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getCategory());
            statement.setString(4, book.getIsbn());
            statement.setInt(5, book.getQuantity());
            statement.setInt(6, book.getAvailableQuantity());
            statement.setInt(7, book.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Update book failed: " + e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM books WHERE id = ?";

        try (
                Connection connection = DatabaseService.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Delete book failed: " + e.getMessage());
        }
    }

    private Book mapBook(ResultSet resultSet) throws SQLException {
        return new Book(
                resultSet.getInt("id"),
                resultSet.getString("title"),
                resultSet.getString("author"),
                resultSet.getString("category"),
                resultSet.getString("isbn"),
                resultSet.getInt("quantity"),
                resultSet.getInt("available_quantity")
        );
    }
}

