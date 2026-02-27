package com.keony.app.ui.components;

import java.awt.*;
import java.awt.geom.Path2D;
import javax.swing.*;

public class ControlPanel extends JPanel {

    private enum ShapeType { PLAY, PAUSE, NEXT, PREV }

    private JButton btnPrev;
    private JButton btnPlay;
    private JButton btnPause;
    private JButton btnNext;

    public ControlPanel() {
        this.setBackground(new Color(30, 30, 30, 200));
        this.setOpaque(false);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 20));

        Color iconColor = new Color(220, 220, 220);
        int iconSize = 24;

        btnPrev = createButton(ShapeType.PREV, iconSize, iconColor);
        btnPlay = createButton(ShapeType.PLAY, iconSize, iconColor);
        btnPause = createButton(ShapeType.PAUSE, iconSize, iconColor);
        btnNext = createButton(ShapeType.NEXT, iconSize, iconColor);

        this.add(btnPrev);
        this.add(btnPlay);
        this.add(btnPause);
        this.add(btnNext);
    }

    private JButton createButton(ShapeType type, int size, Color iconColor) {
        JButton btn = new JButton(new SimpleShapeIcon(type, size, iconColor)) {
            @Override
            protected void paintComponent(Graphics g) {
                if (getModel().isRollover()) {
                    Graphics2D g2d = (Graphics2D) g.create();
                    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2d.setColor(new Color(60, 60, 60, 100));
                    g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                    g2d.dispose();
                }
                super.paintComponent(g);
            }
        };
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(size + 20, size + 20));
        return btn;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(getBackground());
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
        g2d.dispose();
    }

    public JButton getBtnPrev() { return btnPrev; }
    public JButton getBtnPlay() { return btnPlay; }
    public JButton getBtnPause() { return btnPause; }
    public JButton getBtnNext() { return btnNext; }

    private static class SimpleShapeIcon implements Icon {
        private final ShapeType type;
        private final int size;
        private final Color color;

        public SimpleShapeIcon(ShapeType type, int size, Color color) {
            this.type = type;
            this.size = size;
            this.color = color;
        }

        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(color);
            g2d.translate(x, y);

            switch (type) {
                case PLAY: drawPlay(g2d); break;
                case PAUSE: drawPause(g2d); break;
                case NEXT: drawNext(g2d); break;
                case PREV: drawPrev(g2d); break;
            }
            g2d.dispose();
        }

        private void drawPlay(Graphics2D g2d) {
            Path2D triangle = new Path2D.Double();
            triangle.moveTo(0, 0);
            triangle.lineTo(size, size / 2.0);
            triangle.lineTo(0, size);
            triangle.closePath();
            g2d.fill(triangle);
        }

        private void drawPause(Graphics2D g2d) {
            int barWidth = size / 3;
            g2d.fillRect(0, 0, barWidth, size);
            g2d.fillRect(size - barWidth, 0, barWidth, size);
        }

        private void drawNext(Graphics2D g2d) {
            int barWidth = size / 4;
            int triangleWidth = size - barWidth - 2;
            Path2D triangle = new Path2D.Double();
            triangle.moveTo(0, 0);
            triangle.lineTo(triangleWidth, size / 2.0);
            triangle.lineTo(0, size);
            triangle.closePath();
            g2d.fill(triangle);
            g2d.fillRect(size - barWidth, 0, barWidth, size);
        }

        private void drawPrev(Graphics2D g2d) {
            int barWidth = size / 4;
            int triangleStart = barWidth + 2;
            g2d.fillRect(0, 0, barWidth, size);
            Path2D triangle = new Path2D.Double();
            triangle.moveTo(size, 0);
            triangle.lineTo(triangleStart, size / 2.0);
            triangle.lineTo(size, size);
            triangle.closePath();
            g2d.fill(triangle);
        }

        @Override
        public int getIconWidth() { return size; }

        @Override
        public int getIconHeight() { return size; }
    }
}