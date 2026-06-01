package org.app.functions;

import org.app.ModernButton;

import javax.swing.*;
import java.awt.*;

public class getMenuButton extends ModernButton {

    public getMenuButton(CardLayout cl, JPanel cardLayout) {
        super("☰");

        setPreferredSize(new Dimension(58, 46));

        // Zostaje SansSerif, bo pixel font robił biały kwadrat
        setFont(new Font("SansSerif", Font.BOLD, 22));

        // To centruje znak w widocznej części przycisku
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
        setHorizontalTextPosition(SwingConstants.CENTER);
        setVerticalTextPosition(SwingConstants.CENTER);

        // ModernButton ma cień po prawej i na dole, więc dajemy małą korektę
        setBorder(BorderFactory.createEmptyBorder(0, 0, 6, 6));
        setMargin(new Insets(0, 0, 0, 0));

        JPopupMenu popupMenu = new JPopupMenu();
        popupMenu.setBackground(new Color(18, 22, 52));
        popupMenu.setBorder(
                BorderFactory.createLineBorder(new Color(170, 160, 230), 1)
        );

        JMenuItem userItem = new JMenuItem("User");
        styleMenuItem(userItem);
        userItem.addActionListener(e -> cl.show(cardLayout, "User"));

        JMenuItem exitItem = new JMenuItem("Exit");
        styleMenuItem(exitItem);
        exitItem.addActionListener(e -> System.exit(0));

        popupMenu.add(userItem);
        popupMenu.addSeparator();
        popupMenu.add(exitItem);

        addActionListener(e -> popupMenu.show(this, 0, getHeight()));
    }

    private void styleMenuItem(JMenuItem item) {
        item.setOpaque(true);
        item.setBackground(new Color(18, 22, 52));
        item.setForeground(new Color(245, 241, 255));
        item.setFont(new Font("SansSerif", Font.PLAIN, 14));
    }
}