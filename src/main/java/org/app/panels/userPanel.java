package org.app.panels;

import org.app.AppFonts;

import javax.swing.*;
import java.awt.*;

public class userPanel extends JPanel {
    public userPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder());
        setBackground(new Color(219, 216, 206));

        JLabel titleLabel = new JLabel("ACCOUNT");
        titleLabel.setFont(AppFonts.bold(60f));
        add(titleLabel);
    }

}
