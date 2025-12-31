package at.nager.date.providers;

import at.nager.date.CountryCode;
import at.nager.date.models.Holiday;
import at.nager.date.models.HolidaySpecification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Holiday Specification Processor
 * <p>
 * Internal processing class that converts HolidaySpecification objects
 * (created by providers) into Holiday objects (returned to users).
 * Applies observed date rules and generates unique IDs.
 * </p>
 */
class HolidaySpecificationProcessor {

    /**
     * Private constructor to prevent instantiation
     */
    private HolidaySpecificationProcessor() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Process holiday specifications to holidays
     * <p>
     * For each specification:
     * - Generates ID in format {countryCode}-{specificationId}
     * - Applies ObservedRuleSet if present to calculate observed date
     * - Maps all fields from specification to Holiday
     * </p>
     *
     * @param holidaySpecifications list of holiday specifications from provider
     * @param countryCode the country code for the holidays
     * @return list of processed Holiday objects
     */
    static List<Holiday> process(
            List<HolidaySpecification> holidaySpecifications,
            CountryCode countryCode) {

        List<Holiday> holidays = new ArrayList<>();

        for (HolidaySpecification spec : holidaySpecifications) {
            LocalDate holidayDate = spec.getDate();
            String id = "";

            if (spec.getId() != null && !spec.getId().isEmpty()) {
                id = countryCode + "-" + spec.getId();
            }

            Holiday holiday = new Holiday();
            holiday.setId(id);
            holiday.setDate(holidayDate);
            holiday.setEnglishName(spec.getEnglishName());
            holiday.setLocalName(spec.getLocalName());
            holiday.setHolidayTypes(spec.getHolidayTypes());
            holiday.setSubdivisionCodes(spec.getSubdivisionCodes());
            holiday.setCountryCode(countryCode);

            // Apply observed rule set if present
            if (spec.getObservedRuleSet() != null) {
                LocalDate observedDate = spec.getObservedRuleSet().getObservedDate(holidayDate);
                holiday.setObservedDate(observedDate != null ? observedDate : holidayDate);
            } else {
                holiday.setObservedDate(holidayDate);
            }

            holidays.add(holiday);
        }

        return holidays;
    }
}
