package org.app.panels;

import org.app.AppFonts;
import org.app.functions.GetHoroscope;
import org.app.userData;

import javax.swing.*;
import java.awt.*;

public class horoscopePanel extends JPanel {
    public horoscopePanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(219, 216, 206));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 600));

        JPanel cardLayout = new JPanel(new CardLayout());

        JTextArea dailyHoroscopeContent = new JTextArea();
        JPanel dailyHoroscopePanel = createHoroscopePanel("Daily Horoscope", dailyHoroscopeContent, "daily");

        JTextArea weeklyHoroscopeContent = new JTextArea();
        JPanel weeklyHoroscopePanel = createHoroscopePanel("Weekly Horoscope", weeklyHoroscopeContent, "weekly");

        JTextArea monthlyHoroscopeContent = new JTextArea();
        JPanel monthlyHoroscopePanel = createHoroscopePanel("Monthly Horoscope", monthlyHoroscopeContent, "monthly");

        cardLayout.add(dailyHoroscopePanel, "Daily");
        cardLayout.add(weeklyHoroscopePanel, "Weekly");
        cardLayout.add(monthlyHoroscopePanel, "Monthly");
        CardLayout cl = (CardLayout) cardLayout.getLayout();

        // Period selector combo box — centered in its own panel
        String[] choices = new String[]{"Daily", "Weekly", "Monthly"};
        JComboBox<String> comboBox = new JComboBox<>(choices);
        comboBox.setFont(AppFonts.regular(14f));
        comboBox.setSelectedIndex(0);
        comboBox.addActionListener(_ -> {
            cl.show(cardLayout, (String) comboBox.getSelectedItem());
            dailyHoroscopeContent.setText("");
            repaint(userData.getZodiac().toLowerCase(), dailyHoroscopeContent, weeklyHoroscopeContent, monthlyHoroscopeContent);
        });

        // Wrap combo box in a centered panel so it doesn't stretch full width
        JPanel comboPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        comboPanel.setBackground(new Color(219, 216, 206));
        comboPanel.add(comboBox);

        add(comboPanel, BorderLayout.NORTH);
        add(cardLayout, BorderLayout.CENTER);
    }

    public void repaint(String zodiacSign, JTextArea daily, JTextArea weekly, JTextArea monthly) {
        daily.setText(userData.getZodiac() + ": " + GetHoroscope.horoscopeData(zodiacSign.toLowerCase(), "daily"));
        weekly.setText(userData.getZodiac() + ": " + GetHoroscope.horoscopeData(zodiacSign.toLowerCase(), "weekly"));
        monthly.setText(userData.getZodiac() + ": " + GetHoroscope.horoscopeData(zodiacSign.toLowerCase(), "monthly"));
    }

    /**
     * Creates a horoscope sub-panel with a title and a text area.
     * Uses BorderLayout so the JTextArea is constrained to the parent width
     * and wraps text properly instead of overflowing.
     */
    private JPanel createHoroscopePanel(String title, JTextArea contentArea, String horoscopeType) {
        // BorderLayout constrains the text area width to the parent container
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(219, 216, 206));

        JLabel label = new JLabel(title, SwingConstants.CENTER);
        label.setFont(AppFonts.bold(36f));

        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        labelPanel.setBackground(new Color(219, 216, 206));
        labelPanel.add(label);

        panel.add(labelPanel, BorderLayout.NORTH);

        contentArea.setText("");
        contentArea.setBackground(new Color(219, 216, 206));
        contentArea.setEditable(false);
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);
        contentArea.setFont(AppFonts.regular(14f));
        contentArea.setMargin(new Insets(8, 12, 8, 12));
        contentArea.setText(userData.getZodiac() + ": " + GetHoroscope.horoscopeData(userData.getZodiac().toLowerCase(), horoscopeType));

        // Place text area in CENTER so it fills available width and wraps properly
        panel.add(contentArea, BorderLayout.CENTER);

        return panel;
    }
}
