package org.app.panels;

import javax.swing.*;
import java.awt.*;

public class bottomMenu extends JPanel {
    public bottomMenu(CardLayout cl, JPanel cardLayout) {
        setLayout(new GridLayout(1, 4, 10, 0));
        setBackground(new Color(55, 50, 28));

        // Proportional height based on screen size, fill width
        int barHeight = Math.max(60, (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.07));
        setPreferredSize(new Dimension(Integer.MAX_VALUE, barHeight));

        // Navigation buttons — evenly distributed by GridLayout
        JButton buttonDaily = new JButton("Daily"); // Acts as redirect to main page
        add(buttonDaily);
        buttonDaily.addActionListener(_ -> cl.show(cardLayout, "Daily"));

        JButton buttonReading = new JButton("Readings");
        add(buttonReading);
        buttonReading.addActionListener(_ -> cl.show(cardLayout, "Readings"));

        JButton buttonCompatibility = new JButton("Compatibility");
        add(buttonCompatibility);
        buttonCompatibility.addActionListener(_ -> {
            compatibilityPanel.updateComp();
            cl.show(cardLayout, "Compatibility");
        });

        JButton buttonMe = new JButton("Me");
        add(buttonMe);
        buttonMe.addActionListener(_ -> {
            mePanel.updateChart();
            cl.show(cardLayout, "Me");
        });
    }

}
