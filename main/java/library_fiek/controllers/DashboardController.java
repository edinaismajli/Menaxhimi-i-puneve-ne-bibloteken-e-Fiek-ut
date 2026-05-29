package library_fiek.controllers;

import library_fiek.models.DashboardStats;
import library_fiek.services.AIRecommendationService;
import library_fiek.services.DashboardService;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

public class DashboardController {
    @FXML
    private Label totalBooksLabel;

    @FXML
    private Label totalMembersLabel;

    @FXML
    private Label activeLoansLabel;

    @FXML
    private Label overdueLoansLabel;

    @FXML
    private Label aiRecommendationLabel;

    @FXML
    private PieChart categoryChart;

    @FXML
    private BarChart<String, Number> monthlyLoansChart;

    private final DashboardService dashboardService = new DashboardService();
    private final AIRecommendationService aiRecommendationService = new AIRecommendationService();

    @FXML
    public void initialize() {
        DashboardStats stats = dashboardService.getStats();

        totalBooksLabel.setText(String.valueOf(stats.getTotalBooks()));
        totalMembersLabel.setText(String.valueOf(stats.getTotalMembers()));
        activeLoansLabel.setText(String.valueOf(stats.getActiveLoans()));
        overdueLoansLabel.setText(String.valueOf(stats.getOverdueLoans()));

        categoryChart.getData().setAll(dashboardService.getBookCategoryData());

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Huazimet");
        series.getData().setAll(dashboardService.getMonthlyLoanData());

        monthlyLoansChart.getData().setAll(series);

        aiRecommendationLabel.setText(aiRecommendationService.getRecommendation());
    }
}
