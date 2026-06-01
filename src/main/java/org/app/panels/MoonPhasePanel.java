package org.app.panels;

import kong.unirest.core.json.JSONObject;
import org.app.AppFonts;
import org.app.RoundedPanel;
import org.app.functions.GetMoonPhase;
import org.app.functions.svgUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MoonPhasePanel extends JPanel {

    public MoonPhasePanel() {

        setLayout(new BorderLayout());
        setOpaque(false);
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 700));

        JSONObject response = GetMoonPhase.moonPhaseData();

        String svgString = response.getString("svg");
        String moonPhase = response.getString("phase_name");
        String moonSign = response.getString("zodiac_sign");
        String interpretation = response.getString("interpretation");

        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setOpaque(false);
        wrapper.setBorder(BorderFactory.createEmptyBorder(6, 0, 0, 0));

        RoundedPanel panel = new RoundedPanel(
                new BorderLayout(0, 12),
                new Color(18, 22, 52, 180),
                30
        );

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        18,
                        18,
                        18,
                        18
                )
        );

        // ===== TITLE =====

        JLabel titleLabel = new JLabel(
                "Moon Phase Today",
                SwingConstants.CENTER
        );

        titleLabel.setFont(AppFonts.bold(36f));
        titleLabel.setForeground(
                new Color(245, 241, 255)
        );

        // ===== INFO =====

        JPanel infoPanel = new JPanel();
        infoPanel.setOpaque(false);
        infoPanel.setLayout(
                new BoxLayout(
                        infoPanel,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel phaseLabel = new JLabel(
                "Phase: " + moonPhase
        );

        phaseLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        phaseLabel.setForeground(
                new Color(240, 236, 255)
        );

        phaseLabel.setFont(
                AppFonts.regular(16f)
        );

        JLabel signLabel = new JLabel(
                "Moon in: " + moonSign
        );

        signLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        signLabel.setForeground(
                new Color(240, 236, 255)
        );

        signLabel.setFont(
                AppFonts.regular(16f)
        );

        infoPanel.add(phaseLabel);
        infoPanel.add(Box.createVerticalStrut(4));
        infoPanel.add(signLabel);

        // ===== MOON IMAGE =====

        BufferedImage moonImage =
                svgUtil.svgToImage(svgString, 250, 250);

        JLabel moonLabel =
                new JLabel(new ImageIcon(moonImage));

        moonLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        JPanel imagePanel = new JPanel();
        imagePanel.setOpaque(false);
        imagePanel.add(moonLabel);

        // ===== INTERPRETATION =====

        JTextArea interpretationArea =
                new JTextArea();

        interpretationArea.setText(
                interpretation
        );

        interpretationArea.setOpaque(false);
        interpretationArea.setEditable(false);
        interpretationArea.setLineWrap(true);
        interpretationArea.setWrapStyleWord(true);
        interpretationArea.setForeground(
                new Color(240, 236, 255)
        );

        interpretationArea.setFont(
                AppFonts.regular(14f)
        );

        interpretationArea.setMargin(
                new Insets(8, 12, 8, 12)
        );

        JScrollPane scrollPane =
                new JScrollPane(interpretationArea);

        scrollPane.setBorder(
                BorderFactory.createEmptyBorder()
        );

        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        scrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );

        // ===== CENTER CONTENT =====

        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(
                new BoxLayout(
                        centerPanel,
                        BoxLayout.Y_AXIS
                )
        );

        centerPanel.add(infoPanel);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(imagePanel);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(scrollPane);

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(centerPanel, BorderLayout.CENTER);

        wrapper.add(panel, BorderLayout.CENTER);

        add(wrapper, BorderLayout.CENTER);
    }
}