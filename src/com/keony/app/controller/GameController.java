package com.keony.app.controller;

import com.keony.app.core.engine.GameEngine;
import com.keony.app.ui.window.MainWindow;

public class GameController {
    private final MainWindow mainWindow;
    private final GameEngine gameEngine;

    public GameController(MainWindow mainWindow, GameEngine gameEngine) {
        this.mainWindow = mainWindow;
        this.gameEngine = gameEngine;
        gameEngine.addListener(mainWindow.getGamePanel());
    }
}
