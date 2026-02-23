package com.keony.app.controller;

import com.keony.app.core.engine.GameEngine;
import com.keony.app.ui.window.MainWindow;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;

public class GameController implements MouseListener, MouseMotionListener {
    private final MainWindow mainWindow;
    private final GameEngine gameEngine;
    private boolean firstCellState; // Saves the state of a cell before clicking
    private Point lastMousePosition;


    public GameController(MainWindow mainWindow, GameEngine gameEngine) {
        this.mainWindow = mainWindow;
        this.gameEngine = gameEngine;
        gameEngine.addListener(mainWindow.getGamePanel());
        this.mainWindow.getGamePanel().addMouseListener(this);
        this.mainWindow.getGamePanel().addMouseMotionListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) { // Drag and move
            lastMousePosition = e.getLocationOnScreen();
            return;
        } 
        
        int cellSize = mainWindow.getGamePanel().getCellsize();

        // Every cell drawn on screen has 1px offset, so we need to account for that
        // cellSize + 1 accounts for cellsize + offset.
        this.firstCellState = gameEngine.getCell(e.getX() / (cellSize + 1), e.getY() / (cellSize + 1)); // Captures the state of the first clicked cell
        gameEngine.toggleCell( e.getX() / (cellSize + 1), e.getY() / (cellSize + 1));
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // Drag and move
        if (SwingUtilities.isRightMouseButton(e)) {
            
            if (lastMousePosition != null) {
                Point currentMousePosition = e.getLocationOnScreen();
                
                int deltaX = lastMousePosition.x - currentMousePosition.x;
                int deltaY = lastMousePosition.y - currentMousePosition.y;

                JViewport viewport = (JViewport) SwingUtilities.getAncestorOfClass(JViewport.class, mainWindow.getGamePanel());
                
                if (viewport != null) {
                    Point viewPos = viewport.getViewPosition();
                    viewPos.translate(deltaX, deltaY);
                    viewport.setViewPosition(viewPos);
                }
                
                lastMousePosition = currentMousePosition;
            }
            return;

        }
        
        // Paint cells
        int cellSize = mainWindow.getGamePanel().getCellsize();
        gameEngine.setCell(e.getX() / (cellSize + 1), e.getY() / (cellSize + 1), !firstCellState);
        

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
