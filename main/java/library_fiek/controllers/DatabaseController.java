package library_fiek.controllers;

import library_fiek.models.Loan;
import library_fiek.services.DatabaseService;
import library_fiek.services.LanguageService;
import library_fiek.services.LoanService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;

public class DatabaseController {
    @FXML private Label connectionStatusLabel;
    @FXML private TableView<Loan> databaseTable;
    @FXML private TableColumn<Loan, Integer> idColumn;
    @FXML private TableColumn<Loan, Integer> bookIdColumn;
    @FXML private TableColumn<Loan, Integer> memberIdColumn;
    @FXML private TableColumn<Loan, String> statusColumn;

    private final LoanService loanService = new LoanService();

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        bookIdColumn.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        memberIdColumn.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        refreshData();
    }

    @FXML
    private void testConnection() {
        try (Connection connection = DatabaseService.getConnection()) {
            if (connection != null && !connection.isClosed()) {
                connectionStatusLabel.setText(LanguageService.get("database.connectionValid"));
            } else {
                connectionStatusLabel.setText(LanguageService.get("database.connectionFailed"));
            }
        } catch (Exception e) {
            connectionStatusLabel.setText(LanguageService.get("database.connectionFailed") + ": " + e.getMessage());
        }
    }

    @FXML
    private void refreshData() {
        databaseTable.setItems(FXCollections.observableArrayList(loanService.findAll()));
    }
}