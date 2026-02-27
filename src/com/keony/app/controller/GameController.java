package com.keony.app.controller;

import com.keony.app.core.engine.GameEngine;
import com.keony.app.ui.components.ControlPanel;
import com.keony.app.ui.window.MainWindow;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class GameController implements MouseListener, MouseMotionListener, KeyListener {
    private final MainWindow mainWindow;
    private final GameEngine gameEngine;
    private boolean firstCellState; // Saves the state of a cell before clicking
    private Point lastMousePosition;
    private boolean isPaused = true;
    private Timer timer;


    public GameController(MainWindow mainWindow, GameEngine gameEngine) {
        this.mainWindow = mainWindow;
        this.gameEngine = gameEngine;
        gameEngine.addListener(mainWindow.getGamePanel());
        this.mainWindow.getGamePanel().addMouseListener(this);
        this.mainWindow.getGamePanel().addMouseMotionListener(this);
        this.mainWindow.addKeyListener(this);

        connectButtons();
        
        timer = new Timer(1000, e -> {
            gameEngine.step(1);
        });
        timer.stop();


    }

    @Override
    public void mouseClicked(MouseEvent e) {}

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
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
        switch (e.getKeyCode()) {
            case 39: // Arrow right key
                gameEngine.step(1);
            break;
            case 37: // left arrow key
                gameEngine.step(-1);
            break;
            case 32: // spacebar
                isPaused = !isPaused;
                if (isPaused) timer.stop();
                else if (!isPaused) timer.start();
            break;
            case 82: // R key
                gameEngine.clear();
            break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    private void connectButtons() {
        ControlPanel control = mainWindow.getControlPanel();

        control.getBtnPlay().addActionListener(e -> {
            isPaused = false;
            timer.start();
        });

        control.getBtnPause().addActionListener(e -> {
            isPaused = true;
            timer.stop();
        });

        control.getBtnNext().addActionListener(e -> {
            gameEngine.step(1);
        });

        control.getBtnPrev().addActionListener(e -> {
            gameEngine.step(-1);
        });
    }
}
