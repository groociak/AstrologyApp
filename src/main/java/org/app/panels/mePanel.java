package org.app.panels;

import org.app.AppFonts;
import org.app.functions.GetBirthChart;
import org.app.functions.svgUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;

public class mePanel extends JPanel {
    public static JLabel chartLabel;
    private static BufferedImage originalChartImage;

    public mePanel() {
        setBackground(new Color(219, 216, 206));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Title section
        JLabel titleLabel = new JLabel("Me");
        titleLabel.setFont(AppFonts.bold(70f));
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(219, 216, 206));
        titlePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        titlePanel.add(titleLabel);
        add(titlePanel);

        // Birth chart image — scales proportionally on resize
        originalChartImage = GetBirthChart.getChartSvg();
        chartLabel = new JLabel(new ImageIcon(originalChartImage));
        JPanel chartPanel = new JPanel(new BorderLayout());
        chartPanel.setBackground(new Color(219, 216, 206));
        chartPanel.add(chartLabel, BorderLayout.CENTER);
        chartLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(chartPanel);

        // Re-scale the chart image when the panel is resized
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                svgUtil.rescaleSvg(originalChartImage,chartLabel);
            }
        });
    }



    public static void updateChart() {
        originalChartImage = GetBirthChart.getChartSvg();
        chartLabel.setIcon(new ImageIcon(originalChartImage));
    }
}
