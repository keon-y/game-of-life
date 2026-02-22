package com.keony.app.ui.components;

import com.keony.app.core.events.GridUpdateListener;
import java.awt.*;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements GridUpdateListener {

    private boolean[][] grid;
    private int cellSize = 10;

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (grid == null) return;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j]) g.setColor(Color.BLACK);
                else g.setColor(Color.WHITE);
                
                g.fillRect(i * cellSize + i, j * cellSize + j, cellSize, cellSize);
            }
        }
    }

    @Override
    public void onGridUpdated(boolean[][] newGrid) {
        grid = newGrid;
        repaint();
    }

    public void setCellSize(int size) {
        this.cellSize = size;
    }

    public void setGrid(boolean[][] grid) {
        this.grid = grid;
        repaint();
    }


    
}
