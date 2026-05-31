package org.app.panels;

import org.app.AppFonts;

import javax.swing.*;
import java.awt.*;

public class readingsPanel extends JPanel {
    public readingsPanel(CardLayout mainCl, JPanel mainCards) {
        setBackground(new Color(219, 216, 206));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));


        // Title section
        JLabel titleLabel = new JLabel("Readings");
        titleLabel.setFont(AppFonts.bold(70f));
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(219, 216, 206));
        titlePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        titlePanel.add(titleLabel);
        add(titlePanel);
        add(Box.createVerticalStrut(10));

        //Tarot daily button
        JButton buttonTarot = new JButton("Daily Tarot Draw");
        buttonTarot.setFont(AppFonts.regular(18f));
        buttonTarot.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonTarot.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        buttonTarot.setBackground(new Color(219, 216, 206));
        buttonTarot.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(55, 50, 28), 2),
                BorderFactory.createEmptyBorder(8, 20, 8, 20)
        ));
        buttonTarot.addActionListener(e -> mainCl.show(mainCards, "Tarot"));
        add(buttonTarot);

    }
}
