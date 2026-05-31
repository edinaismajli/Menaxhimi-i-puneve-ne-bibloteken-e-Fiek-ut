package library_fiek.controllers;

import library_fiek.application.AppNavigator;
import library_fiek.services.LanguageService;
import library_fiek.utilities.SceneManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainController {
    @FXML private BorderPane mainPane;
    @FXML private Label statusPrefixLabel;
    @FXML private Label statusLabel;

    @FXML private Menu fileMenu;
    @FXML private Menu manageMenu;
    @FXML private Menu languageMenu;
    @FXML private Menu helpMenu;

    @FXML private MenuItem exitMenuItem;
    @FXML private MenuItem dashboardMenuItem;
    @FXML private MenuItem booksMenuItem;
    @FXML private MenuItem membersMenuItem;
    @FXML private MenuItem loansMenuItem;
    @FXML private MenuItem databaseMenuItem;
    @FXML private MenuItem settingsMenuItem;
    @FXML private MenuItem helpMenuItem;

    @FXML private Button dashboardToolbarButton;
    @FXML private Button booksToolbarButton;
    @FXML private Button membersToolbarButton;
    @FXML private Button loansToolbarButton;
    @FXML private Button databaseToolbarButton;
    @FXML private Button helpToolbarButton;

    @FXML private Button dashboardSideButton;
    @FXML private Button booksSideButton;
    @FXML private Button membersSideButton;
    @FXML private Button loansSideButton;
    @FXML private Button databaseSideButton;
    @FXML private Button helpSideButton;
    @FXML private Button settingsSideButton;

    private String currentView = AppNavigator.DASHBOARD;

    @FXML
    public void initialize() {
        SceneManager.setMainPane(mainPane);
        updateTexts();
        openDashboard();
    }

    @FXML
    private void openDashboard() {
        loadView(AppNavigator.DASHBOARD);
        setStatus(LanguageService.get("status.dashboard"));
    }

    @FXML
    private void openBooks() {
        loadView(AppNavigator.BOOKS);
        setStatus(LanguageService.get("status.books"));
    }

    @FXML
    private void openMembers() {
        loadView(AppNavigator.MEMBERS);
        setStatus(LanguageService.get("status.members"));
    }

    @FXML
    private void openLoans() {
        loadView(AppNavigator.LOANS);
        setStatus(LanguageService.get("status.loans"));
    }

    @FXML
    private void openDatabase() {
        loadView(AppNavigator.DATABASE);
        setStatus(LanguageService.get("status.database"));
    }

    @FXML
    private void openHelp() {
        loadView(AppNavigator.HELP);
        setStatus(LanguageService.get("status.help"));
    }

    @FXML
    private void openSettings() {
        loadView(AppNavigator.SETTINGS);
        setStatus(LanguageService.get("status.settings"));
    }

    @FXML
    private void setAlbanianLanguage() {
        LanguageService.setLanguage("sq");
        updateTexts();
        reloadCurrentView();
        setStatus(LanguageService.get("status.language.sq"));
    }

    @FXML
    private void setEnglishLanguage() {
        LanguageService.setLanguage("en");
        updateTexts();
        reloadCurrentView();
        setStatus(LanguageService.get("status.language.en"));
    }

    @FXML
    private void exitApp() {
        System.exit(0);
    }

    private void loadView(String fxmlPath) {
        currentView = fxmlPath;
        SceneManager.loadCenter(fxmlPath);
    }

    private void reloadCurrentView() {
        SceneManager.loadCenter(currentView);
    }

    private void updateTexts() {
        fileMenu.setText(LanguageService.get("menu.file"));
        manageMenu.setText(LanguageService.get("menu.manage"));
        languageMenu.setText(LanguageService.get("menu.language"));
        helpMenu.setText(LanguageService.get("menu.help"));

        exitMenuItem.setText(LanguageService.get("menu.exit"));
        dashboardMenuItem.setText(LanguageService.get("dashboard.title"));
        booksMenuItem.setText(LanguageService.get("books.short"));
        membersMenuItem.setText(LanguageService.get("members.short"));
        loansMenuItem.setText(LanguageService.get("loans.short"));
        databaseMenuItem.setText(LanguageService.get("database.title"));
        settingsMenuItem.setText(LanguageService.get("settings.title"));
        helpMenuItem.setText(LanguageService.get("help.title"));

        dashboardToolbarButton.setText(LanguageService.get("dashboard.title"));
        booksToolbarButton.setText(LanguageService.get("books.short"));
        membersToolbarButton.setText(LanguageService.get("members.short"));
        loansToolbarButton.setText(LanguageService.get("loans.short"));
        databaseToolbarButton.setText(LanguageService.get("database.title"));
        helpToolbarButton.setText(LanguageService.get("help.title"));

        dashboardSideButton.setText(LanguageService.get("dashboard.title"));
        booksSideButton.setText(LanguageService.get("books.short"));
        membersSideButton.setText(LanguageService.get("members.short"));
        loansSideButton.setText(LanguageService.get("loans.short"));
        databaseSideButton.setText(LanguageService.get("database.title"));
        helpSideButton.setText(LanguageService.get("help.title"));
        settingsSideButton.setText(LanguageService.get("settings.title"));

        statusPrefixLabel.setText(LanguageService.get("status.label"));
        updateStageTitle();
    }

    private void setStatus(String text) {
        statusLabel.setText(text);
    }

    private void updateStageTitle() {
        Platform.runLater(() -> {
            if (mainPane.getScene() != null && mainPane.getScene().getWindow() instanceof Stage stage) {
                stage.setTitle(LanguageService.get("app.title"));
            }
        });
    }
}