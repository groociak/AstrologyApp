package org.example;
import javax.swing.*;
import java.awt.*;


public class appLayout extends JFrame {


    public appLayout() {
        setTitle("Astrology App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 1000);
        setLocationRelativeTo(null);

        //containers=======================================================================================
        JPanel window = new JPanel(new BorderLayout());
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10,10));
        JPanel dailyPanel = new JPanel(new BorderLayout());
        JPanel readingsPanel = new JPanel(new FlowLayout());
        JPanel compatibilityPanel = new JPanel(new FlowLayout());
        JPanel mePanel = new JPanel(new FlowLayout());

        JPanel cardLayout = new JPanel(new CardLayout());
        cardLayout.add(dailyPanel, "Daily");
        cardLayout.add(readingsPanel, "Readings");
        cardLayout.add(compatibilityPanel, "Compatibility");
        cardLayout.add(mePanel, "Me");
        CardLayout cl = (CardLayout) cardLayout.getLayout();

        //menu=======================================================================================
        JButton menuButton = getMenuButton();
        JPanel menuPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10,10));
        menuPanel.setBackground(new Color(219, 216, 206));
        menuPanel.add(menuButton);


        //daily panel=======================================================================================
        dailyPanel.setBackground(new Color(219, 216, 206));
        dailyPanel.add(new JLabel("Daily"));

        //readings panel=======================================================================================
        readingsPanel.setBackground(new Color(219, 216, 206));
        readingsPanel.add(new JLabel("Readings"));

        //compatibility panel=======================================================================================
        compatibilityPanel.setBackground(new Color(219, 216, 206));
        compatibilityPanel.add(new JLabel("Compatibility"));

        //me panel=======================================================================================
        mePanel.setBackground(new Color(219, 216, 206));
        mePanel.add(new JLabel("Me"));

        //bottomPanel=======================================================================================
        bottomPanel.setBackground(new Color(55, 50, 28));
        bottomPanel.setPreferredSize(new Dimension(600, 90));

        JButton buttonDaily = new JButton("Daily"); //horoscope, moonphase
        buttonDaily.setPreferredSize(new Dimension(70, 70));
        bottomPanel.add(buttonDaily);
        bottomPanel.add(Box.createHorizontalStrut(50));
        buttonDaily.addActionListener(_ -> cl.show(cardLayout, "Daily"));

        JButton buttonReading = new JButton("Readings"); //tarot readings
        buttonReading.setPreferredSize(new Dimension(70, 70));
        bottomPanel.add(buttonReading);
        bottomPanel.add(Box.createHorizontalStrut(50));
        buttonReading.addActionListener(_ -> cl.show(cardLayout, "Readings"));

        JButton buttonCompatibility = new JButton("Compatibility"); //compatibility
        buttonCompatibility.setPreferredSize(new Dimension(70, 70));
        bottomPanel.add(buttonCompatibility);
        bottomPanel.add(Box.createHorizontalStrut(50));
        buttonCompatibility.addActionListener(_ -> cl.show(cardLayout, "Compatibility"));

        JButton buttonMe = new JButton("Me"); //Me: Birthchart, zodiac sign
        buttonMe.setPreferredSize(new Dimension(70, 70));
        bottomPanel.add(buttonMe);
        buttonMe.addActionListener(_ -> cl.show(cardLayout, "Me"));



        //window=======================================================================================
        window.add(menuPanel, BorderLayout.WEST);
        window.add(bottomPanel, BorderLayout.SOUTH);
        window.add(cardLayout, BorderLayout.CENTER);
        add(window, BorderLayout.CENTER);
        setVisible(true);
    }

    private static JButton getMenuButton() {
        JButton menuButton = new JButton("≡");
        menuButton.setPreferredSize(new Dimension(70, 70));
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem userItem = new JMenuItem("User");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(_ -> System.exit(0));
        popupMenu.add(userItem);
        popupMenu.addSeparator();
        popupMenu.add(exitItem);
        menuButton.addActionListener(_ -> popupMenu.show(menuButton, 0, menuButton.getHeight()));
        return menuButton;
    }
}
