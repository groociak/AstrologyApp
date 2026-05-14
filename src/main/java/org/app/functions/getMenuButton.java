package org.app.functions;

import javax.swing.*;
import java.awt.*;

public class getMenuButton extends JButton {
    public getMenuButton(CardLayout cl, JPanel cardLayout) {
        super("≡");
        setPreferredSize(new Dimension(70, 70));
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem userItem = new JMenuItem("User");
        userItem.addActionListener(_ -> cl.show(cardLayout, "User"));
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(_ -> System.exit(0));
        popupMenu.add(userItem);
        popupMenu.addSeparator();
        popupMenu.add(exitItem);
        addActionListener(_ -> popupMenu.show(this, 0, getHeight()));
    }
}
