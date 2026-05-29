package library_fiek.services;

import library_fiek.models.DashboardStats;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DashboardService {
    public DashboardStats getStats() {
        int totalBooks = getCount("SELECT COUNT(*) FROM books");
        int totalMembers = getCount("SELECT COUNT(*) FROM members");
        int activeLoans = getCount("SELECT COUNT(*) FROM loans WHERE status = 'ACTIVE'");
        int overdueLoans = getCount("SELECT COUNT(*) FROM loans WHERE status = 'OVERDUE'");

        return new DashboardStats(totalBooks, totalMembers, activeLoans, overdueLoans);
    }

    public List<PieChart.Data> getBookCategoryData() {
        String sql = """
                SELECT category, COUNT(*) AS total
                FROM books
                GROUP BY category
                """;

        List<PieChart.Data> data = new ArrayList<>();

        try (
                Connection connection = DatabaseService.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)
        ) {
            while (resultSet.next()) {
                data.add(new PieChart.Data(
                        resultSet.getString("category"),
                        resultSet.getInt("total")
                ));
            }
        } catch (Exception e) {
            System.out.println("Category chart failed: " + e.getMessage());
        }

        return data;
    }

    public List<XYChart.Data<String, Number>> getMonthlyLoanData() {
        String sql = """
                SELECT substr(loan_date, 1, 7) AS month, COUNT(*) AS total
                FROM loans
                GROUP BY substr(loan_date, 1, 7)
                ORDER BY month
                """;

        List<XYChart.Data<String, Number>> data = new ArrayList<>();

        try (
                Connection connection = DatabaseService.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)
        ) {
            while (resultSet.next()) {
                data.add(new XYChart.Data<>(
                        resultSet.getString("month"),
                        resultSet.getInt("total")
                ));
            }
        } catch (Exception e) {
            System.out.println("Monthly chart failed: " + e.getMessage());
        }

        return data;
    }

    private int getCount(String sql) {
        try (
                Connection connection = DatabaseService.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)
        ) {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Count failed: " + e.getMessage());
        }

        return 0;
    }
}
