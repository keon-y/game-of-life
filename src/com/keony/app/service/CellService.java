package com.keony.app.service;

import com.keony.app.model.Cell;

public class CellService {
    private final Cell[][] cells;

    public CellService() {
        this.cells = new Cell[20][20];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new Cell(i, j, false);
            }
        }

        /*
        6, 5
8, 6
5, 7
6, 7
9,7
10,7
11,7
        */

        // TEMPORARY: Set some cells to alive for testing
        this.cells[5][6].setAlive(true);
        this.cells[6][8].setAlive(true);
        this.cells[7][5].setAlive(true);
        this.cells[7][6].setAlive(true);
        this.cells[7][9].setAlive(true);
        this.cells[7][10].setAlive(true);
        this.cells[7][11].setAlive(true);
    }

    public Cell[][] getCells() {
        return cells;
    }

    private int countAliveNeighbors(int x, int y) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue; // Skip the cell itself
                int neighborX = x + i;
                int neighborY = y + j;
                if (neighborX >= 0 && neighborX < cells.length && neighborY >= 0 && neighborY < cells[neighborX].length) {
                    if (cells[neighborX][neighborY].isAlive()) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public Cell[][] passGeneration() {
        Cell[][] newCells = new Cell[cells.length][cells[0].length];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                int aliveNeighbors = countAliveNeighbors(i, j);
                if (cells[i][j].isAlive()) {
                    newCells[i][j] = new Cell(i, j, aliveNeighbors == 2 || aliveNeighbors == 3);
                } else {
                    newCells[i][j] = new Cell(i, j, aliveNeighbors == 3);
                }
            }
        }
        System.arraycopy(newCells, 0, cells, 0, cells.length);
        return cells;
    }
}
