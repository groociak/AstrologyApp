package org.app.panels;

import org.app.AppFonts;
import org.app.functions.GetHoroscope;
import org.app.userData;

import javax.swing.*;
import java.awt.*;

public class horoscopePanel extends JPanel {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public horoscopePanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(219, 216, 206));
        setMaximumSize(new Dimension((int)screenSize.getWidth(),600));
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
        cardLayout.setMaximumSize(new Dimension(screenSize.width,300));
        CardLayout cl = (CardLayout) cardLayout.getLayout();


        String[] choices = new String[] {"Daily", "Weekly", "Monthly"};
        JComboBox<String> comboBox = new JComboBox<>(choices);
        comboBox.setMaximumSize(new Dimension(200, 30));
        comboBox.setSelectedIndex(0);
        comboBox.addActionListener(_ -> {
            cl.show(cardLayout, (String) comboBox.getSelectedItem());
            dailyHoroscopeContent.setText("");
            repaint(userData.getZodiac().toLowerCase(),dailyHoroscopeContent,weeklyHoroscopeContent, monthlyHoroscopeContent);
        });
        add(comboBox, BorderLayout.NORTH);
        add(cardLayout, BorderLayout.CENTER);


    }

    public void repaint(String zodiacSign, JTextArea daily, JTextArea weekly, JTextArea monthly ) {
        daily.setText(userData.getZodiac() + ": " + GetHoroscope.horoscopeData(zodiacSign.toLowerCase(),"daily"));
        weekly.setText(userData.getZodiac() + ": " + GetHoroscope.horoscopeData(zodiacSign.toLowerCase(),"weekly"));
        monthly.setText(userData.getZodiac() + ": " + GetHoroscope.horoscopeData(zodiacSign.toLowerCase(),"monthly"));
    }

    private JPanel createHoroscopePanel(String title, JTextArea contentArea, String horoscopeType) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(219, 216, 206));

        JLabel label = new JLabel(title);
        label.setFont(AppFonts.bold(36f));

        JPanel labelPanel = new JPanel(new FlowLayout());
        labelPanel.setBackground(new Color(219, 216, 206));
        labelPanel.add(label);

        panel.add(labelPanel);

        contentArea.setText("");
        contentArea.setBackground(new Color(219, 216, 206));
        contentArea.setEditable(false);
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);
        contentArea.setFont(AppFonts.regular(14f));
        contentArea.setText(userData.getZodiac() + ": " + GetHoroscope.horoscopeData(userData.getZodiac().toLowerCase(),horoscopeType));

        panel.add(contentArea);

        return panel;
    }
}
