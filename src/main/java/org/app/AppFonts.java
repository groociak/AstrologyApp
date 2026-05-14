package org.app;

import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class AppFonts {
    private static Font baseFont;

    static {
        try{
            baseFont = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(AppFonts.class.getResourceAsStream("/fonts/yoster.ttf")));

        }catch(FontFormatException | IOException e){
            e.printStackTrace();
            baseFont = new Font("Arial", Font.PLAIN, 18);
        }
    }
    public static Font regular(float size){
        return baseFont.deriveFont(Font.PLAIN, size);
    }
    public static Font bold(float size){
        return baseFont.deriveFont(Font.BOLD, size);
    }
    public static Font italic(float size){
        return baseFont.deriveFont(Font.ITALIC, size);
    }
}
