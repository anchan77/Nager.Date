package at.nager.date;

import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * Weekend System
 * <p>
 * Provides weekend detection functionality for different countries.
 * Note: This is a minimal implementation for Task 8. Full implementation
 * with country-specific weekend patterns will be completed in a future milestone.
 * </p>
 */
public class WeekendSystem {

    /**
     * Private constructor to prevent instantiation
     */
    private WeekendSystem() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Checks if a given date falls on a weekend in the specified country
     * <p>
     * Note: Current implementation assumes universal weekend (Saturday-Sunday) for all countries.
     * Country-specific weekend patterns will be implemented in a future milestone.
     * </p>
     *
     * @param date        The date to check
     * @param countryCode The country code (ISO 3166-1 ALPHA-2) to determine weekend rules
     * @return True if the given date is a weekend in the specified country, false otherwise
     */
    public static boolean isWeekend(LocalDate date, CountryCode countryCode) {
        // For now, use universal weekend (Saturday and Sunday) for all countries
        // TODO: Implement country-specific weekend patterns in future milestone
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }
}
