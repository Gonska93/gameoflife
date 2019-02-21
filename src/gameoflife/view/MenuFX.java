package gameoflife.view;

import gameoflife.Main;
import gameoflife.controller.GameController;
import gameoflife.model.DisplayConfig;
import gameoflife.model.Game;
import gameoflife.model.Settings;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class MenuFX extends BorderPane {
    private Stage mainStage;
    private VBox container;
    private boolean closedMenu = false;
    private TextField columns;
    private TextField rows;
    private Text errorMessage;

    public MenuFX(String title, Stage mainStage) {
        this.mainStage = mainStage;
        container = new VBox();
        columns = new TextField();
        rows = new TextField();
        errorMessage = new Text();
        columns.setPromptText("Min 2 Max 200");
        rows.setPromptText("Min 2 Max 200");

        setupContainer();
        addTitle(title);
        addInput("Columns", columns);
        addInput("Rows", rows);

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(new SubmitButtonHandler());
        container.getChildren().addAll(submitButton, errorMessage);
        this.setCenter(container);
    }

    private void addInput(String labelName, TextField textField) {
        if (closedMenu) return;

        Label inputLabel = new Label(labelName);
        container.getChildren().addAll(inputLabel, textField);
    }

    private void addTitle(String title) {
        Text titleText = new Text(title);
        titleText.setFont(Font.font(20));
        titleText.setTextAlignment(TextAlignment.CENTER);

        container.getChildren().addAll(titleText);
    }

    private void setupContainer() {
        container.setMaxSize(DisplayConfig.SCREEN_WIDTH/10, DisplayConfig.SCREEN_HEIGHT/10);
        container.setSpacing(10);
    }

    private void displayErrorMessage(String message) {
        errorMessage.setText(message);
    }

    private int getColumnsAmount() {
        try {
            int columnsAmount = Integer.parseInt(columns.getText());
            return columnsAmount;
        } catch (IllegalArgumentException ex) {
            return -1;
        }
    }

    private int getRowsAmount() {
        try {
            int rowsAmount = Integer.parseInt(rows.getText());
            return rowsAmount;
        } catch (IllegalArgumentException ex) {
            return -1;
        }
    }

    private boolean validateInput(int min, int max, int current, String inputName) {
        if (current < 0) {
            displayErrorMessage("Enter a number!");
            return false;
        } else if (current < min) {
            displayErrorMessage(inputName + " is too low.");
            return false;
        } else if (current > max) {
            displayErrorMessage(inputName + " is too high.");
            return false;
        }
        return true;
    }

    class SubmitButtonHandler implements EventHandler {
        @Override
        public void handle(Event event) {
                int columnsAmount = getColumnsAmount();
                int rowsAmount = getRowsAmount();

                if (!validateInput(2, 200, columnsAmount, "Columns")) return;
                else if (!validateInput(2, 200, rowsAmount, "Rows")) return;

                Settings.columns = columnsAmount;
                DisplayConfig.CELL_WIDTH = DisplayConfig.GAME_BOARD_WIDTH/columnsAmount;
                Settings.rows = rowsAmount;
                DisplayConfig.CELL_HEIGHT = DisplayConfig.GAME_BOARD_HEIGHT/rowsAmount;

                GameController gameController = new GameController();
                mainStage.getScene().setRoot(gameController.getGameFX().getRootPane());
        }
    }
}
