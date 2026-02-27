package com.keony.app.ui.window;

import com.keony.app.ui.components.ControlPanel;
import com.keony.app.ui.components.GamePanel;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.*;


public class MainWindow extends JFrame {

    private final GamePanel gamePanel;
    private final ControlPanel controlPanel;

    public MainWindow(int height, int width){
        this.gamePanel = new GamePanel();
        this.controlPanel = new ControlPanel();
        this.gamePanel.setPreferredSize(new Dimension(5000, 5000));
        this.controlPanel.setPreferredSize(new Dimension(300, 100));
        this.setSize(width, height);

        JScrollPane scrollPane = new JScrollPane(gamePanel);
        scrollPane.getViewport().setViewPosition(new Point(2000, 2000));
        
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(width, height));
        scrollPane.setBounds(0, 0, width, height);
        int controlWidth = 300;
        int controlHeight = 80;
        int controlX = (width - controlWidth) / 2; 
        int controlY = height - controlHeight - 10;
        controlPanel.setBounds(controlX, controlY, controlWidth, controlHeight);
        layeredPane.add(scrollPane, JLayeredPane.DEFAULT_LAYER); 
        layeredPane.add(controlPanel, JLayeredPane.PALETTE_LAYER);
        this.add(layeredPane);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public GamePanel getGamePanel() {
        return this.gamePanel;
    }

    public void setGameBounds(int x, int y) {
        gamePanel.setBounds(x, y, this.getWidth(), this.getHeight());
    }
    
    
}
