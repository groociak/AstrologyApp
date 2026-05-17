package org.app;

import org.app.functions.zodiacUtils;

import java.time.LocalDate;

public class userData {
    private static String zodiac =zodiacUtils.getZodiac(LocalDate.now());

    public static String getZodiac() {
        return zodiac;
    }
    public static void setZodiac(String zodiac) {
        userData.zodiac = zodiac;
    }
}
