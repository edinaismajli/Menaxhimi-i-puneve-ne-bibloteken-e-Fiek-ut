package library_fiek.controllers;

import library_fiek.application.AppNavigator;
import library_fiek.utilities.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class MainController {
    @FXML
    private BorderPane mainPane;

    @FXML
    private Label statusLabel;

    @FXML
    public void initialize() {
        SceneManager.setMainPane(mainPane);
        openDashboard();
    }

    @FXML
    private void openDashboard() {
        SceneManager.loadCenter(AppNavigator.DASHBOARD);
        setStatus("Dashboard opened");
    }

    @FXML
    private void openBooks() {
        SceneManager.loadCenter(AppNavigator.BOOKS);
        setStatus("Books opened");
    }

    @FXML
    private void openMembers() {
        SceneManager.loadCenter(AppNavigator.MEMBERS);
        setStatus("Members opened");
    }

    @FXML
    private void openLoans() {
        SceneManager.loadCenter(AppNavigator.LOANS);
        setStatus("Loans opened");
    }

    @FXML
    private void openDatabase() {
        SceneManager.loadCenter(AppNavigator.DATABASE);
        setStatus("Database opened");
    }

    @FXML
    private void openHelp() {
        SceneManager.loadCenter(AppNavigator.HELP);
        setStatus("Help opened");
    }

    @FXML
    private void openSettings() {
        SceneManager.loadCenter(AppNavigator.SETTINGS);
        setStatus("Settings opened");
    }

    @FXML
    private void setAlbanianLanguage() {
        setStatus("Gjuha u ndryshua ne shqip");
    }

    @FXML
    private void setEnglishLanguage() {
        setStatus("Language changed to English");
    }

    @FXML
    private void exitApp() {
        System.exit(0);
    }

    private void setStatus(String text) {
        statusLabel.setText(text);
    }
}