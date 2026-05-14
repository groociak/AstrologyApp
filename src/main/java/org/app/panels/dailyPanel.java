package org.app.panels;

import org.app.functions.DrawTarot;
import org.app.functions.scrollbar;
import org.app.AppFonts;

import javax.swing.*;
import java.awt.*;


public class dailyPanel extends JPanel {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public dailyPanel() {
        setLayout(new CardLayout());
        setBorder(BorderFactory.createEmptyBorder());
        JPanel contentDailyPanel = new JPanel();
        contentDailyPanel.setBorder(BorderFactory.createEmptyBorder());
        contentDailyPanel.setLayout(new BoxLayout(contentDailyPanel, BoxLayout.Y_AXIS));
        contentDailyPanel.setBackground(new Color(219, 216, 206));

        JPanel TarotPanel = new JPanel(new BorderLayout());
        TarotPanel.setBackground(new Color(219, 216, 206));

        add(TarotPanel, "Tarot");
        CardLayout cl = (CardLayout) getLayout();




        //contentDailyPanel elements====================================================================

        JLabel titleLabel = new JLabel("Daily");
        titleLabel.setFont(AppFonts.bold(70f));
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(219, 216, 206));
        titlePanel.setMaximumSize(new Dimension((int) screenSize.getWidth(),100));
        titlePanel.add(titleLabel);
        contentDailyPanel.add(titlePanel);


        JButton buttonTarot = new JButton("Daily Tarot draw");
        contentDailyPanel.add(buttonTarot);
        buttonTarot.addActionListener(_ -> cl.show(this, "Tarot"));

        JLabel tarotLabel = new JLabel(String.valueOf(new DrawTarot()));
        contentDailyPanel.add(tarotLabel);


        //TarotPanel Elements===================================================================
        JPanel northTarotPanel = new JPanel(new FlowLayout());
        northTarotPanel.setBackground(new Color(219, 216, 206));
        JButton backButton = new JButton("<--");
        backButton.addActionListener(_ -> cl.show(this, "Daily"));
        JButton drawButton = new JButton("Draw");
        northTarotPanel.add(backButton);
        northTarotPanel.add(drawButton);
        TarotPanel.add(northTarotPanel, BorderLayout.NORTH);

        TarotPanel cardPanel = new TarotPanel(drawButton);
        TarotPanel.add(cardPanel, BorderLayout.CENTER);


        //scroll=================================================================================
        JScrollPane scrollPaneDaily = new JScrollPane(contentDailyPanel);
        scrollPaneDaily.setBorder(BorderFactory.createEmptyBorder());
        scrollPaneDaily.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );
        scrollPaneDaily.getVerticalScrollBar().setUI(new scrollbar());
        add(scrollPaneDaily, "Daily");
        cl.show(this, "Daily");
    }
}
