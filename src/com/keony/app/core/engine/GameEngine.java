package com.keony.app.core.engine;

import com.keony.app.core.events.GridUpdateListener;
import com.keony.app.core.rules.ConwayRules;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameEngine {

    private final List<GridUpdateListener> gridListeners = new ArrayList<>();
    private final int width;
    private final int height;
    private boolean[][] grid;

    public GameEngine(int gridHeight, int gridWidth) {
         this.grid = new boolean[gridWidth][gridHeight];
         this.width = gridWidth;
         this.height = gridHeight;
    }

    private void updateListeners() {
        for (GridUpdateListener listener : gridListeners) {
            listener.onGridUpdated(this.grid);
        }
    }

    // Passes to the next generation and notify all listeners
    public void nextGeneration() {
        grid = ConwayRules.passGeneration(grid);
        updateListeners();
    }


    // Toggles the state of a cell at the given x and y coordinates (0-based index)
    public void toggleCell(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            grid[x][y] = !grid[x][y];
        }
        updateListeners();
    }

    // Clears the grid by setting all cells to false (dead)
    public void clear() {
        Arrays.fill(grid, false);
        updateListeners();
    }


    public boolean[][] getGrid() {
        return grid;
    }

    public void setGrid(boolean[][] grid) {
        this.grid = grid;
        updateListeners();
    }

    public void setCell(int x, int y, boolean state) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            grid[x][y] = state;
        }
        updateListeners();
    }

    public boolean getCell(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            return grid[x][y];
        }
        return false;
    }

    public void addListener(GridUpdateListener listener) {
        gridListeners.add(listener);
    }
    
}
