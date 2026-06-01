package org.app.panels;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import org.app.AppFonts;
import org.app.functions.zodiacUtils;
import org.app.userData;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.Locale;

public class userPanel extends JPanel {
    public static String name;
    public static String surname;
    public static String date = LocalDate.now().toString();
    public static String zodiac = zodiacUtils.getZodiac(LocalDate.now());

    private final Color PANEL_BACKGROUND = new Color(120, 105, 185);
    private final Color BUTTON_COLOR = new Color(92, 75, 150);
    private final Color TEXT_COLOR = new Color(250, 246, 255);
    private final Color BORDER_COLOR = new Color(170, 160, 230);

    public userPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        setOpaque(true);
        setBackground(PANEL_BACKGROUND);

        // Title section
        JLabel titleLabel = new JLabel("ACCOUNT");
        titleLabel.setFont(AppFonts.bold(60f));
        titleLabel.setForeground(TEXT_COLOR);

        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(true);
        titlePanel.setBackground(PANEL_BACKGROUND);
        titlePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        titlePanel.add(titleLabel);
        add(titlePanel);

        // Zodiac sign display
        JLabel zodiacSignLabel = new JLabel("Your Zodiac Sign: ");
        zodiacSignLabel.setFont(AppFonts.regular(36f));
        zodiacSignLabel.setForeground(TEXT_COLOR);

        JLabel zodiacSign = new JLabel("");
        zodiacSign.setFont(AppFonts.bold(36f));
        zodiacSign.setForeground(TEXT_COLOR);

        JPanel zodiacPanel = new JPanel(new FlowLayout());
        zodiacPanel.setOpaque(true);
        zodiacPanel.setBackground(PANEL_BACKGROUND);
        zodiacPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        zodiacPanel.add(zodiacSignLabel);
        zodiacPanel.add(zodiacSign);
        add(zodiacPanel);

        // Name field
        JLabel nameLabel = new JLabel("Name");
        nameLabel.setFont(AppFonts.regular(36f));
        nameLabel.setForeground(TEXT_COLOR);

        JTextArea nameArea = new JTextArea(1, 10);
        nameArea.setFont(AppFonts.regular(36f));
        styleTextArea(nameArea);

        JPanel namePanel = new JPanel(new FlowLayout());
        namePanel.setOpaque(true);
        namePanel.setBackground(PANEL_BACKGROUND);
        namePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        namePanel.add(nameLabel);
        namePanel.add(nameArea);
        add(namePanel);

        // Surname field
        JLabel surnameLabel = new JLabel("Surname");
        surnameLabel.setFont(AppFonts.regular(36f));
        surnameLabel.setForeground(TEXT_COLOR);

        JTextArea surnameArea = new JTextArea(1, 10);
        surnameArea.setFont(AppFonts.regular(36f));
        styleTextArea(surnameArea);

        JPanel surnamePanel = new JPanel(new FlowLayout());
        surnamePanel.setOpaque(true);
        surnamePanel.setBackground(PANEL_BACKGROUND);
        surnamePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        surnamePanel.add(surnameLabel);
        surnamePanel.add(surnameArea);
        add(surnamePanel);

        // Birth date picker
        DatePickerSettings dateSettings = new DatePickerSettings();
        dateSettings.setAllowEmptyDates(false);
        dateSettings.setLocale(new Locale("eng"));
        dateSettings.setFormatForDatesCommonEra("yyyy-MM-dd");
        dateSettings.setFontValidDate(AppFonts.regular(14f));

        DatePicker datePicker = new DatePicker(dateSettings);

        JLabel dateLabel = new JLabel("Birth date");
        dateLabel.setFont(AppFonts.regular(36f));
        dateLabel.setForeground(TEXT_COLOR);

        JPanel datePanel = new JPanel(new FlowLayout());
        datePanel.setOpaque(true);
        datePanel.setBackground(PANEL_BACKGROUND);
        datePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        datePanel.add(dateLabel);
        datePanel.add(datePicker);
        add(datePanel);

        // Save button
        JButton saveButton = new JButton("Save changes");
        styleButton(saveButton);
        saveButton.setFont(AppFonts.regular(36f));

        saveButton.addActionListener(e -> {
            name = nameArea.getText();
            surname = surnameArea.getText();
            date = datePicker.getDate().toString();
            zodiac = zodiacUtils.getZodiac(LocalDate.parse(date));
            zodiacSign.setText(zodiac);
            userData.setZodiac(zodiacSign.getText());
        });

        JPanel saveButtonPanel = new JPanel(new FlowLayout());
        saveButtonPanel.setOpaque(true);
        saveButtonPanel.setBackground(PANEL_BACKGROUND);
        saveButtonPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 90));
        saveButtonPanel.add(saveButton);
        add(saveButtonPanel);
    }

    private void styleButton(JButton button) {
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(true);
        button.setOpaque(true);

        button.setBackground(BUTTON_COLOR);
        button.setForeground(TEXT_COLOR);

        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER_COLOR, 1, true),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
    }

    private void styleTextArea(JTextArea area) {
        area.setBackground(new Color(250, 246, 255));
        area.setForeground(new Color(25, 22, 45));
        area.setCaretColor(new Color(25, 22, 45));

        area.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER_COLOR, 1, true),
                BorderFactory.createEmptyBorder(4, 8, 4, 8)
        ));
    }
}