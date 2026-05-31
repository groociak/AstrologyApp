package org.app.panels;

import javax.swing.*;
import java.awt.*;

public class TarotPanel extends JPanel {

    public TarotPanel(CardLayout mainCl, JPanel mainCards) {
        setLayout(new BorderLayout());
        setBackground(new Color(219, 216, 206));

        //Panel for top buttons
        JPanel northPanel = new JPanel(new FlowLayout());
        northPanel.setBackground(new Color(219, 216, 206));

        JButton backButton = new JButton("<--");
        JButton drawButton = new JButton("Draw");

        backButton.addActionListener(_ ->
                mainCl.show(mainCards, "Daily"));

        northPanel.add(backButton);
        northPanel.add(drawButton);

        add(northPanel, BorderLayout.NORTH);

        //Cards displayed in the center
        TarotCardsPanel tarotCardsPanel = new TarotCardsPanel(drawButton);
        add(tarotCardsPanel, BorderLayout.CENTER);
    }
}