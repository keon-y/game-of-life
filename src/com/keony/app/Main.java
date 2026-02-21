package com.keony.app;

import com.keony.app.controller.GameController;
import com.keony.app.core.engine.GameEngine;
import com.keony.app.ui.window.MainWindow;

public class Main {

    final static int CELL_SIZE = 10;
    final static int GRID_WIDTH = 60;
    final static int GRID_HEIGHT = 60;
    public static void main(String[] args) {

        GameEngine gameEngine = new GameEngine(GRID_HEIGHT, GRID_WIDTH);
        MainWindow mainWindow = new MainWindow(GRID_HEIGHT * CELL_SIZE, GRID_HEIGHT * CELL_SIZE);
        GameController gameController = new GameController(mainWindow, gameEngine);

        mainWindow.setVisible(true);

    }
}

