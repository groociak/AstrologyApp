package org.app.functions;

import org.app.panels.userPanel;

import java.time.LocalDate;

public class zodiacUtils {
    public static String getZodiac(LocalDate date) {

        int day = date.getDayOfMonth();
        int month = date.getMonthValue();

        return switch (month) {

            case 1 -> (day >= 20) ? "Aquarius" : "Capricorn";

            case 2 -> (day >= 19) ? "Pisces" : "Aquarius";

            case 3 -> (day >= 21) ? "Aries" : "Pisces";

            case 4 -> (day >= 20) ? "Taurus" : "Aries";

            case 5 -> (day >= 21) ? "Gemini" : "Taurus";

            case 6 -> (day >= 21) ? "Cancer" : "Gemini";

            case 7 -> (day >= 23) ? "Leo" : "Cancer";

            case 8 -> (day >= 23) ? "Virgo" : "Leo";

            case 9 -> (day >= 23) ? "Libra" : "Virgo";

            case 10 -> (day >= 23) ? "Scorpio" : "Libra";

            case 11 -> (day >= 22) ? "Sagittarius" : "Scorpio";

            case 12 -> (day >= 22) ? "Capricorn" : "Sagittarius";

            default -> throw new IllegalStateException("Unexpected value");
        };
    }

    public static  String getCompatibleSign(){
        return switch (userPanel.zodiac.toLowerCase()){
            case "aquarius" -> "Gemini, Aries and Sagittarius";
            case "pisces" -> "Cancer, Scorpio and Libra";
            case "aries" -> "Leo, Aries and Gemini";
            case "taurus" -> "Virgo, Cancer and Capricorn";
            case "gemini" -> "Aquarius, Libra and Sagittarius";
            case "cancer" -> "Scorpio, Taurus and Pisces";
            case "leo" -> "Sagittarius, Gemini and Aries";
            case "virgo" -> "Taurus, Capricorn and Cancer";
            case "libra" -> "Gemini, Pisces and Aquarius";
            case "scorpio" -> "Capricorn, Cancer and Pisces";
            case "sagittarius" -> "Aries, Leo and Libra";
            case "capricorn" -> "Virgo, Taurus and Scorpio";
            default -> "";
        };
    }
    public static  String getIncompatibleSign(){
        return switch (userPanel.zodiac.toLowerCase()){
            case "aquarius" -> "Taurus, Cancer";
            case "pisces" -> "Gemini, Libra";
            case "aries" -> "Virgo, Capricorn";
            case "taurus", "capricorn" -> "Aries, Gemini";
            case "gemini", "leo" -> "Capricorn, Pisces";
            case "cancer" -> "Aries, Libra";
            case "virgo" -> "Aries, Aquarius";
            case "libra" -> "Capricorn, Virgo";
            case "scorpio" -> "Leo, Gemini";
            case "sagittarius" -> "Taurus, Virgo";
            default -> "";
        };
    }
}
