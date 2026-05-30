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

    public userPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        setBackground(new Color(219, 216, 206));

        // Title section
        JLabel titleLabel = new JLabel("ACCOUNT");
        titleLabel.setFont(AppFonts.bold(60f));
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(219, 216, 206));
        titlePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        titlePanel.add(titleLabel);
        add(titlePanel);

        // Zodiac sign display
        JLabel zodiacSignLabel = new JLabel("Your Zodiac Sign: ");
        zodiacSignLabel.setFont(AppFonts.regular(36f));
        JLabel zodiacSign = new JLabel("");
        zodiacSign.setFont(AppFonts.bold(36f));
        JPanel zodiacPanel = new JPanel(new FlowLayout());
        zodiacPanel.setBackground(new Color(219, 216, 206));
        zodiacPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        zodiacPanel.add(zodiacSignLabel);
        zodiacPanel.add(zodiacSign);
        add(zodiacPanel);

        // Name field
        JLabel nameLabel = new JLabel("Name");
        nameLabel.setFont(AppFonts.regular(36f));
        JTextArea nameArea = new JTextArea(1, 10);
        nameArea.setFont(AppFonts.regular(36f));
        JPanel namePanel = new JPanel(new FlowLayout());
        namePanel.setBackground(new Color(219, 216, 206));
        namePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        namePanel.add(nameLabel);
        namePanel.add(nameArea);
        add(namePanel);

        // Surname field
        JLabel surnameLabel = new JLabel("Surname");
        surnameLabel.setFont(AppFonts.regular(36f));
        JTextArea surnameArea = new JTextArea(1, 10);
        surnameArea.setFont(AppFonts.regular(36f));
        JPanel surnamePanel = new JPanel(new FlowLayout());
        surnamePanel.setBackground(new Color(219, 216, 206));
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
        JPanel datePanel = new JPanel(new FlowLayout());
        datePanel.setBackground(new Color(219, 216, 206));
        datePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        datePanel.add(dateLabel);
        datePanel.add(datePicker);
        add(datePanel);

        // Save button
        JButton saveButton = new JButton("Save changes");
        saveButton.setBackground(new Color(219, 216, 206));
        saveButton.setBorder(BorderFactory.createLineBorder(new Color(55, 50, 28), 4));
        saveButton.setFont(AppFonts.regular(36f));
        saveButton.addActionListener(_ -> {
            name = nameArea.getText();
            surname = surnameArea.getText();
            date = datePicker.getDate().toString();
            zodiac = zodiacUtils.getZodiac(LocalDate.parse(date));
            zodiacSign.setText(zodiac);
            userData.setZodiac(zodiacSign.getText());
        });
        JPanel saveButtonPanel = new JPanel(new FlowLayout());
        saveButtonPanel.setBackground(new Color(219, 216, 206));
        saveButtonPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        saveButtonPanel.add(saveButton);
        add(saveButtonPanel);
    }

}
