package org.app.panels;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import org.app.AppFonts;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;

public class userPanel extends JPanel {
    private String name;
    private String surname;
    private String date;

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public userPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder());
        setBackground(new Color(219, 216, 206));

        JLabel titleLabel = new JLabel("ACCOUNT");
        titleLabel.setFont(AppFonts.bold(60f));
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(219, 216, 206));
        titlePanel.setMaximumSize(new Dimension((int) screenSize.getWidth(),100));
        titlePanel.add(titleLabel);
        add(titlePanel);

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setFont(AppFonts.regular(36f));
        JTextArea nameArea = new JTextArea(1,10);
        nameArea.setFont(AppFonts.regular(36f));
        JPanel namePanel = new JPanel(new FlowLayout());
        namePanel.setBackground(new Color(219, 216, 206));
        namePanel.setMaximumSize(new Dimension((int) screenSize.getWidth(),100));
        namePanel.add(nameLabel);
        namePanel.add(nameArea);
        add(namePanel);

        JLabel surnameLabel = new JLabel("Surname");
        surnameLabel.setFont(AppFonts.regular(36f));
        JTextArea surnameArea = new JTextArea(1,10);
        surnameArea.setFont(AppFonts.regular(36f));
        JPanel surnamePanel = new JPanel(new FlowLayout());
        surnamePanel.setBackground(new Color(219, 216, 206));
        surnamePanel.setMaximumSize(new Dimension((int) screenSize.getWidth(),100));
        surnamePanel.add(surnameLabel);
        surnamePanel.add(surnameArea);
        add(surnamePanel);


        DatePickerSettings dateSettings = new DatePickerSettings();
        dateSettings.setLocale(new Locale("eng"));
        dateSettings.setFormatForDatesCommonEra("yyyy-MM-dd");
        dateSettings.setFontValidDate(AppFonts.regular(14f));
        DatePicker datePicker = new DatePicker(dateSettings);
        JLabel dateLabel = new JLabel("Birth date");
        dateLabel.setFont(AppFonts.regular(36f));
        JPanel datePanel = new JPanel(new FlowLayout());
        datePanel.setBackground(new Color(219, 216, 206));
        datePanel.setMaximumSize(new Dimension((int) screenSize.getWidth(),100));
        datePanel.add(dateLabel);
        datePanel.add(datePicker);
        add(datePanel);

        JButton saveButton = new JButton("Save changes");
        saveButton.setBackground(new Color(219, 216, 206));
        saveButton.setBorder(BorderFactory.createLineBorder(new Color(55, 50, 28),4));
        saveButton.setFont(AppFonts.regular(36f));
        saveButton.addActionListener(e -> {
            name=nameArea.getText();
            surname=surnameArea.getText();
            date = datePicker.getDate().toString();
        });
        JPanel saveButtonPanel = new JPanel(new FlowLayout());
        saveButtonPanel.setBackground(new Color(219, 216, 206));
        saveButtonPanel.add(saveButton);
        add(saveButtonPanel);


    }

}
