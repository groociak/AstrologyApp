package org.app;

import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {
    private final Image backgroundImage;

    public BackgroundPanel(String path) {
        backgroundImage = new ImageIcon(getClass().getResource(path)).getImage();
        setLayout(new BorderLayout());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

        // lekko przyciemnia tło, żeby napisy były czytelniejsze
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(new Color(10, 12, 30, 90));
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.dispose();
    }
}