package at.nager.date.providers;

import at.nager.date.models.Holiday;

import java.util.Collections;
import java.util.List;

/**
 * No Holidays Holiday Provider
 * <p>
 * Fallback provider that returns no holidays.
 * Used when no specific provider is available for a country.
 * </p>
 */
final class NoHolidaysHolidayProvider implements IHolidayProvider {

    private static final IHolidayProvider INSTANCE = new NoHolidaysHolidayProvider();

    /**
     * Gets the singleton instance of NoHolidaysHolidayProvider
     *
     * @return the singleton instance
     */
    public static IHolidayProvider getInstance() {
        return INSTANCE;
    }

    /**
     * Private constructor to enforce singleton pattern
     */
    private NoHolidaysHolidayProvider() {
    }

    /**
     * {@inheritDoc}
     * Returns an empty list since this is a fallback provider with no holidays
     */
    @Override
    public List<Holiday> getHolidays(int year) {
        return Collections.emptyList();
    }

    /**
     * {@inheritDoc}
     * Returns an empty list since there are no sources for holidays
     */
    @Override
    public List<String> getSources() {
        return Collections.emptyList();
    }
}
