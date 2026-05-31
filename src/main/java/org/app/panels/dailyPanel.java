package org.app.panels;

import org.app.functions.scrollbar;
import org.app.functions.ScrollablePanel;
import org.app.AppFonts;

import javax.swing.*;
import java.awt.*;


public class dailyPanel extends JPanel {
    public dailyPanel(CardLayout mainCl, JPanel mainCards) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder());
        setBackground(new Color(219, 216, 206));

        ScrollablePanel contentDailyPanel = new ScrollablePanel();
        contentDailyPanel.setLayout(new BoxLayout(contentDailyPanel, BoxLayout.Y_AXIS));
        contentDailyPanel.setBackground(new Color(219, 216, 206));

        JPanel TarotPanel = new JPanel(new BorderLayout());
        TarotPanel.setBackground(new Color(219, 216, 206));

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
        buttonTarot.addActionListener(_ -> mainCl.show(mainCards, "Tarot"));

        contentDailyPanel.add(Box.createVerticalStrut(10));

        // Horoscope sub-panel — fills remaining width via BorderLayout wrapping
        horoscopePanel horoscopePanel = new horoscopePanel();
        contentDailyPanel.add(horoscopePanel);

        // Moon Phase sub-panel - comment when not working on it, only 80 api requests per day (refresh 00:00UTC)
        MoonPhasePanel moonPhasePanel = new MoonPhasePanel();
        contentDailyPanel.add(moonPhasePanel);


        // Scrollable wrapper for main daily content
        JScrollPane scrollPaneDaily = new JScrollPane(contentDailyPanel);
        scrollPaneDaily.setBorder(BorderFactory.createEmptyBorder());
        scrollPaneDaily.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );
        scrollPaneDaily.getViewport().setBackground(new Color(219, 216, 206));
        scrollPaneDaily.getVerticalScrollBar().setUI(new scrollbar());
        add(scrollPaneDaily, "Daily");
    }
}
