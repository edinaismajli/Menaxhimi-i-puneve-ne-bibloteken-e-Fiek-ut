package library_fiek.controllers;

import library_fiek.services.AccessibilityService;
import library_fiek.services.LanguageService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class SettingsController {
    @FXML
    private VBox settingsRoot;

    @FXML
    private RadioButton albanianRadio;

    @FXML
    private RadioButton englishRadio;

    @FXML
    private CheckBox largeTextCheckBox;

    private final ToggleGroup languageGroup = new ToggleGroup();

    @FXML
    public void initialize() {
        albanianRadio.setToggleGroup(languageGroup);
        englishRadio.setToggleGroup(languageGroup);

        if ("en".equals(LanguageService.getCurrentLanguage())) {
            englishRadio.setSelected(true);
        } else {
            albanianRadio.setSelected(true);
        }

        AccessibilityService.setFocusOrder(albanianRadio, englishRadio, largeTextCheckBox);
    }

    @FXML
    private void saveSettings() {
        if (englishRadio.isSelected()) {
            LanguageService.setLanguage("en");
        } else {
            LanguageService.setLanguage("sq");
        }

        AccessibilityService.applyLargeText(settingsRoot, largeTextCheckBox.isSelected());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Settings");
        alert.setHeaderText(null);
        alert.setContentText("Settings saved.");
        alert.showAndWait();
    }
}
