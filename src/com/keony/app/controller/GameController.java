package com.keony.app.controller;

import com.keony.app.core.engine.GameEngine;
import com.keony.app.ui.window.MainWindow;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class GameController implements MouseListener, MouseMotionListener {
    private final MainWindow mainWindow;
    private final GameEngine gameEngine;
    private boolean firstCellState; // Saves the state of a cell before clicking


    public GameController(MainWindow mainWindow, GameEngine gameEngine) {
        this.mainWindow = mainWindow;
        this.gameEngine = gameEngine;
        gameEngine.addListener(mainWindow.getGamePanel());
        this.mainWindow.getGamePanel().addMouseListener(this);
        this.mainWindow.getGamePanel().addMouseMotionListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int cellSize = mainWindow.getGamePanel().getCellsize();
        gameEngine.toggleCell(e.getX() / cellSize, e.getY() / cellSize);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int cellSize = mainWindow.getGamePanel().getCellsize();
        this.firstCellState = gameEngine.getCell(e.getX() / cellSize, e.getY() / cellSize); // Captures the state of the first clicked cell 
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int cellSize = mainWindow.getGamePanel().getCellsize();
        gameEngine.setCell(e.getX() / cellSize, e.getY() / cellSize, !firstCellState);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //TODO: ADD MOUSE EVENTS
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //TODO: ADD MOUSE EVENTS
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        
    }
}
