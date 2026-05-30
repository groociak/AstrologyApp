package org.app.panels;

import org.app.AppFonts;
import org.app.functions.GetBirthChart;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class mePanel extends JPanel {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static JLabel chartLabel;
    public mePanel() {
        setBackground(new Color(219, 216, 206));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLabel titleLabel = new JLabel("Me");
        titleLabel.setFont(AppFonts.bold(70f));
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(219, 216, 206));
        titlePanel.setMaximumSize(new Dimension((int) screenSize.getWidth(),100));
        titlePanel.add(titleLabel);
        add(titlePanel);

        BufferedImage chartImage = GetBirthChart.getChartSvg();
        chartLabel = new JLabel(new ImageIcon(chartImage));
        JPanel chartPanel = new JPanel();
        chartPanel.setBackground(new Color(219, 216, 206));
        chartPanel.setMaximumSize(new Dimension((int) screenSize.getWidth(),900));
        chartPanel.add(chartLabel);
        add(chartPanel);

    }
    public static void updateChart() {
        chartLabel.setIcon(new ImageIcon(GetBirthChart.getChartSvg()));
    }
}
