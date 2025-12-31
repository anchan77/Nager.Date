package at.nager.date.models;

import java.time.LocalDate;
import java.util.EnumSet;
import java.util.Map;

/**
 * Holiday Specification
 * Internal model used by providers to define holidays
 */
public class HolidaySpecification {

    private String id = "";
    private LocalDate date;
    private String englishName = "";
    private String localName = "";
    private String[] subdivisionCodes;
    private EnumSet<HolidayTypes> holidayTypes = EnumSet.of(HolidayTypes.PUBLIC);
    private ObservedRuleSet observedRuleSet;
    private Map<String, String> additionalTranslations;
    private HolidaySources holidaySources = HolidaySources.UNDEFINED_HOLIDAY;

    /**
     * Gets the unique ID for the Holiday
     * <p>
     * Database / format rules:
     * <ul>
     * <li>Starts with ISO country code (e.g., "DE")</li>
     * <li>Separator: hyphen ("-")</li>
     * <li>Holiday name in uppercase letters, optionally with hyphens (e.g., "CHRISTMASEVE")</li>
     * <li>Ends with a two-digit numeric index (e.g., "01")</li>
     * <li>Maximum total length: 40 characters</li>
     * <li>Allowed characters: A-Z, 0-9, and hyphens</li>
     * </ul>
     * Examples: "DE-CHRISTMASEVE-01", "DE-NEWYEARSDAY-01"
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
     * Gets the English name
     * @return the English name
     */
    public String getEnglishName() {
        return englishName;
    }

    /**
     * Sets the English name
     * @param englishName the English name
     */
    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    /**
     * Gets the local name
     * @return the local name
     */
    public String getLocalName() {
        return localName;
    }

    /**
     * Sets the local name
     * @param localName the local name
     */
    public void setLocalName(String localName) {
        this.localName = localName;
    }

    /**
     * Gets the initial subdivision of a country (ISO 3166-2)
     * @return the subdivision codes (States, Province, Territories, Federal districts, Cantons)
     */
    public String[] getSubdivisionCodes() {
        return subdivisionCodes;
    }

    /**
     * Sets the initial subdivision of a country (ISO 3166-2)
     * @param subdivisionCodes the subdivision codes
     */
    public void setSubdivisionCodes(String[] subdivisionCodes) {
        this.subdivisionCodes = subdivisionCodes;
    }

    /**
     * Gets the list of types the holiday is valid for
     * @return the holiday types
     */
    public EnumSet<HolidayTypes> getHolidayTypes() {
        return holidayTypes;
    }

    /**
     * Sets the list of types the holiday is valid for
     * @param holidayTypes the holiday types
     */
    public void setHolidayTypes(EnumSet<HolidayTypes> holidayTypes) {
        this.holidayTypes = holidayTypes;
    }

    /**
     * Gets the ruleset to calculate the observed date for the holiday
     * @return the observed rule set
     */
    public ObservedRuleSet getObservedRuleSet() {
        return observedRuleSet;
    }

    /**
     * Sets the ruleset to calculate the observed date for the holiday
     * @param observedRuleSet the observed rule set
     */
    public void setObservedRuleSet(ObservedRuleSet observedRuleSet) {
        this.observedRuleSet = observedRuleSet;
    }

    /**
     * Gets additional holiday translations
     * @return map of language code to translated name
     */
    public Map<String, String> getAdditionalTranslations() {
        return additionalTranslations;
    }

    /**
     * Sets additional holiday translations
     * @param additionalTranslations map of language code to translated name
     */
    public void setAdditionalTranslations(Map<String, String> additionalTranslations) {
        this.additionalTranslations = additionalTranslations;
    }

    /**
     * Gets the holiday source
     * @return the holiday sources
     */
    public HolidaySources getHolidaySources() {
        return holidaySources;
    }

    /**
     * Sets the holiday source
     * @param holidaySources the holiday sources
     */
    public void setHolidaySources(HolidaySources holidaySources) {
        this.holidaySources = holidaySources;
    }

    /**
     * Sets the subdivision codes (fluent API)
     * @param subdivisionCodes the subdivision codes
     * @return this instance for method chaining
     */
    public HolidaySpecification withSubdivisionCodes(String... subdivisionCodes) {
        this.subdivisionCodes = subdivisionCodes;
        return this;
    }

    /**
     * Sets the holiday types (fluent API)
     * @param holidayTypes the holiday types
     * @return this instance for method chaining
     */
    public HolidaySpecification withHolidayTypes(EnumSet<HolidayTypes> holidayTypes) {
        this.holidayTypes = holidayTypes;
        return this;
    }

    /**
     * Sets the ID (fluent API)
     * @param id the ID
     * @return this instance for method chaining
     */
    public HolidaySpecification withId(String id) {
        this.id = id;
        return this;
    }
}
