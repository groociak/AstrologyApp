package org.app.panels;

import org.app.AppFonts;
import org.app.ModernButton;

import javax.swing.*;
import java.awt.*;

public class bottomMenu extends JPanel {

    public bottomMenu(CardLayout cl, JPanel cardLayout) {
        setLayout(new GridLayout(1, 4, 12, 0));
        setBackground(new Color(10, 11, 30));
        setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        int barHeight = Math.max(
                70,
                (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.075)
        );

        setPreferredSize(new Dimension(Integer.MAX_VALUE, barHeight));

        ModernButton buttonDaily = new ModernButton("Daily");
        styleNavButton(buttonDaily);
        add(buttonDaily);

        buttonDaily.addActionListener(e -> cl.show(cardLayout, "Daily"));

        ModernButton buttonReading = new ModernButton("Readings");
        styleNavButton(buttonReading);
        add(buttonReading);

        buttonReading.addActionListener(e -> cl.show(cardLayout, "Readings"));

        ModernButton buttonCompatibility = new ModernButton("Compatibility");
        styleNavButton(buttonCompatibility);
        add(buttonCompatibility);

        buttonCompatibility.addActionListener(e -> {
            compatibilityPanel.updateComp();
            cl.show(cardLayout, "Compatibility");
        });

        ModernButton buttonMe = new ModernButton("Me");
        styleNavButton(buttonMe);
        add(buttonMe);

        buttonMe.addActionListener(e -> {
            mePanel.updateChart();
            cl.show(cardLayout, "Me");
        });
    }

    private void styleNavButton(JButton button) {
        button.setFont(AppFonts.regular(15f));
        button.setForeground(new Color(250, 246, 255));
    }
}