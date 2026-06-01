package org.app.panels;

import org.app.AppFonts;
import org.app.RoundedPanel;
import org.app.ModernButton;

import javax.swing.*;
import java.awt.*;

public class readingsPanel extends JPanel {

    public readingsPanel(CardLayout mainCl, JPanel mainCards) {
        setLayout(new BorderLayout(0, 15));
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        // Title section
        JLabel titleLabel = new JLabel("Readings");
        titleLabel.setFont(AppFonts.bold(70f));
        titleLabel.setForeground(new Color(245, 241, 255));

        JPanel titlePanel = new RoundedPanel(
                new FlowLayout(FlowLayout.CENTER, 0, 5),
                new Color(18, 22, 52, 180),
                30
        );
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 26, 10, 26));
        titlePanel.add(titleLabel);

        JPanel titleWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titleWrapper.setOpaque(false);
        titleWrapper.add(titlePanel);

        add(titleWrapper, BorderLayout.NORTH);

        // Big transparent content box
        RoundedPanel contentBox = new RoundedPanel(
                new BorderLayout(),
                new Color(18, 22, 52, 180),
                30
        );
        contentBox.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        // Tarot daily button
        ModernButton buttonTarot = new ModernButton("Daily Tarot Draw");
        buttonTarot.setFont(AppFonts.regular(15f));
        buttonTarot.setPreferredSize(new Dimension(210, 58));
        buttonTarot.addActionListener(e -> mainCl.show(mainCards, "Tarot"));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);
        buttonPanel.add(buttonTarot);

        contentBox.add(buttonPanel, BorderLayout.NORTH);

        add(contentBox, BorderLayout.CENTER);
    }

}