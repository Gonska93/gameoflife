package gameoflife.model;

import javafx.scene.layout.Pane;

public class Game {
    private Cell[][] cells;

    public Game() {
        this.cells = new Cell[Settings.rows][Settings.columns];
        initCells();
        initObervers();
    }

    private void initCells() {
        for (int row = 0; row < Settings.rows; row++) {
            for (int column = 0; column < Settings.columns; column++) {
                cells[row][column] = new Cell();
            }
        }
    }

    private void initObervers() {
        for (int row = 0; row < Settings.rows; row++) {
            for (int column = 0; column < Settings.columns; column++) {
                addObserversToCell(row, column);
            }
        }
    }

    private void addObserversToCell(int currentCellRow, int currentCellColumn) {
        Cell currentCell = cells[currentCellRow][currentCellColumn];
        int startRow, endRow, startColumn, endColumn;

        if (currentCellRow == 0) {
            startRow = currentCellRow;
            endRow = currentCellRow + 1;
        } else if (currentCellRow == Settings.rows - 1) {
            startRow = currentCellRow - 1;
            endRow = currentCellRow;
        } else {
            startRow = currentCellRow - 1;
            endRow = currentCellRow + 1;
        }

        if (currentCellColumn == 0) {
            startColumn = currentCellColumn;
            endColumn = currentCellColumn + 1;
        } else if (currentCellColumn == Settings.columns - 1) {
            startColumn = currentCellColumn - 1;
            endColumn = currentCellColumn;
        } else {
            startColumn = currentCellColumn - 1;
            endColumn = currentCellColumn + 1;
        }

        for (int r = startRow; r <= endRow; r++) {
            for (int c = startColumn; c <= endColumn; c++) {
                if (r == currentCellRow && c == currentCellColumn) continue;
                currentCell.addObserver(cells[r][c]);
            }
        }
    }

    public Cell[][] getCells() {
        return cells;
    }
}
