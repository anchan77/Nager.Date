package at.nager.date.providers.religious;

import at.nager.date.models.HolidaySpecification;
import at.nager.date.models.ObservedRuleSet;

import java.time.LocalDate;

/**
 * Orthodox Provider Interface
 */
public interface IOrthodoxProvider {

    /**
     * Get Orthodox easter for requested year
     * @param year the year
     * @return Date of Orthodox Easter Sunday for given year
     */
    LocalDate easterSunday(int year);

    /**
     * Get Good Friday
     * @param localName The local name of the holiday
     * @param year the year
     * @param observedRuleSet the observed rule set (optional)
     * @return Public holiday info for Orthodox Good Friday for given year and country
     */
    HolidaySpecification goodFriday(String localName, int year, ObservedRuleSet observedRuleSet);

    /**
     * Get Good Friday (without observed rule set)
     * @param localName The local name of the holiday
     * @param year the year
     * @return Public holiday info for Orthodox Good Friday for given year and country
     */
    default HolidaySpecification goodFriday(String localName, int year) {
        return goodFriday(localName, year, null);
    }

    /**
     * Get Holy Saturday
     * @param localName The local name of the holiday
     * @param year the year
     * @param observedRuleSet the observed rule set (optional)
     * @return Public holiday info for Orthodox Holy Saturday for given year and country
     */
    HolidaySpecification holySaturday(String localName, int year, ObservedRuleSet observedRuleSet);

    /**
     * Get Holy Saturday (without observed rule set)
     * @param localName The local name of the holiday
     * @param year the year
     * @return Public holiday info for Orthodox Holy Saturday for given year and country
     */
    default HolidaySpecification holySaturday(String localName, int year) {
        return holySaturday(localName, year, null);
    }

    /**
     * Get Easter Sunday (specification)
     * @param localName The local name of the holiday
     * @param year the year
     * @param observedRuleSet the observed rule set (optional)
     * @return Public holiday info for Orthodox Easter Sunday for given year and country
     */
    HolidaySpecification easterSundaySpecification(String localName, int year, ObservedRuleSet observedRuleSet);

    /**
     * Get Easter Sunday (specification, without observed rule set)
     * @param localName The local name of the holiday
     * @param year the year
     * @return Public holiday info for Orthodox Easter Sunday for given year and country
     */
    default HolidaySpecification easterSundaySpecification(String localName, int year) {
        return easterSundaySpecification(localName, year, null);
    }

    /**
     * Get Easter Monday
     * @param localName The local name of the holiday
     * @param year the year
     * @param observedRuleSet the observed rule set (optional)
     * @return Public holiday info for Orthodox Easter Monday for given year and country
     */
    HolidaySpecification easterMonday(String localName, int year, ObservedRuleSet observedRuleSet);

    /**
     * Get Easter Monday (without observed rule set)
     * @param localName The local name of the holiday
     * @param year the year
     * @return Public holiday info for Orthodox Easter Monday for given year and country
     */
    default HolidaySpecification easterMonday(String localName, int year) {
        return easterMonday(localName, year, null);
    }

    /**
     * Get Pentecost
     * @param localName The local name of the holiday
     * @param year the year
     * @param observedRuleSet the observed rule set (optional)
     * @return Public holiday info for Orthodox Pentecost for given year and country
     */
    HolidaySpecification pentecost(String localName, int year, ObservedRuleSet observedRuleSet);

    /**
     * Get Pentecost (without observed rule set)
     * @param localName The local name of the holiday
     * @param year the year
     * @return Public holiday info for Orthodox Pentecost for given year and country
     */
    default HolidaySpecification pentecost(String localName, int year) {
        return pentecost(localName, year, null);
    }

    /**
     * Get Whit Monday / Pentecost Monday
     * @param localName The local name of the holiday
     * @param year the year
     * @param observedRuleSet the observed rule set (optional)
     * @return Public holiday info for Orthodox Whit Monday / Pentecost Monday for given year, country and counties
     */
    HolidaySpecification whitMonday(String localName, int year, ObservedRuleSet observedRuleSet);

    /**
     * Get Whit Monday / Pentecost Monday (without observed rule set)
     * @param localName The local name of the holiday
     * @param year the year
     * @return Public holiday info for Orthodox Whit Monday / Pentecost Monday for given year, country and counties
     */
    default HolidaySpecification whitMonday(String localName, int year) {
        return whitMonday(localName, year, null);
    }
}
