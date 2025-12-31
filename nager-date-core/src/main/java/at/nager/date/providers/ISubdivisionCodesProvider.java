package at.nager.date.providers;

import java.util.Map;

/**
 * Subdivision Codes Provider Interface
 * Implemented by holiday providers that support subdivisions (states, provinces, territories, etc.)
 */
public interface ISubdivisionCodesProvider {

    /**
     * Get Subdivision Codes
     * Returns a mapping of ISO 3166-2 subdivision codes to human-readable names
     *
     * @return Map of subdivision code to subdivision name
     */
    Map<String, String> getSubdivisionCodes();
}
