package at.nager.date.helpers;

import at.nager.date.models.Month;
import at.nager.date.models.Occurrence;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;

/**
 * Date Helper
 * <p>
 * Provides static utility methods for date calculations commonly needed by holiday providers.
 * All methods operate on LocalDate and support finding specific weekdays within months.
 * </p>
 */
public class DateHelper {

    /**
     * Private constructor to prevent instantiation
     */
    private DateHelper() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Find the latest occurrence of a weekday in a month (e.g., last Monday of May)
     *
     * @param year the year
     * @param month the month enum
     * @param day the day of the week to find
     * @return the date of the last occurrence of the specified day
     */
    public static LocalDate findLastDay(int year, Month month, DayOfWeek day) {
        return findLastDay(year, month.getValue(), day);
    }

    /**
     * Find the latest occurrence of a weekday in a month (e.g., last Monday of May)
     *
     * @param year the year
     * @param month the month (1-12)
     * @param day the day of the week to find
     * @return the date of the last occurrence of the specified day
     */
    public static LocalDate findLastDay(int year, int month, DayOfWeek day) {
        LocalDate resultedDay = findDay(year, month, day, 5);
        if (resultedDay == null) {
            resultedDay = findDay(year, month, day, 4);
        }
        return resultedDay;
    }

    /**
     * Find the next occurrence of a weekday from a specific date
     *
     * @param year the year
     * @param month the month enum
     * @param day the day of the month
     * @param dayOfWeek the day of the week to find
     * @return the date of the next occurrence of the specified day
     */
    public static LocalDate findDay(int year, Month month, int day, DayOfWeek dayOfWeek) {
        return findDay(year, month.getValue(), day, dayOfWeek);
    }

    /**
     * Find the next occurrence of a weekday from a specific date
     *
     * @param year the year
     * @param month the month (1-12)
     * @param day the day of the month
     * @param dayOfWeek the day of the week to find
     * @return the date of the next occurrence of the specified day
     */
    public static LocalDate findDay(int year, int month, int day, DayOfWeek dayOfWeek) {
        return findDay(LocalDate.of(year, month, day), dayOfWeek);
    }

    /**
     * Find the next occurrence of a weekday from a specific date
     *
     * @param date the starting date
     * @param dayOfWeek the day of the week to find
     * @return the date of the next occurrence of the specified day (including the start date if it matches)
     */
    public static LocalDate findDay(LocalDate date, DayOfWeek dayOfWeek) {
        int daysNeeded = dayOfWeek.getValue() - date.getDayOfWeek().getValue();

        if (dayOfWeek.getValue() >= date.getDayOfWeek().getValue()) {
            return date.plusDays(daysNeeded);
        }

        return date.plusDays(daysNeeded + 7);
    }

    /**
     * Find the first occurrence of a weekday between two dates (inclusive)
     *
     * @param yearStart the start year
     * @param monthStart the start month (1-12)
     * @param dayStart the start day
     * @param yearEnd the end year
     * @param monthEnd the end month (1-12)
     * @param dayEnd the end day
     * @param dayOfWeek the day of the week to find
     * @return the date of the first occurrence of the specified day, or null if not found
     */
    public static LocalDate findDayBetween(
            int yearStart,
            int monthStart,
            int dayStart,
            int yearEnd,
            int monthEnd,
            int dayEnd,
            DayOfWeek dayOfWeek) {

        LocalDate startDay = LocalDate.of(yearStart, monthStart, dayStart);
        LocalDate endDay = LocalDate.of(yearEnd, monthEnd, dayEnd);
        long days = java.time.temporal.ChronoUnit.DAYS.between(startDay, endDay);

        for (long i = 0; i <= days; i++) {
            LocalDate specificDayDate = startDay.plusDays(i);
            if (specificDayDate.getDayOfWeek() == dayOfWeek) {
                return specificDayDate;
            }
        }

        if (startDay.getDayOfWeek() == dayOfWeek) {
            return startDay;
        }

        return null;
    }

    /**
     * Find the first occurrence of a weekday between two dates (inclusive)
     *
     * @param startDate the start date
     * @param endDate the end date
     * @param dayOfWeek the day of the week to find
     * @return the date of the first occurrence of the specified day, or null if not found
     */
    public static LocalDate findDayBetween(LocalDate startDate, LocalDate endDate, DayOfWeek dayOfWeek) {
        return findDayBetween(
                startDate.getYear(), startDate.getMonthValue(), startDate.getDayOfMonth(),
                endDate.getYear(), endDate.getMonthValue(), endDate.getDayOfMonth(),
                dayOfWeek
        );
    }

    /**
     * Find the most recent occurrence of a weekday before a specific date
     *
     * @param year the year
     * @param month the month enum
     * @param day the day of the month
     * @param dayOfWeek the day of the week to find
     * @return the date of the most recent occurrence of the specified day before the given date
     */
    public static LocalDate findDayBefore(int year, Month month, int day, DayOfWeek dayOfWeek) {
        return findDayBefore(year, month.getValue(), day, dayOfWeek);
    }

    /**
     * Find the most recent occurrence of a weekday before a specific date
     *
     * @param year the year
     * @param month the month (1-12)
     * @param day the day of the month
     * @param dayOfWeek the day of the week to find
     * @return the date of the most recent occurrence of the specified day before the given date
     */
    public static LocalDate findDayBefore(int year, int month, int day, DayOfWeek dayOfWeek) {
        LocalDate calculationDay = LocalDate.of(year, month, day);

        if (dayOfWeek.getValue() < calculationDay.getDayOfWeek().getValue()) {
            int daysSubtract = calculationDay.getDayOfWeek().getValue() - dayOfWeek.getValue();
            return calculationDay.minusDays(daysSubtract);
        } else {
            int daysSubtract = dayOfWeek.getValue() - calculationDay.getDayOfWeek().getValue();
            return calculationDay.minusDays(7 - daysSubtract);
        }
    }

    /**
     * Find the most recent occurrence of a weekday before a specific date
     *
     * @param date the date where the search starts
     * @param dayOfWeek the day of the week to find
     * @return the date of the most recent occurrence of the specified day before the given date
     */
    public static LocalDate findDayBefore(LocalDate date, DayOfWeek dayOfWeek) {
        return findDayBefore(date.getYear(), date.getMonthValue(), date.getDayOfMonth(), dayOfWeek);
    }

    /**
     * Finds the date of a specific occurrence of a day within a month (e.g., the 3rd Monday)
     *
     * @param year the year
     * @param month the month (1-12)
     * @param day the day of the week
     * @param occurrence the occurrence number (1-5)
     * @return the date of the specified occurrence, or null if the occurrence doesn't exist in that month
     * @throws IllegalArgumentException if occurrence is 0 or greater than 5
     */
    public static LocalDate findDay(int year, int month, DayOfWeek day, int occurrence) {
        if (occurrence == 0 || occurrence > 5) {
            throw new IllegalArgumentException("Occurrence is invalid: " + occurrence);
        }

        LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);

        // Subtract first day of the month with the required day of the week
        int daysNeeded = day.getValue() - firstDayOfMonth.getDayOfWeek().getValue();

        // If it is less than zero we need to get the next week day (add 7 days)
        if (daysNeeded < 0) {
            daysNeeded += 7;
        }

        // DayOfWeek is 1-indexed; multiply by the occurrence to get the day
        int resultedDay = (daysNeeded + 1) + (7 * (occurrence - 1));

        if (resultedDay > YearMonth.of(year, month).lengthOfMonth()) {
            return null;
        }

        return LocalDate.of(year, month, resultedDay);
    }

    /**
     * Finds the date of a specific occurrence of a day within a month (e.g., the 3rd Monday)
     *
     * @param year the year
     * @param month the month enum
     * @param day the day of the week
     * @param occurrence the occurrence enum (FIRST, SECOND, THIRD, FOURTH, FIFTH)
     * @return the date of the specified occurrence, or null if the occurrence doesn't exist in that month
     */
    public static LocalDate findDay(int year, Month month, DayOfWeek day, Occurrence occurrence) {
        return findDay(year, month.getValue(), day, occurrence.getValue());
    }

    /**
     * Check if a date is a weekend day for a given country
     * <p>
     * Translated from C# DateTimeExtension.IsWeekend method.
     * This method checks if the given date falls on a weekend day according to
     * the weekend rules of the specified country.
     * </p>
     * <p>
     * <b>Note:</b> This is a stub implementation. The full implementation requires
     * WeekendSystem infrastructure (WeekendProvider, IWeekendProvider, country-specific
     * weekend configurations) which will be migrated in a future task.
     * </p>
     *
     * @param date the date to check
     * @param countryCode the country code (ISO 3166-1 ALPHA-2)
     * @return true if the date is a weekend day in the given country, false otherwise
     * @throws UnsupportedOperationException until WeekendSystem is implemented
     */
    public static boolean isWeekend(LocalDate date, at.nager.date.CountryCode countryCode) {
        // TODO: Implement once WeekendSystem is migrated (requires IWeekendProvider, WeekendProvider, etc.)
        throw new UnsupportedOperationException(
                "isWeekend method requires WeekendSystem infrastructure which will be migrated in a future task. " +
                "This includes: WeekendSystem, IWeekendProvider, WeekendProvider, and country-specific weekend configurations."
        );
    }

    /**
     * Shift a date based on which day of the week it falls on
     * <p>
     * This method applies custom transformation functions to shift dates that fall on
     * Saturday, Sunday, or Monday. Useful for observed holiday calculations.
     * </p>
     *
     * @param value the date to potentially shift
     * @param saturday shift function for Saturday (e.g., date -> date.minusDays(1))
     * @param sunday shift function for Sunday (e.g., date -> date.plusDays(1))
     * @param monday shift function for Monday (optional, may be null)
     * @return the shifted date if it falls on Saturday/Sunday/Monday, otherwise the original date
     */
    static LocalDate shift(
            LocalDate value,
            java.util.function.Function<LocalDate, LocalDate> saturday,
            java.util.function.Function<LocalDate, LocalDate> sunday,
            java.util.function.Function<LocalDate, LocalDate> monday) {

        switch (value.getDayOfWeek()) {
            case SATURDAY:
                return saturday.apply(value);

            case SUNDAY:
                return sunday.apply(value);

            case MONDAY:
                if (monday != null) {
                    return monday.apply(value);
                }
                break;

            default:
                break;
        }

        return value;
    }
}
