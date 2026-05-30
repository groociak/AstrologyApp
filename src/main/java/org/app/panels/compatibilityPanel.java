package org.app.panels;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import org.app.AppFonts;
import org.app.functions.AstrologyCompatibilityService;
import org.app.functions.zodiacUtils;
import org.app.userData;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.Locale;

public class compatibilityPanel extends JPanel {
    String date1;
    String date2;
    public static JLabel zodiacIncompatibilityResult, zodiacCompatibilityResult, zodiacCompatibilityResultLabel;

    public compatibilityPanel() {
        setBackground(new Color(219, 216, 206));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Title section
        JLabel titleLabel = new JLabel("Compatibility");
        titleLabel.setFont(AppFonts.bold(70f));
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(219, 216, 206));
        titlePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        titlePanel.add(titleLabel);
        add(titlePanel);

        // First person date picker
        DatePickerSettings dateSettings1 = new DatePickerSettings();
        dateSettings1.setAllowEmptyDates(false);
        dateSettings1.setLocale(new Locale("eng"));
        dateSettings1.setFormatForDatesCommonEra("yyyy-MM-dd");
        dateSettings1.setFontValidDate(AppFonts.regular(14f));

        // Second person date picker
        DatePickerSettings dateSettings2 = new DatePickerSettings();
        dateSettings2.setAllowEmptyDates(false);
        dateSettings2.setLocale(new Locale("eng"));
        dateSettings2.setFormatForDatesCommonEra("yyyy-MM-dd");
        dateSettings2.setFontValidDate(AppFonts.regular(14f));

        DatePicker datePicker1 = new DatePicker(dateSettings1);
        JLabel dateLabel1 = new JLabel("1 person birth date: ");
        dateLabel1.setFont(AppFonts.regular(24f));
        JPanel datePanel1 = new JPanel(new FlowLayout());
        datePanel1.setBackground(new Color(219, 216, 206));
        datePanel1.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        datePanel1.add(dateLabel1);
        datePanel1.add(datePicker1);
        add(datePanel1);

        DatePicker datePicker2 = new DatePicker(dateSettings2);
        JLabel dateLabel2 = new JLabel("2 person birth date: ");
        dateLabel2.setFont(AppFonts.regular(24f));
        JPanel datePanel2 = new JPanel(new FlowLayout());
        datePanel2.setBackground(new Color(219, 216, 206));
        datePanel2.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        datePanel2.add(dateLabel2);
        datePanel2.add(datePicker2);
        add(datePanel2);

        // Calculate button and result label
        JLabel resultLabel = new JLabel("");
        resultLabel.setFont(AppFonts.regular(24f));
        JButton calculateButton = new JButton("Calculate");
        calculateButton.setBackground(new Color(219, 216, 206));
        calculateButton.setBorder(BorderFactory.createLineBorder(new Color(55, 50, 28), 4));
        calculateButton.setFont(AppFonts.regular(36f));
        calculateButton.addActionListener(_ -> {
            date1 = datePicker1.getDate().toString();
            date2 = datePicker2.getDate().toString();
            resultLabel.setText("Compatibility result: " + AstrologyCompatibilityService.calculateCompatibility(date1, date2) + "%");
        });
        JPanel calculateButtonPanel = new JPanel(new FlowLayout());
        calculateButtonPanel.setBackground(new Color(219, 216, 206));
        calculateButtonPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        calculateButtonPanel.add(calculateButton);
        add(calculateButtonPanel);

        JPanel resultPanel = new JPanel(new FlowLayout());
        resultPanel.setBackground(new Color(219, 216, 206));
        resultPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        resultPanel.add(resultLabel);
        add(resultPanel);

        // Zodiac compatibility section
        JLabel zodiacCompatibilityLabel = new JLabel("Zodiac Compatibility");
        zodiacCompatibilityLabel.setFont(AppFonts.bold(36f));
        JPanel zodiacCompatibilityPanel = new JPanel(new FlowLayout());
        zodiacCompatibilityPanel.setBackground(new Color(219, 216, 206));
        zodiacCompatibilityPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        zodiacCompatibilityPanel.add(zodiacCompatibilityLabel);
        add(zodiacCompatibilityPanel);

        zodiacCompatibilityResultLabel = new JLabel("Zodiac Compatibility Result for " + userPanel.zodiac + ": ");
        zodiacCompatibilityResultLabel.setFont(AppFonts.regular(24f));
        zodiacCompatibilityResult = new JLabel("Sign is compatible with: " + zodiacUtils.getCompatibleSign());
        zodiacCompatibilityResult.setFont(AppFonts.italic(18f));
        zodiacIncompatibilityResult = new JLabel("Sign is incompatible with: " + zodiacUtils.getIncompatibleSign());
        zodiacIncompatibilityResult.setFont(AppFonts.italic(18f));

        JPanel zodiacCompatibilityResultPanel = new JPanel();
        zodiacCompatibilityResultPanel.setLayout(new BoxLayout(zodiacCompatibilityResultPanel, BoxLayout.Y_AXIS));
        zodiacCompatibilityResultPanel.setBackground(new Color(219, 216, 206));
        zodiacCompatibilityResultPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));

        // Center-align zodiac result labels
        zodiacCompatibilityResultLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        zodiacCompatibilityResult.setAlignmentX(Component.CENTER_ALIGNMENT);
        zodiacIncompatibilityResult.setAlignmentX(Component.CENTER_ALIGNMENT);

        zodiacCompatibilityResultPanel.add(zodiacCompatibilityResultLabel);
        zodiacCompatibilityResultPanel.add(Box.createVerticalStrut(5));
        zodiacCompatibilityResultPanel.add(zodiacCompatibilityResult);
        zodiacCompatibilityResultPanel.add(Box.createVerticalStrut(5));
        zodiacCompatibilityResultPanel.add(zodiacIncompatibilityResult);
        add(zodiacCompatibilityResultPanel);
    }

    public static void updateComp() {
        zodiacCompatibilityResultLabel.setText("Zodiac Compatibility Result for " + userPanel.zodiac + ": ");
        zodiacIncompatibilityResult.setText("Sign is compatible with: " + zodiacUtils.getCompatibleSign());
        zodiacIncompatibilityResult.setText("Sign is incompatible with: " + zodiacUtils.getIncompatibleSign());
    }
}
