package at.nager.date.helpers;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Orthodox Easter Sunday Helper
 * Used for testing Orthodox Easter calculations
 */
public class OrthodoxEasterSundayHelper {

    private static final Map<Integer, Integer> FIRST_2_DIGITS_OF_YEAR_MAPPING = new HashMap<>();
    private static final Map<Integer, Integer> SECOND_2_DIGITS_OF_YEAR_MAPPING = new HashMap<>();
    private static final Map<Integer, Integer> FOLLOWING_SUNDAY_MAPPING = new HashMap<>();

    static {
        // Initialize first 2 digits mapping
        FIRST_2_DIGITS_OF_YEAR_MAPPING.put(10, 2);
        FIRST_2_DIGITS_OF_YEAR_MAPPING.put(11, 1);
        FIRST_2_DIGITS_OF_YEAR_MAPPING.put(12, 0);
        FIRST_2_DIGITS_OF_YEAR_MAPPING.put(13, 6);
        FIRST_2_DIGITS_OF_YEAR_MAPPING.put(14, 5);
        FIRST_2_DIGITS_OF_YEAR_MAPPING.put(15, 4);
        FIRST_2_DIGITS_OF_YEAR_MAPPING.put(16, 3);
        FIRST_2_DIGITS_OF_YEAR_MAPPING.put(17, 2);
        FIRST_2_DIGITS_OF_YEAR_MAPPING.put(18, 1);
        FIRST_2_DIGITS_OF_YEAR_MAPPING.put(19, 0);
        FIRST_2_DIGITS_OF_YEAR_MAPPING.put(20, 6);
        FIRST_2_DIGITS_OF_YEAR_MAPPING.put(21, 5);
        FIRST_2_DIGITS_OF_YEAR_MAPPING.put(22, 4);
        FIRST_2_DIGITS_OF_YEAR_MAPPING.put(23, 3);
        FIRST_2_DIGITS_OF_YEAR_MAPPING.put(24, 2);
        FIRST_2_DIGITS_OF_YEAR_MAPPING.put(25, 1);
        FIRST_2_DIGITS_OF_YEAR_MAPPING.put(26, 0);
        FIRST_2_DIGITS_OF_YEAR_MAPPING.put(27, 6);
        FIRST_2_DIGITS_OF_YEAR_MAPPING.put(28, 5);
        FIRST_2_DIGITS_OF_YEAR_MAPPING.put(29, 4);
        FIRST_2_DIGITS_OF_YEAR_MAPPING.put(30, 3);
        FIRST_2_DIGITS_OF_YEAR_MAPPING.put(31, 2);
        FIRST_2_DIGITS_OF_YEAR_MAPPING.put(32, 1);
        FIRST_2_DIGITS_OF_YEAR_MAPPING.put(33, 0);

        // Initialize second 2 digits mapping
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(0, 0);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(1, 1);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(2, 2);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(3, 3);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(4, 5);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(5, 6);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(6, 0);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(7, 1);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(8, 3);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(9, 4);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(10, 5);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(11, 6);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(12, 1);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(13, 2);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(14, 3);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(15, 4);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(16, 6);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(17, 0);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(18, 1);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(19, 2);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(20, 4);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(21, 5);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(22, 6);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(23, 0);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(24, 2);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(25, 3);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(26, 4);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(27, 5);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(28, 0);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(29, 1);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(30, 2);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(31, 3);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(32, 5);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(33, 6);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(34, 0);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(35, 1);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(36, 3);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(37, 4);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(38, 5);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(39, 6);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(40, 1);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(41, 2);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(42, 3);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(43, 4);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(44, 6);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(45, 0);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(46, 1);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(47, 2);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(48, 4);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(49, 5);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(50, 6);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(51, 0);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(52, 2);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(53, 3);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(54, 4);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(55, 5);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(56, 0);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(57, 1);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(58, 2);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(59, 3);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(60, 5);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(61, 6);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(62, 0);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(63, 1);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(64, 3);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(65, 4);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(66, 5);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(67, 6);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(68, 1);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(69, 2);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(70, 3);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(71, 4);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(72, 6);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(73, 0);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(74, 1);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(75, 2);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(76, 4);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(77, 5);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(78, 6);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(79, 0);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(80, 2);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(81, 3);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(82, 4);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(83, 5);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(84, 0);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(85, 1);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(86, 2);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(87, 3);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(88, 5);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(89, 6);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(90, 0);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(91, 1);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(92, 3);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(93, 4);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(94, 5);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(95, 6);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(96, 1);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(97, 2);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(98, 3);
        SECOND_2_DIGITS_OF_YEAR_MAPPING.put(99, 4);

        // Initialize following Sunday mapping
        FOLLOWING_SUNDAY_MAPPING.put(0, 7);
        FOLLOWING_SUNDAY_MAPPING.put(1, 6);
        FOLLOWING_SUNDAY_MAPPING.put(2, 5);
        FOLLOWING_SUNDAY_MAPPING.put(3, 4);
        FOLLOWING_SUNDAY_MAPPING.put(4, 3);
        FOLLOWING_SUNDAY_MAPPING.put(5, 2);
        FOLLOWING_SUNDAY_MAPPING.put(6, 1);
        FOLLOWING_SUNDAY_MAPPING.put(7, 7);
        FOLLOWING_SUNDAY_MAPPING.put(8, 6);
        FOLLOWING_SUNDAY_MAPPING.put(9, 5);
        FOLLOWING_SUNDAY_MAPPING.put(10, 4);
        FOLLOWING_SUNDAY_MAPPING.put(11, 3);
        FOLLOWING_SUNDAY_MAPPING.put(12, 2);
        FOLLOWING_SUNDAY_MAPPING.put(13, 1);
        FOLLOWING_SUNDAY_MAPPING.put(14, 7);
        FOLLOWING_SUNDAY_MAPPING.put(15, 6);
        FOLLOWING_SUNDAY_MAPPING.put(16, 5);
        FOLLOWING_SUNDAY_MAPPING.put(17, 4);
        FOLLOWING_SUNDAY_MAPPING.put(18, 3);
    }

    /**
     * Paschal Full Moon data holder
     */
    private static class PaschalFullMoon {
        final int key;
        final LocalDate date;

        PaschalFullMoon(int key, LocalDate date) {
            this.key = key;
            this.date = date;
        }
    }

    /**
     * Calculate Easter Sunday
     * @see <a href="https://www.assa.org.au/edm#OrthCalculator">ASSA Orthodox Calculator</a>
     * @param year the year
     * @return the date of Orthodox Easter Sunday
     */
    public static LocalDate calculateEasterSunday(int year) {
        double yearDividedBy19 = year / 19.0;
        double decimalPartOfYearDividedBy19 = Math.round((yearDividedBy19 % 1) * 1000) / 1000.0;
        int centuryPartOfYear = year / 100;
        int yearInCentury = year % 100;

        Map<Double, PaschalFullMoon> paschalFullMoonMapping = new HashMap<>();
        paschalFullMoonMapping.put(0.0, new PaschalFullMoon(3, LocalDate.of(year, 4, 5)));
        paschalFullMoonMapping.put(0.053, new PaschalFullMoon(6, LocalDate.of(year, 3, 25)));
        paschalFullMoonMapping.put(0.105, new PaschalFullMoon(4, LocalDate.of(year, 4, 13)));
        paschalFullMoonMapping.put(0.158, new PaschalFullMoon(0, LocalDate.of(year, 4, 2)));
        paschalFullMoonMapping.put(0.211, new PaschalFullMoon(3, LocalDate.of(year, 3, 22)));
        paschalFullMoonMapping.put(0.263, new PaschalFullMoon(1, LocalDate.of(year, 4, 10)));
        paschalFullMoonMapping.put(0.316, new PaschalFullMoon(4, LocalDate.of(year, 3, 30)));
        paschalFullMoonMapping.put(0.368, new PaschalFullMoon(2, LocalDate.of(year, 4, 18)));
        paschalFullMoonMapping.put(0.421, new PaschalFullMoon(5, LocalDate.of(year, 4, 7)));
        paschalFullMoonMapping.put(0.474, new PaschalFullMoon(1, LocalDate.of(year, 3, 27)));
        paschalFullMoonMapping.put(0.526, new PaschalFullMoon(6, LocalDate.of(year, 4, 15)));
        paschalFullMoonMapping.put(0.579, new PaschalFullMoon(2, LocalDate.of(year, 4, 4)));
        paschalFullMoonMapping.put(0.632, new PaschalFullMoon(5, LocalDate.of(year, 3, 24)));
        paschalFullMoonMapping.put(0.684, new PaschalFullMoon(3, LocalDate.of(year, 4, 12)));
        paschalFullMoonMapping.put(0.737, new PaschalFullMoon(6, LocalDate.of(year, 4, 1)));
        paschalFullMoonMapping.put(0.789, new PaschalFullMoon(2, LocalDate.of(year, 3, 21)));
        paschalFullMoonMapping.put(0.842, new PaschalFullMoon(0, LocalDate.of(year, 4, 9)));
        paschalFullMoonMapping.put(0.895, new PaschalFullMoon(3, LocalDate.of(year, 3, 29)));
        paschalFullMoonMapping.put(0.947, new PaschalFullMoon(1, LocalDate.of(year, 4, 17)));

        PaschalFullMoon paschalFullMoon = paschalFullMoonMapping.get(decimalPartOfYearDividedBy19);
        int dayToAdd1 = FIRST_2_DIGITS_OF_YEAR_MAPPING.get(centuryPartOfYear);
        int dayToAdd2 = SECOND_2_DIGITS_OF_YEAR_MAPPING.get(yearInCentury);

        int daysToAddForGregorianCalendar = getDaysToAddForGregorianCalendar(year);

        int followingSunday = FOLLOWING_SUNDAY_MAPPING.get(paschalFullMoon.key + dayToAdd1 + dayToAdd2);

        return paschalFullMoon.date.plusDays(followingSunday).plusDays(daysToAddForGregorianCalendar);
    }

    /**
     * Get the number of days to add to convert Julian calendar date to Gregorian calendar date
     * @param year the year
     * @return the number of days to add
     */
    private static int getDaysToAddForGregorianCalendar(int year) {
        if (year >= 1583 && year <= 1699) {
            return 10;
        } else if (year >= 1700 && year <= 1799) {
            return 11;
        } else if (year >= 1800 && year <= 1899) {
            return 12;
        } else if (year >= 1900 && year <= 2099) {
            return 13;
        } else if (year >= 2100 && year <= 2199) {
            return 14;
        } else if (year >= 2200 && year <= 2299) {
            return 15;
        } else if (year >= 2300 && year <= 2499) {
            return 16;
        } else if (year >= 2500 && year <= 2599) {
            return 17;
        } else if (year >= 2600 && year <= 2699) {
            return 18;
        } else if (year >= 2700 && year <= 2899) {
            return 19;
        } else if (year >= 2900 && year <= 2999) {
            return 20;
        } else if (year >= 3000 && year <= 3099) {
            return 21;
        } else if (year >= 3100 && year <= 3299) {
            return 22;
        } else if (year >= 3300 && year <= 3399) {
            return 23;
        } else {
            return 0;
        }
    }
}
