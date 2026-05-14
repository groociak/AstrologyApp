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
        setSize(700, (int) screenSize.getHeight());
        setLocationRelativeTo(null);

        //containers=======================================================================================
        JPanel window = new JPanel(new BorderLayout());
        JPanel mainPanel = new JPanel(new BorderLayout());

        dailyPanel dailyPanel = new dailyPanel();
        readingsPanel readingsPanel = new readingsPanel();
        compatibilityPanel compatibilityPanel = new compatibilityPanel();
        mePanel mePanel = new mePanel();
        userPanel userPanel = new userPanel();

        JPanel cardLayout = new JPanel(new CardLayout());
        cardLayout.add(dailyPanel, "Daily");
        cardLayout.add(readingsPanel, "Readings");
        cardLayout.add(compatibilityPanel, "Compatibility");
        cardLayout.add(mePanel, "Me");
        cardLayout.add(userPanel, "User");
        cardLayout.setBorder(BorderFactory.createEmptyBorder());

        CardLayout cl = (CardLayout) cardLayout.getLayout();
        bottomMenu bottomMenu = new bottomMenu(cl,cardLayout);


        //menu=======================================================================================
        getMenuButton menuButton = new getMenuButton(cl,cardLayout);
        JPanel menuPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10,10));
        menuPanel.setBackground(new Color(219, 216, 206));
        menuPanel.add(menuButton);






        //window & main panel=======================================================================================
        mainPanel.add(cardLayout, BorderLayout.CENTER);
        mainPanel.add(menuPanel, BorderLayout.WEST);
        window.add(bottomMenu,  BorderLayout.SOUTH);
        window.add(mainPanel, BorderLayout.CENTER);

        add(window, BorderLayout.CENTER);
        setVisible(true);
    }

}
