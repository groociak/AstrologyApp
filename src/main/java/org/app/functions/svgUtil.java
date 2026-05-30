package org.app.functions;

import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.apache.commons.io.output.ByteArrayOutputStream;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.StringReader;

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
}
