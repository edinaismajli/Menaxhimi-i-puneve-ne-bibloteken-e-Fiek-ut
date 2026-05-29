package library_fiek.repositories;

import library_fiek.enums.LoanStatus;
import library_fiek.models.Loan;
import library_fiek.services.DatabaseService;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LoanRepository {
    public List<Loan> findAll() {
        String sql = "SELECT * FROM loans ORDER BY id DESC";
        List<Loan> loans = new ArrayList<>();

        try (
                Connection connection = DatabaseService.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                loans.add(mapLoan(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("Find loans failed: " + e.getMessage());
        }

        return loans;
    }

    public void insert(int bookId, int memberId, LocalDate dueDate) {
        String sql = """
                INSERT INTO loans(book_id, member_id, loan_date, due_date, status)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (
                Connection connection = DatabaseService.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setInt(1, bookId);
            statement.setInt(2, memberId);
            statement.setString(3, LocalDate.now().toString());
            statement.setString(4, dueDate.toString());
            statement.setString(5, LoanStatus.ACTIVE.name());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Insert loan failed: " + e.getMessage());
        }
    }

    public void markReturned(int id) {
        String sql = """
                UPDATE loans
                SET return_date = ?, status = ?
                WHERE id = ?
                """;

        try (
                Connection connection = DatabaseService.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, LocalDate.now().toString());
            statement.setString(2, LoanStatus.RETURNED.name());
            statement.setInt(3, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Return loan failed: " + e.getMessage());
        }
    }

    public void updateOverdueLoans() {
        String sql = """
                UPDATE loans
                SET status = ?
                WHERE status = ? AND due_date < ? AND return_date IS NULL
                """;

        try (
                Connection connection = DatabaseService.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, LoanStatus.OVERDUE.name());
            statement.setString(2, LoanStatus.ACTIVE.name());
            statement.setString(3, LocalDate.now().toString());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Update overdue loans failed: " + e.getMessage());
        }
    }

    private Loan mapLoan(ResultSet resultSet) throws SQLException {
        return new Loan(
                resultSet.getInt("id"),
                resultSet.getInt("book_id"),
                resultSet.getInt("member_id"),
                resultSet.getString("loan_date"),
                resultSet.getString("due_date"),
                resultSet.getString("return_date"),
                resultSet.getString("status")
        );
    }
}