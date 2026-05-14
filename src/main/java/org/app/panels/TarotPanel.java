package org.app.panels;

import org.app.AppFonts;
import org.app.functions.DrawTarot;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class TarotPanel extends JPanel {
    public TarotPanel(JButton drawButton) {
        setLayout(new GridBagLayout());
        setBackground(new Color(219, 216, 206));
        BufferedImage backCard;
        try {
            backCard = ImageIO.read(Objects.requireNonNull(getClass().getResource("/TarotCards/card_back.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Image scaledBackCard = backCard.getScaledInstance(200, 200, Image.SCALE_DEFAULT);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(5, 5, 5, 5);
        //first row - images
        gbc.gridy=0;
        gbc.gridx=0;
        JLabel backCard1Label = new JLabel(new ImageIcon(scaledBackCard));
        add(backCard1Label,gbc);
        gbc.gridx=1;
        JLabel backCard2Label = new JLabel(new ImageIcon(scaledBackCard));
        add(backCard2Label,gbc);
        gbc.gridx=2;
        JLabel backCard3Label = new JLabel(new ImageIcon(scaledBackCard));
        add(backCard3Label,gbc);


        //second row - names
        gbc.gridy=1;
        gbc.gridx=0;
        JLabel Card1 = new JLabel("");
        Card1.setFont(AppFonts.regular(16f));
        add(Card1,gbc);
        gbc.gridx=1;
        JLabel Card2 = new JLabel("");
        Card2.setFont(AppFonts.regular(16f));
        add(Card2,gbc);
        gbc.gridx=2;
        JLabel Card3 = new JLabel("");
        Card3.setFont(AppFonts.regular(16f));
        add(Card3,gbc);

        //third row - descriptions
        gbc.gridy=2;
        gbc.gridx=0;
        JTextArea Card1Meaning = new JTextArea(5,20);
        Card1Meaning.setFont(AppFonts.italic(12f));
        Card1Meaning.setLineWrap(true);
        Card1Meaning.setBackground(new Color(219, 216, 206));
        Card1Meaning.setWrapStyleWord(true);
        Card1Meaning.setEditable(false);
        add(Card1Meaning,gbc);
        gbc.gridx=1;
        JTextArea Card2Meaning = new JTextArea(5,20);
        Card2Meaning.setFont(AppFonts.italic(12f));
        Card2Meaning.setLineWrap(true);
        Card2Meaning.setBackground(new Color(219, 216, 206));
        Card2Meaning.setWrapStyleWord(true);
        Card2Meaning.setEditable(false);
        add(Card2Meaning,gbc);
        gbc.gridx=2;
        JTextArea Card3Meaning = new JTextArea(5,20);
        Card3Meaning.setFont(AppFonts.italic(12f));
        Card3Meaning.setLineWrap(true);
        Card3Meaning.setBackground(new Color(219, 216, 206));
        Card3Meaning.setWrapStyleWord(true);
        Card3Meaning.setEditable(false);
        add(Card3Meaning,gbc);

        drawButton.addActionListener(_ -> {
            {
                int[] revVal = new int[3];
                for (int i = 0; i < revVal.length; i++) {
                    revVal[i]=(int) Math.round(Math.random());
                }
                var card1Data= new DrawTarot();
                var card2Data= new DrawTarot();
                var card3Data= new DrawTarot();
                Card1.setText(String.valueOf(card1Data));
                Card1Meaning.setText(card1Data.getMeaning(revVal[0]));
                Card2.setText(String.valueOf(card2Data));
                Card2Meaning.setText(String.valueOf(card2Data.getMeaning(revVal[1])));
                Card3.setText(String.valueOf(card3Data));
                Card3Meaning.setText(String.valueOf(card3Data.getMeaning(revVal[2])));
            }
        });
    }
}
