package org.app.panels;

import org.app.AppFonts;
import org.app.RoundedPanel;
import org.app.functions.ScrollablePanel;
import org.app.functions.scrollbar;

import javax.swing.*;
import java.awt.*;

public class dailyPanel extends JPanel {

    public dailyPanel(CardLayout mainCl, JPanel mainCards) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder());
        setOpaque(false);

        ScrollablePanel contentDailyPanel = new ScrollablePanel();
        contentDailyPanel.setLayout(new BoxLayout(contentDailyPanel, BoxLayout.Y_AXIS));
        contentDailyPanel.setBorder(BorderFactory.createEmptyBorder(14, 12, 14, 12));
        contentDailyPanel.setOpaque(false);

        // Title section
        JLabel titleLabel = new JLabel("Daily");
        titleLabel.setFont(AppFonts.bold(70f));
        titleLabel.setForeground(new Color(245, 241, 255));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel titlePanel = new RoundedPanel(
                new FlowLayout(FlowLayout.CENTER, 0, 5),
                new Color(18, 22, 52, 180),
                30
        );

        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 26, 10, 26));
        titlePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        titlePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titlePanel.add(titleLabel);

        contentDailyPanel.add(titlePanel);
        contentDailyPanel.add(Box.createVerticalStrut(14));

        // Tarot draw button
        JButton buttonTarot = new JButton("Daily Tarot draw");
        styleButton(buttonTarot);

        buttonTarot.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonTarot.setMaximumSize(new Dimension(Integer.MAX_VALUE, 48));

        contentDailyPanel.add(buttonTarot);

        buttonTarot.addActionListener(e -> mainCl.show(mainCards, "Tarot"));

        contentDailyPanel.add(Box.createVerticalStrut(14));

        // Horoscope sub-panel
        horoscopePanel horoscopePanel = new horoscopePanel();
        horoscopePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        contentDailyPanel.add(horoscopePanel);

        // Moon Phase sub-panel - comment when not working on it, only 80 api requests per day (refresh 00:00UTC)
         MoonPhasePanel moonPhasePanel = new MoonPhasePanel();
         contentDailyPanel.add(moonPhasePanel);

        // Scrollable wrapper for main daily content
        JScrollPane scrollPaneDaily = new JScrollPane(contentDailyPanel);
        scrollPaneDaily.setBorder(BorderFactory.createEmptyBorder());

        scrollPaneDaily.setOpaque(false);
        scrollPaneDaily.getViewport().setOpaque(false);

        scrollPaneDaily.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );

        scrollPaneDaily.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
        );

        scrollPaneDaily.getVerticalScrollBar().setUI(new scrollbar());

        add(scrollPaneDaily, "Daily");
    }

    private void styleButton(JButton button) {
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(true);
        button.setOpaque(true);

        button.setBackground(new Color(92, 75, 150));
        button.setForeground(new Color(250, 246, 255));
        button.setFont(AppFonts.regular(16f));

        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(170, 160, 230), 1, true),
                BorderFactory.createEmptyBorder(10, 18, 10, 18)
        ));
    }
}