package com.keony.app.ui.window;

import com.keony.app.ui.components.GamePanel;
import javax.swing.*;

public class MainWindow {

    private final JFrame window;
    private final GamePanel gamePanel;

    public MainWindow(int height, int width){
        window = new JFrame();
        gamePanel = new GamePanel();
        window.add(gamePanel);
        window.setSize(width, height);
    }

    public GamePanel getGamePanel() {
        return this.gamePanel;
    }

    public void setVisible(boolean visibility) {
        this.window.setVisible(visibility);
    }

    
}
