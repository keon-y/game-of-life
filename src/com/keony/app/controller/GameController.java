package com.keony.app.controller;

import com.keony.app.core.engine.GameEngine;
import com.keony.app.ui.window.MainWindow;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameController implements MouseListener {
    private final MainWindow mainWindow;
    private final GameEngine gameEngine;

    public GameController(MainWindow mainWindow, GameEngine gameEngine) {
        this.mainWindow = mainWindow;
        this.gameEngine = gameEngine;
        gameEngine.addListener(mainWindow.getGamePanel());
        this.mainWindow.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //TODO: ADD MOUSE EVENTS
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //TODO: ADD MOUSE EVENTS
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //TODO: ADD MOUSE EVENTS
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //TODO: ADD MOUSE EVENTS
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //TODO: ADD MOUSE EVENTS
    }
}
