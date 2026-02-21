package com.keony.app.ui.window;

import com.keony.app.ui.components.GamePanel;
import java.awt.Dimension;
import javax.swing.*;

public class MainWindow extends JFrame {

    private final GamePanel gamePanel;

    public MainWindow(int height, int width){
        this.gamePanel = new GamePanel();
        this.gamePanel.setPreferredSize(new Dimension(2000, 2000));
        this.setSize(width, height);

        JScrollPane scrollPane = new JScrollPane(gamePanel);
        
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        this.add(scrollPane);
        
    }

    public GamePanel getGamePanel() {
        return this.gamePanel;
    }

    public void setGameBounds(int x, int y) {
        gamePanel.setBounds(x, y, this.getWidth(), this.getHeight());
    }
    
    
}
