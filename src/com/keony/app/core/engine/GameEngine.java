package com.keony.app.core.engine;

import com.keony.app.core.events.GridUpdateListener;
import com.keony.app.core.rules.ConwayRules;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class GameEngine {

    private final List<GridUpdateListener> gridListeners = new ArrayList<>();
    private final int width;
    private final int height;

    private LinkedList<boolean[][]> gridSnapshots = new LinkedList<>();
    private ListIterator<boolean[][]> gridIterator = gridSnapshots.listIterator();
    private boolean[][] grid;

    public GameEngine(int gridHeight, int gridWidth) {
        this.grid = new boolean[gridWidth][gridHeight];
        this.width = gridWidth;
        this.height = gridHeight;
    }

    private void updateListeners() {
        for (GridUpdateListener listener : gridListeners) {
            listener.onGridUpdated(grid);
        }
    }

    // Passes to the next generation and notify all listeners
    public void nextGeneration() {
        
        if (gridSnapshots.isEmpty()) { // First iteration, need to add manually the first user edited grid since its not generated.
            gridIterator.add(grid);
        }
        

        grid = ConwayRules.passGeneration(grid);
        gridIterator.add(grid);
        updateListeners();
    }

    public void step(int stepAmount) {
        for (int i = 0; i < Math.abs(stepAmount); i++) {
            if (stepAmount < 0 && gridIterator.hasPrevious()) { // step back
                grid = gridIterator.previous();
                updateListeners();
            }
            else {
                if (gridIterator.hasNext()) {
                   grid = gridIterator.next(); 
                }
                else {
                    this.nextGeneration();
                }
                
                updateListeners();
            }
        }
    }


    // Toggles the state of a cell at the given x and y coordinates (0-based index)
    public void toggleCell(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            grid[x][y] = !grid[x][y];
        }
        timeTravelIterator();
        updateListeners();
    }

    // Clears the grid by setting all cells to false (dead)
    public void clear() {
        Arrays.fill(grid, false);
        updateListeners();
    }

    public void setCell(int x, int y, boolean state) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            grid[x][y] = state;
        }
        timeTravelIterator();
        updateListeners();
    }

    // Solves the "what if I go back and change the grid?" by deleting all generated grids past the editing point.
    // Not very performance optimized, since users can toggle something on and off and not changing anything and would still be necessary to re-calculate all generations
    private void timeTravelIterator(){
        if (gridIterator.hasNext()) {
            ListIterator<boolean[][]> temp = gridIterator;
            while (gridIterator.hasNext()){
                gridIterator.next();
                gridIterator.remove();
            }
            gridIterator = temp;
        }
    }

    public boolean getCell(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            return grid[x][y];
        }
        return false;
    }

    public void addListener(GridUpdateListener listener) {
        gridListeners.add(listener);
        listener.onGridUpdated(grid); // Update as soon as it joins, so it does not have to wait for the update after a cell/grid change
    }
    
}
