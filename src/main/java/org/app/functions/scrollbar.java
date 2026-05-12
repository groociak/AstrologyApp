package org.app.functions;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class scrollbar extends BasicScrollBarUI {

        private final Dimension thumbSize = new Dimension(8, 20);

        @Override
        protected Dimension getMinimumThumbSize() {
            return thumbSize;
        }

        @Override
        protected void configureScrollBarColors() {

            thumbColor = new Color(90, 80, 60, 180);
            trackColor = new Color(0, 0, 0, 0);
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
            // brak tła
        }

        @Override
        protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {

            if (thumbBounds.isEmpty() || !scrollbar.isEnabled()) {
                return;
            }

            Graphics2D g2 = (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            g2.setColor(thumbColor);

            g2.fillRoundRect(
                    thumbBounds.x + 2,
                    thumbBounds.y,
                    thumbBounds.width - 4,
                    thumbBounds.height,
                    10,
                    10
            );

            g2.dispose();
        }

}
