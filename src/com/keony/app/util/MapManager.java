package com.keony.app.util;

import com.keony.app.model.Cell;

public class MapManager {

    public void draw(Cell[][] cells) {
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                if (cell != null && cell.isAlive()) {
                    System.out.print("O ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }



    

}
