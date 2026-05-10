package org.app;

import javax.swing.*;
import java.awt.*;

public class bottomMenu extends JPanel {
    public bottomMenu(CardLayout cl, JPanel cardLayout) {
        setLayout(new FlowLayout(FlowLayout.CENTER, 10,10));
        setBackground(new Color(55, 50, 28));
        setPreferredSize(new Dimension(600, 90));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        setMaximumSize(new Dimension(600, 90));

        JButton buttonDaily = new JButton("Daily"); //horoscope, moonphase
        buttonDaily.setPreferredSize(new Dimension(70, 70));
        add(buttonDaily);
        add(Box.createHorizontalStrut(50));
        buttonDaily.addActionListener(_ -> cl.show(cardLayout, "Daily"));

        JButton buttonReading = new JButton("Readings"); //tarot readings
        buttonReading.setPreferredSize(new Dimension(70, 70));
        add(buttonReading);
        add(Box.createHorizontalStrut(50));
        buttonReading.addActionListener(_ -> cl.show(cardLayout, "Readings"));

        JButton buttonCompatibility = new JButton("Compatibility"); //compatibility
        buttonCompatibility.setPreferredSize(new Dimension(70, 70));
        add(buttonCompatibility);
        add(Box.createHorizontalStrut(50));
        buttonCompatibility.addActionListener(_ -> cl.show(cardLayout, "Compatibility"));

        JButton buttonMe = new JButton("Me"); //Me: Birth chart, zodiac sign
        buttonMe.setPreferredSize(new Dimension(70, 70));
        add(buttonMe);
        buttonMe.addActionListener(_ -> cl.show(cardLayout, "Me"));
    }


}
