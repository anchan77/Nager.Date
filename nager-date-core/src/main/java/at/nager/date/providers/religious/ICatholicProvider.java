package at.nager.date.providers.religious;

import at.nager.date.models.HolidaySpecification;
import at.nager.date.models.ObservedRuleSet;

import java.time.LocalDate;

/**
 * Catholic Provider Interface
 */
public interface ICatholicProvider {

    /**
     * Get Catholic easter for requested year
     * @param year the year
     * @return Date of Catholic Easter Sunday for given year
     */
    LocalDate easterSunday(int year);

    /**
     * Get advent sunday for requested year
     * @param year the year
     * @return Date of Catholic Advent Sunday for given year
     */
    LocalDate adventSunday(int year);

    /**
     * Get Maundy Thursday
     * @param localName The local name of the holiday
     * @param year the year
     * @param observedRuleSet the observed rule set (optional)
     * @return Holiday info for Catholic Maundy Thursday for given year
     */
    HolidaySpecification maundyThursday(String localName, int year, ObservedRuleSet observedRuleSet);

    /**
     * Get Maundy Thursday (without observed rule set)
     * @param localName The local name of the holiday
     * @param year the year
     * @return Holiday info for Catholic Maundy Thursday for given year
     */
    default HolidaySpecification maundyThursday(String localName, int year) {
        return maundyThursday(localName, year, null);
    }

    /**
     * Get Good Friday
     * @param localName The local name of the holiday
     * @param year the year
     * @param observedRuleSet the observed rule set (optional)
     * @return Holiday info for Catholic Good Friday for given year
     */
    HolidaySpecification goodFriday(String localName, int year, ObservedRuleSet observedRuleSet);

    /**
     * Get Good Friday (without observed rule set)
     * @param localName The local name of the holiday
     * @param year the year
     * @return Holiday info for Catholic Good Friday for given year
     */
    default HolidaySpecification goodFriday(String localName, int year) {
        return goodFriday(localName, year, null);
    }

    /**
     * Get Easter Saturday
     * @param localName The local name of the holiday
     * @param year the year
     * @param observedRuleSet the observed rule set (optional)
     * @return Holiday info for Catholic Easter Saturday for given year
     */
    HolidaySpecification easterSaturday(String localName, int year, ObservedRuleSet observedRuleSet);

    /**
     * Get Easter Saturday (without observed rule set)
     * @param localName The local name of the holiday
     * @param year the year
     * @return Holiday info for Catholic Easter Saturday for given year
     */
    default HolidaySpecification easterSaturday(String localName, int year) {
        return easterSaturday(localName, year, null);
    }

    /**
     * Get Easter Sunday (specification)
     * @param localName The local name of the holiday
     * @param year the year
     * @param observedRuleSet the observed rule set (optional)
     * @return Holiday info for Catholic Easter Sunday for given year
     */
    HolidaySpecification easterSundaySpecification(String localName, int year, ObservedRuleSet observedRuleSet);

    /**
     * Get Easter Sunday (specification, without observed rule set)
     * @param localName The local name of the holiday
     * @param year the year
     * @return Holiday info for Catholic Easter Sunday for given year
     */
    default HolidaySpecification easterSundaySpecification(String localName, int year) {
        return easterSundaySpecification(localName, year, null);
    }

    /**
     * Get Easter Monday
     * @param localName The local name of the holiday
     * @param year the year
     * @param observedRuleSet the observed rule set (optional)
     * @return Holiday info for Catholic Easter Monday for given year
     */
    HolidaySpecification easterMonday(String localName, int year, ObservedRuleSet observedRuleSet);

    /**
     * Get Easter Monday (without observed rule set)
     * @param localName The local name of the holiday
     * @param year the year
     * @return Holiday info for Catholic Easter Monday for given year
     */
    default HolidaySpecification easterMonday(String localName, int year) {
        return easterMonday(localName, year, null);
    }

    /**
     * Get Ascension Day
     * @param localName The local name of the holiday
     * @param year the year
     * @param observedRuleSet the observed rule set (optional)
     * @return Holiday info for Ascension Day for given year
     */
    HolidaySpecification ascensionDay(String localName, int year, ObservedRuleSet observedRuleSet);

    /**
     * Get Ascension Day (without observed rule set)
     * @param localName The local name of the holiday
     * @param year the year
     * @return Holiday info for Ascension Day for given year
     */
    default HolidaySpecification ascensionDay(String localName, int year) {
        return ascensionDay(localName, year, null);
    }

    /**
     * Get Pentecost
     * @param localName The local name of the holiday
     * @param year the year
     * @param observedRuleSet the observed rule set (optional)
     * @return Holiday info for Pentecost for given year
     */
    HolidaySpecification pentecost(String localName, int year, ObservedRuleSet observedRuleSet);

    /**
     * Get Pentecost (without observed rule set)
     * @param localName The local name of the holiday
     * @param year the year
     * @return Holiday info for Pentecost for given year
     */
    default HolidaySpecification pentecost(String localName, int year) {
        return pentecost(localName, year, null);
    }

    /**
     * Get Whit Monday
     * @param localName The local name of the holiday
     * @param year the year
     * @param observedRuleSet the observed rule set (optional)
     * @return Holiday info for Whit Monday for given year
     */
    HolidaySpecification whitMonday(String localName, int year, ObservedRuleSet observedRuleSet);

    /**
     * Get Whit Monday (without observed rule set)
     * @param localName The local name of the holiday
     * @param year the year
     * @return Holiday info for Whit Monday for given year
     */
    default HolidaySpecification whitMonday(String localName, int year) {
        return whitMonday(localName, year, null);
    }

    /**
     * Get Corpus Christi
     * @param localName The local name of the holiday
     * @param year the year
     * @param observedRuleSet the observed rule set (optional)
     * @return Holiday info for Corpus Christi for given year
     */
    HolidaySpecification corpusChristi(String localName, int year, ObservedRuleSet observedRuleSet);

    /**
     * Get Corpus Christi (without observed rule set)
     * @param localName The local name of the holiday
     * @param year the year
     * @return Holiday info for Corpus Christi for given year
     */
    default HolidaySpecification corpusChristi(String localName, int year) {
        return corpusChristi(localName, year, null);
    }
}
