package org.app.panels;

import org.app.AppFonts;
import org.app.RoundedPanel;
import org.app.functions.GetHoroscope;
import org.app.userData;

import javax.swing.*;
import java.awt.*;

public class horoscopePanel extends JPanel {

    public horoscopePanel() {
        setLayout(new BorderLayout());
        setOpaque(false);
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 600));

        JPanel cardLayout = new JPanel(new CardLayout());
        cardLayout.setOpaque(false);

        JTextArea dailyHoroscopeContent = new JTextArea();
        JPanel dailyHoroscopePanel = createHoroscopePanel(
                "Daily Horoscope",
                dailyHoroscopeContent,
                "daily"
        );

        JTextArea weeklyHoroscopeContent = new JTextArea();
        JPanel weeklyHoroscopePanel = createHoroscopePanel(
                "Weekly Horoscope",
                weeklyHoroscopeContent,
                "weekly"
        );

        JTextArea monthlyHoroscopeContent = new JTextArea();
        JPanel monthlyHoroscopePanel = createHoroscopePanel(
                "Monthly Horoscope",
                monthlyHoroscopeContent,
                "monthly"
        );

        cardLayout.add(dailyHoroscopePanel, "Daily");
        cardLayout.add(weeklyHoroscopePanel, "Weekly");
        cardLayout.add(monthlyHoroscopePanel, "Monthly");

        CardLayout cl = (CardLayout) cardLayout.getLayout();

        // Period selector combo box
        String[] choices = new String[]{"Daily", "Weekly", "Monthly"};

        JComboBox<String> comboBox = new JComboBox<>(choices);
        comboBox.setFont(AppFonts.regular(14f));
        comboBox.setSelectedIndex(0);
        comboBox.setFocusable(false);

        comboBox.setBackground(new Color(67, 58, 120));
        comboBox.setForeground(new Color(245, 241, 255));

        comboBox.addActionListener(e -> {
            cl.show(cardLayout, (String) comboBox.getSelectedItem());

            dailyHoroscopeContent.setText("");

            repaint(
                    userData.getZodiac().toLowerCase(),
                    dailyHoroscopeContent,
                    weeklyHoroscopeContent,
                    monthlyHoroscopeContent
            );
        });

        // Wrap combo box in a centered panel so it doesn't stretch full width
        JPanel comboPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        comboPanel.setOpaque(false);
        comboPanel.add(comboBox);

        add(comboPanel, BorderLayout.NORTH);
        add(cardLayout, BorderLayout.CENTER);
    }

    public void repaint(
            String zodiacSign,
            JTextArea daily,
            JTextArea weekly,
            JTextArea monthly
    ) {
        daily.setText(
                userData.getZodiac() + ": " +
                        GetHoroscope.horoscopeData(zodiacSign.toLowerCase(), "daily")
        );

        weekly.setText(
                userData.getZodiac() + ": " +
                        GetHoroscope.horoscopeData(zodiacSign.toLowerCase(), "weekly")
        );

        monthly.setText(
                userData.getZodiac() + ": " +
                        GetHoroscope.horoscopeData(zodiacSign.toLowerCase(), "monthly")
        );
    }

    private JPanel createHoroscopePanel(
            String title,
            JTextArea contentArea,
            String horoscopeType
    ) {
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setOpaque(false);
        wrapper.setBorder(BorderFactory.createEmptyBorder(6, 0, 0, 0));

        RoundedPanel panel = new RoundedPanel(
                new BorderLayout(0, 12),
                new Color(18, 22, 52, 180),
                30
        );

        panel.setBorder(BorderFactory.createEmptyBorder(18, 18, 18, 18));

        JLabel label = new JLabel(title, SwingConstants.CENTER);
        label.setFont(AppFonts.bold(36f));
        label.setForeground(new Color(245, 241, 255));

        contentArea.setText("");
        contentArea.setOpaque(false);
        contentArea.setEditable(false);
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);
        contentArea.setFont(AppFonts.regular(14f));
        contentArea.setForeground(new Color(240, 236, 255));
        contentArea.setMargin(new Insets(8, 12, 8, 12));

        contentArea.setText(
                userData.getZodiac() + ": " +
                        GetHoroscope.horoscopeData(
                                userData.getZodiac().toLowerCase(),
                                horoscopeType
                        )
        );

        JScrollPane textScrollPane = new JScrollPane(contentArea);
        textScrollPane.setBorder(BorderFactory.createEmptyBorder());
        textScrollPane.setOpaque(false);
        textScrollPane.getViewport().setOpaque(false);

        textScrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );

        textScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
        );

        panel.add(label, BorderLayout.NORTH);
        panel.add(textScrollPane, BorderLayout.CENTER);

        wrapper.add(panel, BorderLayout.CENTER);

        return wrapper;
    }
}