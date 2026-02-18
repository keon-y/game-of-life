package com.keony.app;

import com.keony.app.core.engine.GameEngine;

public class Main {

    final static int CELL_SIZE = 10;
    final static int GRID_WIDTH = 60;
    final static int GRID_HEIGHT = 60;
    public static void main(String[] args) {

        GameEngine engine = new GameEngine(GRID_WIDTH, GRID_HEIGHT);


    }
}

