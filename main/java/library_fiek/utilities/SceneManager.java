package library_fiek.utilities;

import library_fiek.services.LanguageService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

public class SceneManager {
    private static BorderPane mainPane;

    public static void setMainPane(BorderPane pane) {
        mainPane = pane;
    }

    public static void loadCenter(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    SceneManager.class.getResource(fxmlPath),
                    LanguageService.getBundle()
            );
            Node view = loader.load();
            mainPane.setCenter(view);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}