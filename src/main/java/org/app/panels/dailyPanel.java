package org.app.panels;

import org.app.functions.scrollbar;
import org.app.functions.ScrollablePanel;
import org.app.AppFonts;

import javax.swing.*;
import java.awt.*;


public class dailyPanel extends JPanel {
    public dailyPanel() {
        setLayout(new CardLayout());
        setBorder(BorderFactory.createEmptyBorder());

        ScrollablePanel contentDailyPanel = new ScrollablePanel();
        contentDailyPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        contentDailyPanel.setLayout(new BoxLayout(contentDailyPanel, BoxLayout.Y_AXIS));
        contentDailyPanel.setBackground(new Color(219, 216, 206));

        JPanel TarotPanel = new JPanel(new BorderLayout());
        TarotPanel.setBackground(new Color(219, 216, 206));

        add(TarotPanel, "Tarot");
        CardLayout cl = (CardLayout) getLayout();

        // Title section — centered with constrained height
        JLabel titleLabel = new JLabel("Daily");
        titleLabel.setFont(AppFonts.bold(70f));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 5));
        titlePanel.setBackground(new Color(219, 216, 206));
        titlePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 90));
        titlePanel.add(titleLabel);
        contentDailyPanel.add(titlePanel);

        // Tarot draw button — full width with margins
        JButton buttonTarot = new JButton("Daily Tarot draw");
        buttonTarot.setFont(AppFonts.regular(18f));
        buttonTarot.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonTarot.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        buttonTarot.setBackground(new Color(219, 216, 206));
        buttonTarot.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(55, 50, 28), 2),
                BorderFactory.createEmptyBorder(8, 20, 8, 20)
        ));
        contentDailyPanel.add(buttonTarot);
        buttonTarot.addActionListener(_ -> cl.show(this, "Tarot"));

        contentDailyPanel.add(Box.createVerticalStrut(10));

        // Horoscope sub-panel — fills remaining width via BorderLayout wrapping
        horoscopePanel horoscopePanel = new horoscopePanel();
        contentDailyPanel.add(horoscopePanel);

        // Tarot panel elements — top navigation bar
        JPanel northTarotPanel = new JPanel(new FlowLayout());
        northTarotPanel.setBackground(new Color(219, 216, 206));
        JButton backButton = new JButton("<--");
        backButton.addActionListener(_ -> cl.show(this, "Daily"));
        JButton drawButton = new JButton("Draw");
        northTarotPanel.add(backButton);
        northTarotPanel.add(drawButton);
        TarotPanel.add(northTarotPanel, BorderLayout.NORTH);

        // Tarot card display area
        TarotPanel cardPanel = new TarotPanel(drawButton);
        TarotPanel.add(cardPanel, BorderLayout.CENTER);

        // Scrollable wrapper for main daily content
        JScrollPane scrollPaneDaily = new JScrollPane(contentDailyPanel);
        scrollPaneDaily.setBorder(BorderFactory.createEmptyBorder());
        scrollPaneDaily.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );
        scrollPaneDaily.getViewport().setBackground(new Color(219, 216, 206));
        scrollPaneDaily.getVerticalScrollBar().setUI(new scrollbar());
        add(scrollPaneDaily, "Daily");
        cl.show(this, "Daily");
    }
}
