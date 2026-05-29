package library_fiek.services;

import javafx.scene.Node;
import javafx.scene.Parent;

public class AccessibilityService {
    public static void setFocusOrder(Node... nodes) {
        for (Node node : nodes) {
            node.setFocusTraversable(true);
        }
    }

    public static void applyLargeText(Parent root, boolean enabled) {
        if (enabled) {
            root.setStyle("-fx-font-size: 16px;");
        } else {
            root.setStyle("-fx-font-size: 13px;");
        }
    }
}
