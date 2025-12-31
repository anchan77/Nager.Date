package at.nager.date.helpers;

import at.nager.date.CountryCode;

import java.util.Optional;

/**
 * CountryCode Helper
 * <p>
 * Provides utility methods for parsing and validating country codes.
 * </p>
 */
public class CountryCodeHelper {

    /**
     * Private constructor to prevent instantiation
     */
    private CountryCodeHelper() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Parse given string to CountryCode
     * <p>
     * Performs case-insensitive parsing of ISO 3166-1 Alpha-2 country codes.
     * </p>
     *
     * @param countryCode the country code string to parse (e.g., "de", "DE", "Us", "US")
     * @return Optional containing the parsed CountryCode if valid, empty Optional otherwise
     */
    public static Optional<CountryCode> tryParseCountryCode(String countryCode) {
        if (countryCode == null || countryCode.isEmpty()) {
            return Optional.empty();
        }

        try {
            return Optional.of(CountryCode.valueOf(countryCode.toUpperCase()));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}
