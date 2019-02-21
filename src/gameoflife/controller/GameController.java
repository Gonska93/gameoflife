package gameoflife.controller;

import gameoflife.model.Cell;
import gameoflife.model.DisplayConfig;
import gameoflife.model.Game;
import gameoflife.model.Settings;
import gameoflife.view.GameFX;

public class GameController implements Runnable{
    private Game game;
    private GameFX gameFX;
    private CellController[] cellControllers;

    public GameController() {
        this.game = new Game();
        this.cellControllers = new CellController[Settings.rows*Settings.columns];
        initCellControllers(game.getCells());
        DisplayConfig.CELL_HEIGHT = DisplayConfig.GAME_BOARD_HEIGHT/Settings.columns;
        DisplayConfig.CELL_WIDTH = DisplayConfig.CELL_HEIGHT*0.8;
        this.gameFX = new GameFX(this);
    }

    public GameFX getGameFX() {
        return gameFX;
    }

    public CellController[] getCellControllers() {
        return cellControllers;
    }

    private void initCellControllers(Cell[][] cells) {
        int cellControllersIndex = 0;
        for (int r = 0; r < Settings.rows; r++) {
            for (int c = 0; c < Settings.columns; c++) {
                cellControllers[cellControllersIndex++] = new CellController(cells[r][c]);
            }
        }
    }

    private void updateCells() {
        for (CellController cellController: cellControllers) {
            cellController.updateCellNextState();
        }
        for (CellController cellController: cellControllers) {
            cellController.updateCellCurrentState();
        }
    }

    @Override
    public void run() {
        while(Settings.running) {
            if (!Settings.paused) {
                updateCells();
            }
            try {
                Thread.sleep((100*Settings.speed_in_tenth_of_second) / Settings.speed_ratio);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
