package com.keony.app;

import com.keony.app.service.CellService;
import com.keony.app.util.MapManager;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
        CellService cellService = new CellService();
        MapManager mapManager = new MapManager();

        Scanner scanner = new Scanner(System.in);

        mapManager.draw(cellService.getCells());
        while (true) {
            System.out.println("Press Enter to pass to the next generation or 'q' to quit...");
            String input = scanner.nextLine();
            if (input.equals("q")) {
                break;
            }
            mapManager.draw(cellService.passGeneration());
        }
        scanner.close();

    }
}

