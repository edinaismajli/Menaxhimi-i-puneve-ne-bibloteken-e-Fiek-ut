package library_fiek.utilities;

import library_fiek.application.AppNavigator;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;

public class KeyboardShortcutUtil {
    public static void registerMainShortcuts(Scene scene) {
        scene.getAccelerators().put(
                new KeyCodeCombination(KeyCode.D, KeyCombination.CONTROL_DOWN),
                () -> SceneManager.loadCenter(AppNavigator.DASHBOARD)
        );

        scene.getAccelerators().put(
                new KeyCodeCombination(KeyCode.B, KeyCombination.CONTROL_DOWN),
                () -> SceneManager.loadCenter(AppNavigator.BOOKS)
        );

        scene.getAccelerators().put(
                new KeyCodeCombination(KeyCode.M, KeyCombination.CONTROL_DOWN),
                () -> SceneManager.loadCenter(AppNavigator.MEMBERS)
        );

        scene.getAccelerators().put(
                new KeyCodeCombination(KeyCode.L, KeyCombination.CONTROL_DOWN),
                () -> SceneManager.loadCenter(AppNavigator.LOANS)
        );

        scene.getAccelerators().put(
                new KeyCodeCombination(KeyCode.H, KeyCombination.CONTROL_DOWN),
                () -> SceneManager.loadCenter(AppNavigator.HELP)
        );

        scene.getAccelerators().put(
                new KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN),
                () -> System.exit(0)
        );
    }
}