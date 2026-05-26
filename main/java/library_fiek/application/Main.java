package library_fiek.application;

import library_fiek.utilities.KeyboardShortcutUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(
                Main.class.getResource("/library_fiek/views/main-view.fxml")
        );

        Scene scene = new Scene(loader.load(), 1100, 720);

        scene.getStylesheets().add(
                Main.class.getResource("/library_fiek/styles/main.css").toExternalForm()
        );

        KeyboardShortcutUtil.registerMainShortcuts(scene);

        stage.setTitle("FIEK Library Management System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}