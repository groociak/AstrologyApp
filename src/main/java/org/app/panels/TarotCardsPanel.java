package org.app.panels;

import org.app.AppFonts;
import org.app.functions.DrawTarot;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class TarotCardsPanel extends JPanel {
    private final JLabel backCard1Label;
    private final JLabel backCard2Label;
    private final JLabel backCard3Label;
    private BufferedImage currentCard1Image;
    private BufferedImage currentCard2Image;
    private BufferedImage currentCard3Image;

    public TarotCardsPanel(JButton drawButton) {
        setLayout(new GridBagLayout());
        setBackground(new Color(219, 216, 206));
        BufferedImage backCard;
        try {
            backCard = ImageIO.read(Objects.requireNonNull(getClass().getResource("/TarotCards/card_back.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        // Initial card size — will be recalculated on resize
        int cardSize = 200;
        Image scaledBackCard = backCard.getScaledInstance(cardSize, cardSize, Image.SCALE_DEFAULT);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.weightx = 1.0;
        gbc.weighty = 0;

        // First row — card images
        gbc.gridy = 0;
        gbc.gridx = 0;
        backCard1Label = new JLabel(new ImageIcon(scaledBackCard));
        add(backCard1Label, gbc);
        gbc.gridx = 1;
        backCard2Label = new JLabel(new ImageIcon(scaledBackCard));
        add(backCard2Label, gbc);
        gbc.gridx = 2;
        backCard3Label = new JLabel(new ImageIcon(scaledBackCard));
        add(backCard3Label, gbc);

        // Initialize card images to the back card
        currentCard1Image = backCard;
        currentCard2Image = backCard;
        currentCard3Image = backCard;

        // Second row — card names
        gbc.gridy = 1;
        gbc.gridx = 0;
        JLabel Card1 = new JLabel("");
        Card1.setFont(AppFonts.regular(16f));
        add(Card1, gbc);
        gbc.gridx = 1;
        JLabel Card2 = new JLabel("");
        Card2.setFont(AppFonts.regular(16f));
        add(Card2, gbc);
        gbc.gridx = 2;
        JLabel Card3 = new JLabel("");
        Card3.setFont(AppFonts.regular(16f));
        add(Card3, gbc);

        // Third row — card meanings (text areas with proportional columns)
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        JTextArea Card1Meaning = createMeaningArea();
        add(Card1Meaning, gbc);
        gbc.gridx = 1;
        JTextArea Card2Meaning = createMeaningArea();
        add(Card2Meaning, gbc);
        gbc.gridx = 2;
        JTextArea Card3Meaning = createMeaningArea();
        add(Card3Meaning, gbc);

        // Draw button action — load card images and display meanings
        drawButton.addActionListener(_ -> {
            {
                int[] revVal = new int[3];
                for (int i = 0; i < revVal.length; i++) {
                    revVal[i] = (int) Math.round(Math.random());
                }
                var card1Data = new DrawTarot();
                var card2Data = new DrawTarot();
                var card3Data = new DrawTarot();

                currentCard1Image = loadRawImage(card1Data.getImagePath());
                currentCard2Image = loadRawImage(card2Data.getImagePath());
                currentCard3Image = loadRawImage(card3Data.getImagePath());

                rescaleCards();

                Card1.setText(String.valueOf(card1Data));
                Card1Meaning.setText(card1Data.getMeaning(revVal[0]));

                Card2.setText(String.valueOf(card2Data));
                Card2Meaning.setText(String.valueOf(card2Data.getMeaning(revVal[1])));

                Card3.setText(String.valueOf(card3Data));
                Card3Meaning.setText(String.valueOf(card3Data.getMeaning(revVal[2])));
            }
        });

        // Re-scale card images when the panel is resized
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                rescaleCards();
            }
        });
    }

    /**
     * Recalculates and applies the card image size based on current panel width.
     * Each card gets roughly 1/3 of the available width minus padding.
     */
    private void rescaleCards() {
        int panelWidth = getWidth();
        if (panelWidth <= 0) return;

        int cardSize = Math.max(80, (panelWidth - 60) / 3);

        backCard1Label.setIcon(scaleImage(currentCard1Image, cardSize));
        backCard2Label.setIcon(scaleImage(currentCard2Image, cardSize));
        backCard3Label.setIcon(scaleImage(currentCard3Image, cardSize));

        revalidate();
    }

    /**
     * Scales a BufferedImage to the given square size.
     */
    private ImageIcon scaleImage(BufferedImage img, int size) {
        if (img == null || size <= 0) return null;
        BufferedImage scaled = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = scaled.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.drawImage(img, 0, 0, size, size, null);
        g2d.dispose();
        return new ImageIcon(scaled);
    }

    /**
     * Creates a consistently styled JTextArea for card meaning descriptions.
     */
    private JTextArea createMeaningArea() {
        JTextArea area = new JTextArea(5, 15);
        area.setFont(AppFonts.italic(12f));
        area.setLineWrap(true);
        area.setBackground(new Color(219, 216, 206));
        area.setWrapStyleWord(true);
        area.setEditable(false);
        return area;
    }

    /**
     * Loads a raw BufferedImage from a classpath resource path.
     */
    private BufferedImage loadRawImage(String path) {
        try {
            return ImageIO.read(Objects.requireNonNull(getClass().getResource(path)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Loads and scales an image to a fixed size (legacy helper, kept for compatibility).
     */
    public ImageIcon loadImage(String path) {
        try {
            BufferedImage img = ImageIO.read(Objects.requireNonNull(getClass().getResource(path)));
            return new ImageIcon(img.getScaledInstance(200, 200, Image.SCALE_SMOOTH));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
