package gameoflife.view;

import gameoflife.model.Cell;

public class GamePanel {
    private Cell[][] game;

    public GamePanel(int columns, int rows) {
        this.game = new Cell[columns][rows];
    }
}
