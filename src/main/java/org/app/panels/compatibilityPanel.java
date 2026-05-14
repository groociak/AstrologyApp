package org.app.panels;

import org.app.AppFonts;

import javax.swing.*;
import java.awt.*;

public class compatibilityPanel extends JPanel {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public compatibilityPanel() {
        setBackground(new Color(219, 216, 206));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLabel titleLabel = new JLabel("Compatibility");
        titleLabel.setFont(AppFonts.bold(70f));
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(219, 216, 206));
        titlePanel.setMaximumSize(new Dimension((int) screenSize.getWidth(),100));
        titlePanel.add(titleLabel);
        add(titlePanel);
    }
}
