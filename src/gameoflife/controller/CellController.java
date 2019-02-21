package gameoflife.controller;

import gameoflife.model.Cell;
import gameoflife.view.CellView;

public class CellController {
    private Cell cellModel;
    private CellView cellView;

    public CellController(Cell cellModel) {
        this.cellModel = cellModel;
        this.cellView = new CellView(this);
    }

    public boolean isCellAlive() {
        return cellModel.isAlive();
    }

    public CellView getCellView() {
        return cellView;
    }

    public void changeCellState() {
        cellModel.changeState();
    }

    public void updateCellNextState() {
        cellModel.updateNextState();
    }

    public void updateCellCurrentState() {
        cellModel.updateCurrentState();
        cellView.updateColor();
    }
}
