package org.app.functions;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class scrollbar extends BasicScrollBarUI {

    private final Dimension thumbSize = new Dimension(8, 24);

    private final Color thumbColor = new Color(115, 95, 185);
    private final Color thumbHoverColor = new Color(135, 115, 210);


    private boolean hovered = false;

    @Override
    protected Dimension getMinimumThumbSize() {
        return thumbSize;
    }

    @Override
    protected void configureScrollBarColors() {
        // track (tło scrolla)
        this.trackColor = new Color(0, 0, 0, 0);
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        return createZeroButton();
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        return createZeroButton();
    }

    private JButton createZeroButton() {
        JButton button = new JButton();
        button.setPreferredSize(new Dimension(0, 0));
        button.setMinimumSize(new Dimension(0, 0));
        button.setMaximumSize(new Dimension(0, 0));
        return button;
    }

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        // subtelne tło tracka (glass effect)
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(new Color(18, 22, 52, 40));
        g2.fillRoundRect(
                trackBounds.x,
                trackBounds.y,
                trackBounds.width,
                trackBounds.height,
                12,
                12
        );

        g2.dispose();
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {

        if (thumbBounds.isEmpty() || !scrollbar.isEnabled()) return;

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // hover detection (prosty trick)
        Point mouse = c.getMousePosition();
        hovered = mouse != null &&
                thumbBounds.contains(mouse);

        Color base = hovered ? thumbHoverColor : thumbColor;

        // cień (3D effect)
        g2.setColor(new Color(0, 0, 0, 80));
        g2.fillRoundRect(
                thumbBounds.x + 2,
                thumbBounds.y + 2,
                thumbBounds.width - 4,
                thumbBounds.height,
                12,
                12
        );

        // gradient thumb
        GradientPaint gp = new GradientPaint(
                0, thumbBounds.y, base.brighter(),
                0, thumbBounds.y + thumbBounds.height, base.darker()
        );

        g2.setPaint(gp);
        g2.fillRoundRect(
                thumbBounds.x + 1,
                thumbBounds.y,
                thumbBounds.width - 2,
                thumbBounds.height,
                12,
                12
        );

        // highlight (glass shine)
        g2.setColor(new Color(255, 255, 255, 35));
        g2.fillRoundRect(
                thumbBounds.x + 2,
                thumbBounds.y + 2,
                thumbBounds.width - 6,
                thumbBounds.height / 2,
                10,
                10
        );

        g2.dispose();
    }
}