package at.nager.date;

import at.nager.date.models.Holiday;
import at.nager.date.providers.IHolidayProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unique ID Test
 * <p>
 * Tests that holiday IDs are unique and properly formatted according to specifications.
 * Holiday IDs should follow the format: {COUNTRYCODE}-{INDEX} where INDEX is a two-digit number.
 * </p>
 * <p>
 * Note: These tests are structured to work with the current empty registry in Milestone 1.
 * Once country providers are registered (starting with Germany in Task 8), these tests
 * will validate all registered providers.
 * </p>
 */
class UniqueIdTest {

    @BeforeEach
    void setup() {
        // Set a valid license key for testing
        HolidaySystem.setLicenseKey("dGVzdC1saWNlbnNlLWtleS1mb3ItdGVzdGluZy1wdXJwb3Nlcw==");
    }

    @Test
    void holidayProvider_CheckIdIsUnique() {
        // Test that holiday IDs are unique within each country and year
        int startYear = LocalDate.now().getYear() - 100;
        int endYear = LocalDate.now().getYear() + 100;

        for (CountryCode countryCode : CountryCode.values()) {
            IHolidayProvider provider = HolidaySystem.getHolidayProvider(countryCode);

            for (int year = startYear; year <= endYear; year++) {
                List<Holiday> holidays = provider.getHolidays(year);

                // Filter holidays with IDs
                List<Holiday> holidaysWithId = holidays.stream()
                        .filter(holiday -> holiday.getId() != null && !holiday.getId().isEmpty())
                        .collect(Collectors.toList());

                // Get distinct IDs
                Set<String> distinctHolidayIds = holidaysWithId.stream()
                        .map(Holiday::getId)
                        .collect(Collectors.toSet());

                // Find duplicate IDs
                Map<String, Long> idCounts = holidaysWithId.stream()
                        .collect(Collectors.groupingBy(Holiday::getId, Collectors.counting()));

                List<String> duplicateIds = idCounts.entrySet().stream()
                        .filter(entry -> entry.getValue() > 1)
                        .map(Map.Entry::getKey)
                        .collect(Collectors.toList());

                assertEquals(holidaysWithId.size(), distinctHolidayIds.size(),
                        String.format("%s has %d duplicate IDs: %s",
                                countryCode, duplicateIds.size(), String.join(",", duplicateIds)));
            }
        }
    }

    @Test
    void holidayProvider_CheckIdFormat() {
        // Test that holiday IDs follow the correct format
        int startYear = LocalDate.now().getYear() - 100;
        int endYear = LocalDate.now().getYear() + 100;

        for (CountryCode countryCode : CountryCode.values()) {
            IHolidayProvider provider = HolidaySystem.getHolidayProvider(countryCode);

            for (int year = startYear; year <= endYear; year++) {
                List<Holiday> holidays = provider.getHolidays(year);

                for (Holiday holiday : holidays) {
                    if (holiday.getId() == null || holiday.getId().isEmpty()) {
                        continue;
                    }

                    String id = holiday.getId();

                    // Check that ID is uppercase, no spaces, and normalized
                    String upper = id.replace(" ", "").toUpperCase();
                    assertEquals(upper, id, "ID should be uppercase without spaces");

                    // Check ID length
                    assertTrue(id.length() <= 40,
                            String.format("%s - %s ID is too long (%d chars)",
                                    countryCode, id, id.length()));

                    // Check format: should have a dash 3 characters from the end
                    if (id.length() >= 3) {
                        char splitChar = id.charAt(id.length() - 3);
                        if (splitChar != '-') {
                            fail(String.format("wrong format %s - %s (expected dash at position -3)",
                                    countryCode, id));
                        }

                        // Check that last 2 characters are numeric
                        String holidayIdIndex = id.substring(id.length() - 2);
                        try {
                            int holidayIndex = Integer.parseInt(holidayIdIndex);
                            assertTrue(holidayIndex > 0,
                                    String.format("wrong format %s - %s (index must be > 0)",
                                            countryCode, id));
                        } catch (NumberFormatException e) {
                            fail(String.format("wrong format %s - %s (last 2 chars must be numeric)",
                                    countryCode, id));
                        }
                    }
                }
            }
        }
    }
}
