package at.nager.date.providers;

import at.nager.date.CountryCode;
import at.nager.date.models.Holiday;
import at.nager.date.models.HolidaySpecification;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Abstract Holiday Provider
 * <p>
 * Base class for country-specific holiday providers.
 * Implements the template method pattern: subclasses provide holiday specifications,
 * and this class converts them to Holiday objects and orders them by date.
 * </p>
 */
abstract class AbstractHolidayProvider implements IHolidayProvider {

    private final CountryCode countryCode;

    /**
     * Constructor
     *
     * @param countryCode the country code this provider handles
     */
    protected AbstractHolidayProvider(CountryCode countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * Get the country code for this provider
     *
     * @return the country code
     */
    protected CountryCode getCountryCode() {
        return countryCode;
    }

    /**
     * Get holidays of the given year
     * <p>
     * Template method that:
     * 1. Calls getHolidaySpecifications(year) to get specifications from subclass
     * 2. Processes specifications into Holiday objects
     * 3. Orders holidays by date
     * </p>
     *
     * @param year the year for which to retrieve holidays
     * @return list of holidays for the given year, ordered by date
     */
    @Override
    public List<Holiday> getHolidays(int year) {
        List<HolidaySpecification> holidaySpecifications = getHolidaySpecifications(year);
        List<Holiday> holidays = HolidaySpecificationProcessor.process(holidaySpecifications, this.countryCode);
        return holidays.stream()
                .sorted(Comparator.comparing(Holiday::getDate))
                .collect(Collectors.toList());
    }

    /**
     * Get holiday specifications for a given year
     * <p>
     * Subclasses must implement this method to define country-specific holidays.
     * </p>
     *
     * @param year the year for which to retrieve holiday specifications
     * @return list of holiday specifications for the given year
     */
    protected abstract List<HolidaySpecification> getHolidaySpecifications(int year);
}
