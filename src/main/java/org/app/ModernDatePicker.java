package org.app;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;

public class ModernDatePicker extends DatePicker {

    private JTextField field;

    public ModernDatePicker() {
        this(createDefaultSettings());
    }

    public ModernDatePicker(DatePickerSettings settings) {
        super(settings);
        applyStyle();
    }

    private static DatePickerSettings createDefaultSettings() {

        DatePickerSettings settings = new DatePickerSettings();

        settings.setAllowEmptyDates(false);
        settings.setLocale(Locale.ENGLISH);
        settings.setFormatForDatesCommonEra("yyyy-MM-dd");

        // Fonts
        settings.setFontValidDate(AppFonts.regular(16f));
        settings.setFontInvalidDate(AppFonts.regular(16f));

        settings.setFontCalendarDateLabels(
                AppFonts.regular(14f)
        );

        settings.setFontCalendarWeekdayLabels(
                AppFonts.bold(14f)
        );

        settings.setFontCalendarWeekNumberLabels(
                AppFonts.regular(14f)
        );

        // ===== KOLORY KALENDARZA  =====

        Color DARK_BG = new Color(18, 22, 52);
        Color MID_BG = new Color(75, 60, 135);
        Color HOVER_BG = new Color(115, 95, 185);
        Color TEXT = new Color(245, 241, 255);
        Color TEXT_DARK = new Color(0, 0, 31);


        settings.setColor(DatePickerSettings.DateArea.CalendarDefaultBackgroundHighlightedDates,
                MID_BG
        );

// cały popup kalendarza
        settings.setColor(
                DatePickerSettings.DateArea.BackgroundOverallCalendarPanel,
                DARK_BG
        );

// normalne dni
        settings.setColor(
                DatePickerSettings.DateArea.CalendarBackgroundNormalDates,
                MID_BG
        );

// zaznaczona data
        settings.setColor(
                DatePickerSettings.DateArea.CalendarBackgroundSelectedDate,
                HOVER_BG
        );

// tekst normalnych dni
        settings.setColor(
                DatePickerSettings.DateArea.CalendarTextNormalDates,
                TEXT
        );



// dni tygodnia (Mon, Tue...)
        settings.setColor(
                DatePickerSettings.DateArea.CalendarTextWeekdays,
                TEXT
        );

// numery tygodni (po lewej)
        settings.setColor(
                DatePickerSettings.DateArea.CalendarTextWeekNumbers,
                TEXT
        );

// input field (tekst w polu)
        settings.setColor(
                DatePickerSettings.DateArea.DatePickerTextValidDate,
                TEXT_DARK
        );

        settings.setColor(
                DatePickerSettings.DateArea.DatePickerTextInvalidDate,
                new Color(255, 120, 120)
        );

        return settings;
    }

    private void applyStyle() {

        setFont(AppFonts.regular(16f));

        field = getComponentDateTextField();

        Dimension size = new Dimension(220, 42);

        setPreferredSize(size);
        setMinimumSize(size);

        field.setPreferredSize(
                new Dimension(180, 42)
        );

        field.setFont(
                AppFonts.regular(16f)
        );

        field.setForeground(
                new Color(250, 246, 255)
        );

        field.setCaretColor(
                new Color(250, 246, 255)
        );

        field.setBackground(
                new Color(75, 60, 135)
        );

        field.setMargin(
                new Insets(8, 12, 8, 12)
        );

        field.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(185, 175, 245),
                                2
                        ),
                        BorderFactory.createEmptyBorder(
                                8, 12, 8, 12
                        )
                )
        );

        JButton calendarButton =
                getComponentToggleCalendarButton();

        calendarButton.setFont(
                AppFonts.regular(14f)
        );

        calendarButton.setBackground(
                new Color(75, 60, 135)
        );

        calendarButton.setForeground(
                new Color(250, 246, 255)
        );

        calendarButton.setBorder(
                BorderFactory.createLineBorder(
                        new Color(185, 175, 245),
                        2
                )
        );

        // wymuszenie fontu po każdej zmianie daty
        addDateChangeListener(e ->
                SwingUtilities.invokeLater(() ->
                        field.setFont(
                                AppFonts.regular(16f)
                        )
                )
        );
    }

}