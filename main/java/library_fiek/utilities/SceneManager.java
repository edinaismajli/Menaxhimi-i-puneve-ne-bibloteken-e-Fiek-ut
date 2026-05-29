package library_fiek.utilities;

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
            Node view = FXMLLoader.load(SceneManager.class.getResource(fxmlPath));
            mainPane.setCenter(view);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}