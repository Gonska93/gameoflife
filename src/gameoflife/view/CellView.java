package gameoflife.view;

import gameoflife.controller.CellController;
import gameoflife.model.DisplayConfig;
import gameoflife.model.Settings;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

public class CellView extends Rectangle {
    private CellController cellController;

    public CellView(CellController cellController) {
        super(DisplayConfig.CELL_WIDTH, DisplayConfig.CELL_HEIGHT, DisplayConfig.CELL_COLOR_DEAD);
        setStroke(Color.BLACK);
        setStrokeType(StrokeType.INSIDE);
        setCellController(cellController);
        this.setOnMouseClicked(new CellOnPressListener());
        this.setOnMouseEntered(new CellOnEnterListener());
        this.setOnMouseExited(new CellOnExitListener());
    }

    public void setCellController(CellController cellController) {
        this.cellController = cellController;
    }

    public void updateColor() {
        this.setFill((cellController.isCellAlive()) ? DisplayConfig.CELL_COLOR_ALIVE : DisplayConfig.CELL_COLOR_DEAD);
    }

    class CellOnPressListener implements EventHandler {
        @Override
        public void handle(Event event) {
            if (!Settings.paused && Settings.started) return;
            cellController.changeCellState();
            updateColor();
        }
    }
    class CellOnEnterListener implements EventHandler {
        @Override
        public void handle(Event event) {
            if (!Settings.paused && Settings.started) return;
            setFill((cellController.isCellAlive()) ? DisplayConfig.CELL_ALIVE_COLOR_ENTERED : DisplayConfig.CELL_DEAD_COLOR_ENTERED);
        }
    }
    class CellOnExitListener implements EventHandler {
        @Override
        public void handle(Event event) {
            if (!Settings.paused && Settings.started) return;
            updateColor();
        }
    }
}
