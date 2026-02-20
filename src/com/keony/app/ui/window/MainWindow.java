package com.keony.app.ui.window;

import com.keony.app.ui.components.GamePanel;
import javax.swing.*;

public class MainWindow extends JFrame {

    private final GamePanel gamePanel;

    public MainWindow(int height, int width){
        gamePanel = new GamePanel();
        this.add(gamePanel);
        this.setSize(width, height);
    }

    public GamePanel getGamePanel() {
        return this.gamePanel;
    }

    
    
}
