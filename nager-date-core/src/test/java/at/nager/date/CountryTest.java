package at.nager.date;

import at.nager.date.providers.IHolidayProvider;
import at.nager.date.providers.ISubdivisionCodesProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Country Test
 * <p>
 * Tests country-specific provider functionality including country code validation,
 * subdivision code validation, and case-insensitive country code parsing.
 * </p>
 * <p>
 * Note: These tests are structured to work with the current empty registry in Milestone 1.
 * Once country providers are registered (starting with Germany in Task 8), these tests
 * will validate all registered providers.
 * </p>
 */
class CountryTest {

    @BeforeEach
    void setup() {
        // Set a valid license key for testing
        HolidaySystem.setLicenseKey("dGVzdC1saWNlbnNlLWtleS1mb3ItdGVzdGluZy1wdXJwb3Nlcw==");
    }

    @Test
    void holidayProvider_ReturnCorrectCountryCode() {
        // Test that providers return holidays with the correct country code
        for (CountryCode countryCode : CountryCode.values()) {
            IHolidayProvider provider = HolidaySystem.getHolidayProvider(countryCode);

            var holidays = provider.getHolidays(LocalDate.now().getYear());
            if (holidays.isEmpty()) {
                continue;
            }

            // Group by country code to ensure all holidays have the correct country
            Set<CountryCode> countries = new HashSet<>();
            for (var holiday : holidays) {
                countries.add(holiday.getCountryCode());
            }

            assertEquals(1, countries.size(), countryCode + " has a failure");
            assertEquals(countryCode, countries.iterator().next());
        }
    }

    @Test
    void checkSubdivisionCodes() {
        // Test that subdivision codes in holidays match the provider's defined subdivision codes
        List<String> failures = new ArrayList<>();

        int startYear = LocalDate.now().getYear() - 100;
        int endYear = LocalDate.now().getYear() + 100;

        for (CountryCode countryCode : CountryCode.values()) {
            IHolidayProvider provider = HolidaySystem.getHolidayProvider(countryCode);

            Map<String, String> subdivisionCodes = new HashMap<>();
            if (provider instanceof ISubdivisionCodesProvider) {
                subdivisionCodes = ((ISubdivisionCodesProvider) provider).getSubdivisionCodes();
            }

            for (int year = startYear; year <= endYear; year++) {
                var holidays = HolidaySystem.getHolidays(year, countryCode);

                for (var holiday : holidays) {
                    if (holiday.getSubdivisionCodes() == null) {
                        continue;
                    }

                    // Check for duplicate subdivision codes within a single holiday
                    Map<String, Long> codeCount = new HashMap<>();
                    for (String code : holiday.getSubdivisionCodes()) {
                        codeCount.put(code, codeCount.getOrDefault(code, 0L) + 1);
                    }

                    for (Map.Entry<String, Long> entry : codeCount.entrySet()) {
                        if (entry.getValue() > 1) {
                            failures.add(countryCode + " - Duplicate SubdivisionCode by " + holiday);
                        }
                    }

                    // Check that all subdivision codes are defined in the provider
                    int definedCount = 0;
                    List<String> unknownCodes = new ArrayList<>();
                    for (String code : holiday.getSubdivisionCodes()) {
                        if (subdivisionCodes.containsKey(code)) {
                            definedCount++;
                        } else {
                            unknownCodes.add(code);
                        }
                    }

                    if (definedCount != holiday.getSubdivisionCodes().length) {
                        failures.add("Unknown subdivisionCode in " + provider.getClass().getSimpleName() +
                                   " \"" + holiday.getEnglishName() + "\" " + String.join(",", unknownCodes));
                    }
                }
            }
        }

        if (!failures.isEmpty()) {
            fail(System.lineSeparator() + String.join(System.lineSeparator(), failures));
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"de", "De", "dE", "DE"})
    void checkCaseInsensitive(String countryCode) {
        // Test that country code parsing is case-insensitive
        var result = HolidaySystem.getHolidays(2018, countryCode);
        assertNotNull(result);
    }

    @Test
    void holidaySystem_CheckInvalidCountry_ThrowException() {
        // Test that invalid country codes throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> {
            HolidaySystem.getHolidays(2018, "1000");
        });
    }
}
