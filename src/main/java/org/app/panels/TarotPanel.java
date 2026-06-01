package org.app.panels;

import org.app.AppFonts;

import javax.swing.*;
import java.awt.*;

public class TarotPanel extends JPanel {

    private final Color TAROT_BACKGROUND = new Color(120, 105, 185);
    private final Color BUTTON_COLOR = new Color(92, 75, 150);
    private final Color TEXT_COLOR = new Color(250, 246, 255);
    private final Color BORDER_COLOR = new Color(170, 160, 230);

    public TarotPanel(CardLayout mainCl, JPanel mainCards) {
        setLayout(new BorderLayout());
        setOpaque(true);
        setBackground(TAROT_BACKGROUND);

        // Panel for top buttons
        JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 8));
        northPanel.setOpaque(true);
        northPanel.setBackground(TAROT_BACKGROUND);

        JButton backButton = new JButton("<--");
        JButton drawButton = new JButton("Draw");

        styleButton(backButton);
        styleButton(drawButton);

        backButton.addActionListener(e ->
                mainCl.show(mainCards, "Daily")
        );

        northPanel.add(backButton);
        northPanel.add(drawButton);

        add(northPanel, BorderLayout.NORTH);

        // Cards displayed in the center
        TarotCardsPanel tarotCardsPanel = new TarotCardsPanel(drawButton);
        tarotCardsPanel.setOpaque(true);
        tarotCardsPanel.setBackground(TAROT_BACKGROUND);

        add(tarotCardsPanel, BorderLayout.CENTER);
    }

    private void styleButton(JButton button) {
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(true);
        button.setOpaque(true);

        button.setBackground(BUTTON_COLOR);
        button.setForeground(TEXT_COLOR);
        button.setFont(AppFonts.regular(14f));

        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER_COLOR, 1, true),
                BorderFactory.createEmptyBorder(8, 16, 8, 16)
        ));
    }
}