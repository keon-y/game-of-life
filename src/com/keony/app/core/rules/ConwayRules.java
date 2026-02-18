package com.keony.app.core.rules;



public class ConwayRules {

    // Counts alive neighbours of a given cell located at x and y.
    private static int getAliveNeighbors(int x, int y, boolean[][] grid) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue; // Skip the cell itself
                int neighborX = x + i;
                int neighborY = y + j;
                if (neighborX >= 0 && neighborX < grid.length && neighborY >= 0 && neighborY < grid[neighborX].length) {
                    if (grid[neighborX][neighborY]) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    // Calculates the next generation of a grid.
    public static boolean[][] passGeneration(boolean[][] grid) {
        boolean[][] newGrid = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                int aliveNeighbors = getAliveNeighbors(i, j, grid);
                if (grid[i][j]) {
                    newGrid[i][j] =  aliveNeighbors == 2 || aliveNeighbors == 3;
                } else {
                    newGrid[i][j] = aliveNeighbors == 3;
                }
            }
        }
        return newGrid;
    }
}
