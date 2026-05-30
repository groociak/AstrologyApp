package org.app.panels;

import org.app.AppFonts;
import org.app.functions.GetBirthChart;

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
                rescaleChart();
            }
        });
    }

    /**
     * Rescales the birth chart image to fit the current panel width
     * while maintaining the original aspect ratio.
     */
    private void rescaleChart() {
        if (originalChartImage == null) return;
        int availableWidth = getWidth() - 40; // padding
        if (availableWidth <= 0) return;

        int imgW = originalChartImage.getWidth();
        int imgH = originalChartImage.getHeight();
        // Keep aspect ratio, cap at available width
        int targetW = Math.min(availableWidth, imgW);
        int targetH = (int) ((double) imgH / imgW * targetW);

        if (targetW <= 0 || targetH <= 0) return;

        BufferedImage scaled = new BufferedImage(targetW, targetH, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = scaled.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawImage(originalChartImage, 0, 0, targetW, targetH, null);
        g2d.dispose();
        
        chartLabel.setIcon(new ImageIcon(scaled));
    }

    public static void updateChart() {
        originalChartImage = GetBirthChart.getChartSvg();
        chartLabel.setIcon(new ImageIcon(originalChartImage));
    }
}
