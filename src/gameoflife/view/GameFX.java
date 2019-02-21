package gameoflife.view;

import gameoflife.controller.CellController;
import gameoflife.controller.GameController;
import gameoflife.model.DisplayConfig;
import gameoflife.model.Settings;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.geometry.Insets;

public class GameFX {
    private final BorderPane rootPane;
    private GameController gameController;
    private GridPane cellContainer;
    private MenuPanel menuPanel;
    private Insets padding;

    public GameFX(GameController gameController) {
        this.rootPane = new BorderPane();
        this.gameController = gameController;
        this.cellContainer = initCellContainer();
        initCellBlocks(gameController.getCellControllers());
        this.menuPanel = initMenuPanel();

        rootPane.setLeft(menuPanel);
        rootPane.setCenter(cellContainer);
    }

    public BorderPane getRootPane() {
        return rootPane;
    }

    private MenuPanel initMenuPanel() {
        menuPanel = new MenuPanel();
        padding = new Insets(5, 20, 5, 0);
        menuPanel.setMinWidth(DisplayConfig.MENU_PANEL_WIDTH);
        menuPanel.setStyle("-fx-background-color: gray;\n" +
                           "-fx-border-color: black;\n" +
                           "-fx-border-radius: 5;\n");

        Button startButton = new Button("Start");
        startButton.setOnAction(new StartButtonHandler());
        menuPanel.setMargin(startButton, padding);
        menuPanel.addButton(startButton);

        return menuPanel;
    }

    private GridPane initCellContainer() {
        GridPane cellContainer = new GridPane();
        cellContainer.setPrefWidth(DisplayConfig.GAME_BOARD_WIDTH);
        cellContainer.setPrefHeight(DisplayConfig.GAME_BOARD_HEIGHT);
        cellContainer.setGridLinesVisible(true);
        cellContainer.setStyle("-fx-background-color: white;\n" +
                               "-fx-border-color: gray;\n" +
                               "-fx-border-radius: 5;\n");

        return cellContainer;
    }

    private void initCellBlocks(CellController[] cellControllers) {
        int counter = 0;
        VBox mainContainer = new VBox();

        for (int r = 0; r < Settings.rows; r++) {
            HBox currentRow = new HBox();
            for (int c = 0; c < Settings.columns; c++) {
                CellView cell = cellControllers[counter++].getCellView();
                currentRow.getChildren().add(cell);
            }
            mainContainer.getChildren().add(currentRow);
        }
        cellContainer.getChildren().add(mainContainer);
    }

    class SpeedButtonHandler implements EventHandler {
        @Override
        public void handle(Event event) {
            if (Settings.speed_ratio < 3) Settings.speed_ratio++;
            else Settings.speed_ratio = 1;

            Button currentButton = (Button) event.getSource();
            currentButton.setText("Speed x" + Settings.speed_ratio);
        }
    }

    class PauseButtonHandler implements EventHandler {
        @Override
        public void handle(Event event) {
            Settings.paused = !Settings.paused;
            System.out.println(Settings.paused);
            Button currentButton = (Button) event.getSource();
            currentButton.setText((Settings.paused) ? "Play":"Paused");
        }
    }

    class StartButtonHandler implements EventHandler {

        @Override
        public void handle(Event event) {
            Settings.started = true;
            Settings.running = true;
            Button currentButton = (Button) event.getSource();
            menuPanel.getChildren().remove(currentButton);

            Button speedButton = new Button("Speed x" + Settings.speed_ratio);
            menuPanel.setMargin(speedButton, padding);
            Button pauseButton = new Button("Pause");
            menuPanel.setMargin(pauseButton, padding);

            speedButton.setOnAction(new SpeedButtonHandler());
            pauseButton.setOnAction(new PauseButtonHandler());

            menuPanel.addButton(speedButton);
            menuPanel.addButton(pauseButton);

            new Thread(gameController, "Game Controller").start();
        }
    }
}
