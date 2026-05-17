package org.app.functions;

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
}
