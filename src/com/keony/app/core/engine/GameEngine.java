package com.keony.app.core.engine;

import com.keony.app.core.rules.ConwayRules;
import java.util.Arrays;

public class GameEngine {

    private final int width;
    private final int height;
    private boolean[][] grid;

    public GameEngine(int height, int width) {
         this.grid = new boolean[width][height];
         this.width = width;
         this.height = height;
    }

    public void nextGeneration() {
        grid = ConwayRules.passGeneration(grid);
    }


    // Toggles the state of a cell at the given x and y coordinates (0-based index)
    public void toggleCell(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            grid[x][y] = !grid[x][y];
        }
    }

    // Clears the grid by setting all cells to false (dead)
    public void clear() {
        Arrays.fill(grid, false);
    }


    public boolean[][] getGrid() {
        return grid;
    }

    public void setGrid(boolean[][] grid) {
        this.grid = grid;
    }

    
}
