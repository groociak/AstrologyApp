package org.app.panels;

import org.app.AppFonts;
import org.app.RoundedPanel;
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
        setLayout(new BorderLayout(0, 15));
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        // Title section
        JLabel titleLabel = new JLabel("Me");
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

        // Birth chart image — scales proportionally on resize
        originalChartImage = GetBirthChart.getChartSvg();
        chartLabel = new JLabel(new ImageIcon(originalChartImage));
        chartLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel chartPanel = new JPanel(new BorderLayout());
        chartPanel.setOpaque(false);
        chartPanel.add(chartLabel, BorderLayout.CENTER);

        contentBox.add(chartPanel, BorderLayout.CENTER);

        add(contentBox, BorderLayout.CENTER);

        // Re-scale the chart image when the panel is resized
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                svgUtil.rescaleSvg(originalChartImage, chartLabel);
            }
        });
    }

    public static void updateChart() {
        originalChartImage = GetBirthChart.getChartSvg();
        chartLabel.setIcon(new ImageIcon(originalChartImage));
    }
}