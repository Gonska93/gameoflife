package gameoflife.model;

import java.util.LinkedList;
import java.util.ListIterator;

public class Cell {
    private boolean alive;
    private boolean nextAlive;
    private int amountOfAliveNeighbours;
    private LinkedList<Cell> neighboursCells;

    public Cell() {
        alive = false;
        nextAlive = false;
        amountOfAliveNeighbours = 0;
        neighboursCells = new LinkedList<>();
    }

    public boolean isAlive() {
        return alive;
    }

    public void changeState() {
        alive = !alive;
    }

    public void updateAliveNeighbours() {
        int counter = 0;
        ListIterator<Cell> iterator = neighboursCells.listIterator();
        while (iterator.hasNext()) {
            if (iterator.next().isAlive()) counter++;
        }
        amountOfAliveNeighbours = counter;
    }

    public void updateNextState() {
        updateAliveNeighbours();
        if (alive) {
            nextAlive = (amountOfAliveNeighbours == 2 || amountOfAliveNeighbours == 3) ? true : false;
        } else {
            nextAlive = (amountOfAliveNeighbours == 3) ? true : false;
        }
    }

    public void updateCurrentState() {
        alive = nextAlive;
    }

    public void addObserver(Cell observer) {
        this.neighboursCells.add(observer);
    }
}
