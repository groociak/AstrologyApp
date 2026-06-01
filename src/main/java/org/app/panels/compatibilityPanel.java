package org.app.panels;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import org.app.*;
import org.app.functions.AstrologyCompatibilityService;
import org.app.functions.zodiacUtils;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.Locale;

public class compatibilityPanel extends JPanel {
    String date1;
    String date2;

    public static JLabel zodiacIncompatibilityResult;
    public static JLabel zodiacCompatibilityResult;
    public static JLabel zodiacCompatibilityResultLabel;

    public compatibilityPanel() {
        setLayout(new BorderLayout(0, 15));
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        // Title section
        JLabel titleLabel = new JLabel("Compatibility");
        titleLabel.setFont(AppFonts.bold(70f));
        titleLabel.setForeground(new Color(245, 241, 255));

        JPanel titlePanel = new RoundedPanel(
                new FlowLayout(FlowLayout.CENTER, 0, 5),
                new Color(18, 22, 52, 180),
                30
        );
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 26, 10, 26));
        titlePanel.add(titleLabel);

        JPanel titleWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titleWrapper.setOpaque(false);
        titleWrapper.add(titlePanel);

        add(titleWrapper, BorderLayout.NORTH);

        // Big transparent content box
        RoundedPanel contentBox = new RoundedPanel(
                new BorderLayout(),
                new Color(18, 22, 52, 180),
                30
        );
        contentBox.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        JPanel contentPanel = new JPanel();
        contentPanel.setOpaque(false);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        ModernDatePicker datePicker1 = new ModernDatePicker();

        JLabel dateLabel1 = new JLabel("1 person birth date: ");
        dateLabel1.setFont(AppFonts.regular(24f));
        dateLabel1.setForeground(new Color(245, 241, 255));

        JPanel datePanel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        datePanel1.setOpaque(false);
        datePanel1.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        datePanel1.add(dateLabel1);
        datePanel1.add(datePicker1);

        contentPanel.add(datePanel1);

        ModernDatePicker datePicker2 = new ModernDatePicker();

        JLabel dateLabel2 = new JLabel("2 person birth date: ");
        dateLabel2.setFont(AppFonts.regular(24f));
        dateLabel2.setForeground(new Color(245, 241, 255));

        JPanel datePanel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        datePanel2.setOpaque(false);
        datePanel2.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        datePanel2.add(dateLabel2);
        datePanel2.add(datePicker2);

        contentPanel.add(datePanel2);

        // Calculate button and result label
        JLabel resultTextLabel = new JLabel("");
        resultTextLabel.setFont(AppFonts.regular(24f));
        resultTextLabel.setForeground(new Color(245, 241, 255));

        JLabel percentLabel = new JLabel("");
        percentLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        percentLabel.setForeground(new Color(245, 241, 255));

        ModernButton calculateButton = new ModernButton("Calculate");
        calculateButton.setFont(AppFonts.regular(32f));
        calculateButton.setPreferredSize(new Dimension(230, 70));

        calculateButton.addActionListener(e -> {
            date1 = datePicker1.getDate().toString();
            date2 = datePicker2.getDate().toString();

            resultTextLabel.setText(
                    "Compatibility result: " +
                            AstrologyCompatibilityService.calculateCompatibility(date1, date2)
            );

            percentLabel.setText("%");
        });

        JPanel calculateButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        calculateButtonPanel.setOpaque(false);
        calculateButtonPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 90));
        calculateButtonPanel.add(calculateButton);

        contentPanel.add(calculateButtonPanel);

        JPanel resultPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        resultPanel.setOpaque(false);
        resultPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));

        resultPanel.add(resultTextLabel);
        resultPanel.add(percentLabel);

        contentPanel.add(resultPanel);

        // Zodiac compatibility section
        JLabel zodiacCompatibilityLabel = new JLabel("Zodiac Compatibility");
        zodiacCompatibilityLabel.setFont(AppFonts.bold(36f));
        zodiacCompatibilityLabel.setForeground(new Color(245, 241, 255));

        JPanel zodiacCompatibilityPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        zodiacCompatibilityPanel.setOpaque(false);
        zodiacCompatibilityPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));
        zodiacCompatibilityPanel.add(zodiacCompatibilityLabel);

        contentPanel.add(zodiacCompatibilityPanel);

        zodiacCompatibilityResultLabel = new JLabel(
                "Zodiac Compatibility Result for " + userPanel.zodiac + ": "
        );
        zodiacCompatibilityResultLabel.setFont(AppFonts.regular(24f));
        zodiacCompatibilityResultLabel.setForeground(new Color(245, 241, 255));

        zodiacCompatibilityResult = new JLabel(
                "Sign is compatible with: " + zodiacUtils.getCompatibleSign()
        );
        zodiacCompatibilityResult.setFont(AppFonts.italic(18f));
        zodiacCompatibilityResult.setForeground(new Color(240, 236, 255));

        zodiacIncompatibilityResult = new JLabel(
                "Sign is incompatible with: " + zodiacUtils.getIncompatibleSign()
        );
        zodiacIncompatibilityResult.setFont(AppFonts.italic(18f));
        zodiacIncompatibilityResult.setForeground(new Color(240, 236, 255));

        JPanel zodiacCompatibilityResultPanel = new JPanel();
        zodiacCompatibilityResultPanel.setLayout(new BoxLayout(zodiacCompatibilityResultPanel, BoxLayout.Y_AXIS));
        zodiacCompatibilityResultPanel.setOpaque(false);
        zodiacCompatibilityResultPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));

        zodiacCompatibilityResultLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        zodiacCompatibilityResult.setAlignmentX(Component.CENTER_ALIGNMENT);
        zodiacIncompatibilityResult.setAlignmentX(Component.CENTER_ALIGNMENT);

        zodiacCompatibilityResultPanel.add(zodiacCompatibilityResultLabel);
        zodiacCompatibilityResultPanel.add(Box.createVerticalStrut(5));
        zodiacCompatibilityResultPanel.add(zodiacCompatibilityResult);
        zodiacCompatibilityResultPanel.add(Box.createVerticalStrut(5));
        zodiacCompatibilityResultPanel.add(zodiacIncompatibilityResult);

        contentPanel.add(zodiacCompatibilityResultPanel);

        contentBox.add(contentPanel, BorderLayout.NORTH);

        add(contentBox, BorderLayout.CENTER);
    }


    public static void updateComp() {
        zodiacCompatibilityResultLabel.setText(
                "Zodiac Compatibility Result for " + userPanel.zodiac + ": "
        );

        zodiacCompatibilityResult.setText(
                "Sign is compatible with: " + zodiacUtils.getCompatibleSign()
        );

        zodiacIncompatibilityResult.setText(
                "Sign is incompatible with: " + zodiacUtils.getIncompatibleSign()
        );
    }
}