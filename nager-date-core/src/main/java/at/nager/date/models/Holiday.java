package at.nager.date.models;

import at.nager.date.CountryCode;

import java.time.LocalDate;
import java.util.EnumSet;

/**
 * Holiday
 */
public class Holiday {

    private String id = "";
    private LocalDate date;
    private LocalDate observedDate;
    private String englishName = "";
    private String localName = "";
    private CountryCode countryCode;
    private String[] subdivisionCodes;
    private EnumSet<HolidayTypes> holidayTypes = EnumSet.of(HolidayTypes.PUBLIC);

    /**
     * Gets the unique ID for the Holiday
     * @return the unique ID
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the unique ID for the Holiday
     * @param id the unique ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the date
     * @return the date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Sets the date
     * @param date the date
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Gets the date on which the holiday is observed
     * @return the observed date
     */
    public LocalDate getObservedDate() {
        return observedDate;
    }

    /**
     * Sets the date on which the holiday is observed
     * @param observedDate the observed date
     */
    public void setObservedDate(LocalDate observedDate) {
        this.observedDate = observedDate;
    }

    /**
     * Gets the name of the holiday in English
     * @return the English name
     */
    public String getEnglishName() {
        return englishName;
    }

    /**
     * Sets the name of the holiday in English
     * @param englishName the English name
     */
    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    /**
     * Gets the name of the holiday in the local language
     * @return the local name
     */
    public String getLocalName() {
        return localName;
    }

    /**
     * Sets the name of the holiday in the local language
     * @param localName the local name
     */
    public void setLocalName(String localName) {
        this.localName = localName;
    }

    /**
     * Gets the country where the holiday is observed
     * @return the country code (ISO 3166-1 alpha-2)
     */
    public CountryCode getCountryCode() {
        return countryCode;
    }

    /**
     * Sets the country where the holiday is observed
     * @param countryCode the country code (ISO 3166-1 alpha-2)
     */
    public void setCountryCode(CountryCode countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * Indicates whether the holiday is a national holiday
     * @return true if it's a national holiday (no subdivision codes), false otherwise
     */
    public boolean isNationalHoliday() {
        return this.subdivisionCodes == null || this.subdivisionCodes.length == 0;
    }

    /**
     * Gets the initial subdivision of the country (ISO 3166-2) where the holiday is observed
     * @return the subdivision codes (States, Province, Territories, Federal districts, Cantons)
     */
    public String[] getSubdivisionCodes() {
        return subdivisionCodes;
    }

    /**
     * Sets the initial subdivision of the country (ISO 3166-2) where the holiday is observed
     * @param subdivisionCodes the subdivision codes (States, Province, Territories, Federal districts, Cantons)
     */
    public void setSubdivisionCodes(String[] subdivisionCodes) {
        this.subdivisionCodes = subdivisionCodes;
    }

    /**
     * Gets the types of the holiday
     * @return the holiday types
     */
    public EnumSet<HolidayTypes> getHolidayTypes() {
        return holidayTypes;
    }

    /**
     * Sets the types of the holiday
     * @param holidayTypes the holiday types
     */
    public void setHolidayTypes(EnumSet<HolidayTypes> holidayTypes) {
        this.holidayTypes = holidayTypes;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s",
            this.date != null ? this.date.toString() : "null",
            this.id,
            this.englishName);
    }
}
