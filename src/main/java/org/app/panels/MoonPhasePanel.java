package org.app.panels;

import kong.unirest.core.json.JSONObject;
import org.app.AppFonts;
import org.app.functions.GetMoonPhase;
import org.app.functions.svgUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;

public class MoonPhasePanel extends JPanel {
    public MoonPhasePanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(219, 216, 206));

        //api call
        JSONObject response = GetMoonPhase.moonPhaseData();
        String svgString = response.getString("svg");
        String moonPhase = response.getString("phase_name");
        String moonSign = response.getString("zodiac_sign");
        String interpretation = response.getString("interpretation");

        //Title
        JLabel titleLabel = new JLabel("Moon Phase Today");
        titleLabel.setFont(AppFonts.bold(36f));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(titleLabel);

        //Phase
        JLabel phaseLabel = new JLabel("Moon phase name: "+moonPhase);
        phaseLabel.setFont(AppFonts.regular(14f));
        phaseLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(phaseLabel);

        //Moons current sign
        JLabel moonSignLabel = new JLabel("Moon current sign: " + moonSign);
        moonSignLabel.setFont(AppFonts.regular(14f));
        moonSignLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(moonSignLabel);

        //SVG moon img
        BufferedImage moonImage = svgUtil.svgToImage(svgString, 300,300);
        JLabel moonLabel = new JLabel(new ImageIcon(moonImage));
        moonLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(moonLabel);

        // Re-scale the chart image when the panel is resized
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                svgUtil.rescaleSvg(moonImage,moonLabel);
            }
        });

        //Moon phase meaning and interpretation
        JTextArea moonPhaseMeaningArea = new JTextArea();
        moonPhaseMeaningArea.setText(interpretation);
        moonPhaseMeaningArea.setBackground(new Color(219, 216, 206));
        moonPhaseMeaningArea.setEditable(false);
        moonPhaseMeaningArea.setLineWrap(true);
        moonPhaseMeaningArea.setWrapStyleWord(true);
        moonPhaseMeaningArea.setFont(AppFonts.regular(14f));
        moonPhaseMeaningArea.setMargin(new Insets(8, 12, 8, 12));
        add(moonPhaseMeaningArea);


    }
}
