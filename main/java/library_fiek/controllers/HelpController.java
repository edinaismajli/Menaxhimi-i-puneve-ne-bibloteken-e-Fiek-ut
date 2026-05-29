package library_fiek.controllers;

import library_fiek.services.LanguageService;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class HelpController {
    @FXML
    private TextArea helpTextArea;

    @FXML
    public void initialize() {
        helpTextArea.setText(LanguageService.get("help.text"));
    }
}
