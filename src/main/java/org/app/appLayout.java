package org.app;
import org.app.functions.getMenuButton;
import org.app.panels.bottomMenu;
import org.app.panels.dailyPanel;

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
        JPanel readingsPanel = new JPanel(new FlowLayout());
        JPanel compatibilityPanel = new JPanel(new FlowLayout());
        JPanel mePanel = new JPanel(new FlowLayout());

        JPanel cardLayout = new JPanel(new CardLayout());
        cardLayout.add(dailyPanel, "Daily");
        cardLayout.add(readingsPanel, "Readings");
        cardLayout.add(compatibilityPanel, "Compatibility");
        cardLayout.add(mePanel, "Me");
        cardLayout.setBorder(BorderFactory.createEmptyBorder());

        CardLayout cl = (CardLayout) cardLayout.getLayout();
        bottomMenu bottomMenu = new bottomMenu(cl,cardLayout);


        //menu=======================================================================================
        getMenuButton menuButton = new getMenuButton();
        JPanel menuPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10,10));
        menuPanel.setBackground(new Color(219, 216, 206));
        menuPanel.add(menuButton);


        //daily panel=======================================================================================


        //readings panel=======================================================================================
        readingsPanel.setBackground(new Color(219, 216, 206));
        readingsPanel.add(new JLabel("Readings"));

        //compatibility panel=======================================================================================
        compatibilityPanel.setBackground(new Color(219, 216, 206));
        compatibilityPanel.add(new JLabel("Compatibility"));

        //me panel=======================================================================================
        mePanel.setBackground(new Color(219, 216, 206));
        mePanel.add(new JLabel("Me"));





        //window & main panel=======================================================================================
        mainPanel.add(cardLayout, BorderLayout.CENTER);
        mainPanel.add(menuPanel, BorderLayout.WEST);
        window.add(bottomMenu,  BorderLayout.SOUTH);
        window.add(mainPanel, BorderLayout.CENTER);

        add(window, BorderLayout.CENTER);
        setVisible(true);
    }

}
