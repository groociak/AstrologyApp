package org.app;
import org.app.functions.getMenuButton;
import org.app.panels.*;

import javax.swing.*;
import java.awt.*;



public class appLayout extends JFrame {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public appLayout() {
        setTitle("Astrology App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Proportional window size: 30% of screen width, 90% of screen height
        int windowWidth = (int) (screenSize.getWidth() * 0.3);
        int windowHeight = (int) (screenSize.getHeight() * 0.9);
        setSize(windowWidth, windowHeight);
        setMinimumSize(new Dimension(480, 400));
        setLocationRelativeTo(null);

        // Main containers
        JPanel window = new JPanel(new BorderLayout());
        JPanel mainPanel = new JPanel(new BorderLayout());


        compatibilityPanel compatibilityPanel = new compatibilityPanel();
        mePanel mePanel = new mePanel();
        userPanel userPanel = new userPanel();

        JPanel cardLayout = new JPanel(new CardLayout());
        CardLayout cl = (CardLayout) cardLayout.getLayout();

        TarotPanel tarotScreen = new TarotPanel(cl, cardLayout);
        dailyPanel dailyPanel = new dailyPanel(cl, cardLayout);
        readingsPanel readingsPanel = new readingsPanel(cl, cardLayout);

        cardLayout.add(dailyPanel, "Daily");
        cardLayout.add(readingsPanel, "Readings");
        cardLayout.add(compatibilityPanel, "Compatibility");
        cardLayout.add(mePanel, "Me");
        cardLayout.add(userPanel, "User");
        cardLayout.add(tarotScreen, "Tarot");
        cardLayout.setBorder(BorderFactory.createEmptyBorder());

        bottomMenu bottomMenu = new bottomMenu(cl, cardLayout);

        // Top menu bar with hamburger button
        getMenuButton menuButton = new getMenuButton(cl, cardLayout);
        JPanel menuPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        menuPanel.setBackground(new Color(219, 216, 206));
        menuPanel.add(menuButton);

        // Assemble window and main panel
        mainPanel.add(cardLayout, BorderLayout.CENTER);
        mainPanel.add(menuPanel, BorderLayout.NORTH);
        window.add(bottomMenu, BorderLayout.SOUTH);
        window.add(mainPanel, BorderLayout.CENTER);

        add(window, BorderLayout.CENTER);
        setVisible(true);
    }

}
