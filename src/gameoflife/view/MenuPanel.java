package gameoflife.view;

import gameoflife.model.DisplayConfig;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class MenuPanel extends VBox {
    public MenuPanel() {
        this.setMaxWidth(DisplayConfig.SCREEN_WIDTH/4);
        this.setSpacing(10);
        this.setPadding(new Insets(20, 0, 20, 20));
    }

    public void addButton(Button button) {
        button.setMaxWidth(Double.MAX_VALUE);
        addNode(button);
    }

    public void addNode(Node node) {
        this.getChildren().add(node);
    }
}