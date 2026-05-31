package org.app.functions;

import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.apache.commons.io.output.ByteArrayOutputStream;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;


public class svgUtil {
    public static BufferedImage svgToImage(String svg, int width, int height) {

        try {
            //usuwanie niewspieranych przez batika elementow
            svg = svg.replaceAll("@import url\\(.*?\\);", "");
            svg = svg.replaceAll("<feDropShadow[^>]*/>", "");
            //usuwanie tla
            svg = svg.replace("<rect class=\"chart-bg\" width=\"500\" height=\"500\"/>", "");
            //zmiana zewnetrznego kola
            svg = svg.replace(
                    ".zodiac-band   { fill: #000000; }",
                    ".zodiac-band   { fill: #5E3B8C; }"
            );

            PNGTranscoder transcoder = new PNGTranscoder();

            transcoder.addTranscodingHint(
                    PNGTranscoder.KEY_WIDTH,
                    (float) width
            );

            transcoder.addTranscodingHint(
                    PNGTranscoder.KEY_HEIGHT,
                    (float) height
            );

            TranscoderInput input = new TranscoderInput(
                    new ByteArrayInputStream(svg.getBytes())
            );

            ByteArrayOutputStream outputStream =
                    new ByteArrayOutputStream();

            TranscoderOutput output =
                    new TranscoderOutput(outputStream);

            transcoder.transcode(input, output);

            outputStream.flush();

            return ImageIO.read(
                    new ByteArrayInputStream(outputStream.toByteArray())
            );

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Rescales the svg image to fit the current panel width
     * while maintaining the original aspect ratio.
     * Moved from mePanel
     */
    public static void rescaleSvg(BufferedImage image, JLabel label) {
        if (image == null) return;
        int availableWidth = image.getWidth() - 40; // padding
        if (availableWidth <= 0) return;

        int imgW = image.getWidth();
        int imgH = image.getHeight();
        // Keep aspect ratio, cap at available width
        int targetW = Math.min(availableWidth, imgW);
        int targetH = (int) ((double) imgH / imgW * targetW);

        if (targetW <= 0 || targetH <= 0) return;

        BufferedImage scaled = new BufferedImage(targetW, targetH, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = scaled.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawImage(image, 0, 0, targetW, targetH, null);
        g2d.dispose();

        label.setIcon(new ImageIcon(scaled));
    }
}
