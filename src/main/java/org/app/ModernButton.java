package org.app;

import javax.swing.*;
import java.awt.*;

public class ModernButton extends JButton {
    private final Color topColor = new Color(115, 95, 185);
    private final Color bottomColor = new Color(75, 60, 135);
    private final Color hoverTopColor = new Color(135, 115, 210);
    private final Color hoverBottomColor = new Color(92, 75, 155);
    private final Color borderColor = new Color(185, 175, 245);

    private boolean hovered = false;

    public ModernButton(String text) {
        super(text);

        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setOpaque(false);

        setForeground(new Color(250, 246, 255));
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        setBorder(BorderFactory.createEmptyBorder(10, 18, 10, 18));

        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                hovered = true;
                repaint();
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                hovered = false;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        int width = getWidth();
        int height = getHeight();
        int arc = 28;

        Color top = hovered ? hoverTopColor : topColor;
        Color bottom = hovered ? hoverBottomColor : bottomColor;

        // cień pod przyciskiem — efekt 3D
        g2.setColor(new Color(0, 0, 0, 90));
        g2.fillRoundRect(3, 4, width - 6, height - 5, arc, arc);

        // gradient przycisku
        GradientPaint gradient = new GradientPaint(
                0, 0, top,
                0, height, bottom
        );

        g2.setPaint(gradient);
        g2.fillRoundRect(0, 0, width - 6, height - 7, arc, arc);

        // jasna górna poświata
        g2.setColor(new Color(255, 255, 255, 45));
        g2.fillRoundRect(3, 3, width - 12, height / 2, arc, arc);

        // obramowanie
        g2.setColor(borderColor);
        g2.drawRoundRect(0, 0, width - 6, height - 7, arc, arc);

        g2.dispose();

        super.paintComponent(g);
    }
}