package at.nager.date.providers;

import at.nager.date.models.Holiday;

import java.util.List;

/**
 * Holiday Provider Interface
 * Defines the contract for all country-specific holiday providers
 */
public interface IHolidayProvider {

    /**
     * Get Holidays of the given year
     *
     * @param year the year to get holidays for
     * @return List of holidays for the given year
     */
    List<Holiday> getHolidays(int year);

    /**
     * Get the Holiday Sources
     * Returns URLs or references to official documentation for the holiday data
     *
     * @return List of holiday sources (links to official documentation)
     */
    List<String> getSources();
}
