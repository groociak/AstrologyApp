package org.app;
import javax.swing.*;
import java.awt.*;


public class appLayout extends JFrame {


    public appLayout() {
        setTitle("Astrology App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 1000);
        setLocationRelativeTo(null);

        //containers=======================================================================================
        JPanel window = new JPanel(new BorderLayout());
        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel dailyPanel = new JPanel(new BorderLayout());
        JPanel readingsPanel = new JPanel(new FlowLayout());
        JPanel compatibilityPanel = new JPanel(new FlowLayout());
        JPanel mePanel = new JPanel(new FlowLayout());

        JPanel cardLayout = new JPanel(new CardLayout());
        cardLayout.add(dailyPanel, "Daily");
        cardLayout.add(readingsPanel, "Readings");
        cardLayout.add(compatibilityPanel, "Compatibility");
        cardLayout.add(mePanel, "Me");
        cardLayout.setBorder(BorderFactory.createEmptyBorder());

        CardLayout cl = (CardLayout) cardLayout.getLayout();
        bottomMenu bottomMenu = new bottomMenu(cl,cardLayout);


        //menu=======================================================================================
        getMenuButton menuButton = new getMenuButton();
        JPanel menuPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10,10));
        menuPanel.setBackground(new Color(219, 216, 206));
        menuPanel.add(menuButton);


        //daily panel=======================================================================================
        dailyPanel.setBorder(BorderFactory.createEmptyBorder());
        JPanel contentDailyPanel = new JPanel();
        contentDailyPanel.setBorder(BorderFactory.createEmptyBorder());
        contentDailyPanel.setLayout(new BoxLayout(contentDailyPanel, BoxLayout.Y_AXIS));
        contentDailyPanel.setBackground(new Color(219, 216, 206));

        contentDailyPanel.add(new JLabel("Daily"));

        JButton test1 = new JButton("Test1");
        test1.setPreferredSize(new Dimension(70, 1400));
        test1.setMaximumSize(new Dimension(70, 1400));
        contentDailyPanel.add(test1);

        //scroll
        JScrollPane scrollPaneDaily = new JScrollPane(contentDailyPanel);
        scrollPaneDaily.setBorder(BorderFactory.createEmptyBorder());
        scrollPaneDaily.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );
        scrollPaneDaily.getVerticalScrollBar().setUI(new scrollbar());
        dailyPanel.add(scrollPaneDaily, BorderLayout.CENTER);

        //readings panel=======================================================================================
        readingsPanel.setBackground(new Color(219, 216, 206));
        readingsPanel.add(new JLabel("Readings"));

        //compatibility panel=======================================================================================
        compatibilityPanel.setBackground(new Color(219, 216, 206));
        compatibilityPanel.add(new JLabel("Compatibility"));

        //me panel=======================================================================================
        mePanel.setBackground(new Color(219, 216, 206));
        mePanel.add(new JLabel("Me"));





        //window & main panel=======================================================================================
        mainPanel.add(cardLayout, BorderLayout.CENTER);
        mainPanel.add(menuPanel, BorderLayout.WEST);
        window.add(bottomMenu,  BorderLayout.SOUTH);
        window.add(mainPanel, BorderLayout.CENTER);

        add(window, BorderLayout.CENTER);
        setVisible(true);
    }

}
