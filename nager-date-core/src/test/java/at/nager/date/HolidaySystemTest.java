package at.nager.date;

import at.nager.date.models.Holiday;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Holiday System Test
 * <p>
 * Tests the core HolidaySystem facade functionality including license enforcement,
 * provider access, and holiday querying methods.
 * </p>
 * <p>
 * Note: Full country-specific tests will be added in later milestones once
 * country providers are implemented. This test focuses on the core API structure
 * and license validation.
 * </p>
 */
class HolidaySystemTest {

    @AfterEach
    void resetLicense() {
        // Reset license between tests by setting a valid test key
        HolidaySystem.setLicenseKey(createValidTestLicenseKey());
    }

    /**
     * Creates a valid test license key for testing purposes
     */
    private String createValidTestLicenseKey() {
        // This is a valid base64 string that passes the basic format check
        // The LicenseHelper in test mode accepts any properly formatted base64 string
        return "dGVzdC1saWNlbnNlLWtleS1mb3ItdGVzdGluZy1wdXJwb3Nlcw==";
    }

    @Test
    void testSetLicenseKey() {
        String testKey = createValidTestLicenseKey();
        assertDoesNotThrow(() -> HolidaySystem.setLicenseKey(testKey));
    }

    @Test
    void testGetHolidayProvider_WithoutLicense_ThrowsException() {
        HolidaySystem.setLicenseKey(null);

        LicenseKeyException exception = assertThrows(
                LicenseKeyException.class,
                () -> HolidaySystem.getHolidayProvider(CountryCode.DE)
        );

        assertTrue(exception.getMessage().contains("No LicenseKey"));
    }

    @Test
    void testGetHolidayProvider_WithInvalidLicense_ThrowsException() {
        HolidaySystem.setLicenseKey("invalid");

        LicenseKeyException exception = assertThrows(
                LicenseKeyException.class,
                () -> HolidaySystem.getHolidayProvider(CountryCode.DE)
        );

        assertTrue(exception.getMessage().contains("Invalid LicenseKey"));
    }

    @Test
    void testGetHolidayProvider_WithValidLicense_ReturnsProvider() {
        HolidaySystem.setLicenseKey(createValidTestLicenseKey());

        // Currently no providers are registered in Milestone 1
        // This should return NoHolidaysHolidayProvider
        assertDoesNotThrow(() -> {
            var provider = HolidaySystem.getHolidayProvider(CountryCode.DE);
            assertNotNull(provider);
        });
    }

    @Test
    void testGetHolidayProvider_StringCountryCode_ValidCode() {
        HolidaySystem.setLicenseKey(createValidTestLicenseKey());

        assertDoesNotThrow(() -> {
            var provider = HolidaySystem.getHolidayProvider("DE");
            assertNotNull(provider);
        });
    }

    @Test
    void testGetHolidayProvider_StringCountryCode_InvalidCode() {
        HolidaySystem.setLicenseKey(createValidTestLicenseKey());

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> HolidaySystem.getHolidayProvider("INVALID")
        );

        assertTrue(exception.getMessage().contains("not valid according to ISO 3166-1 ALPHA-2"));
    }

    @Test
    void testGetHolidays_Year_GermanyRegistered() {
        HolidaySystem.setLicenseKey(createValidTestLicenseKey());

        List<Holiday> holidays = HolidaySystem.getHolidays(2024, CountryCode.DE);
        assertNotNull(holidays);
        // Germany provider is registered (but returns empty list as placeholder)
        assertTrue(holidays.isEmpty());
    }

    @Test
    void testGetHolidays_DateRange_GermanyRegistered() {
        HolidaySystem.setLicenseKey(createValidTestLicenseKey());

        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 12, 31);

        List<Holiday> holidays = HolidaySystem.getHolidays(startDate, endDate, CountryCode.DE);
        assertNotNull(holidays);
        // Germany provider is registered (but returns empty list as placeholder)
        assertTrue(holidays.isEmpty());
    }

    @Test
    void testGetHolidays_DateRange_InvalidRange_ThrowsException() {
        HolidaySystem.setLicenseKey(createValidTestLicenseKey());

        LocalDate startDate = LocalDate.of(2024, 12, 31);
        LocalDate endDate = LocalDate.of(2024, 1, 1);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> HolidaySystem.getHolidays(startDate, endDate, CountryCode.DE)
        );

        assertTrue(exception.getMessage().contains("endDate is before startDate"));
    }

    @Test
    void testGetHolidays_Worldwide_GermanyRegistered() {
        HolidaySystem.setLicenseKey(createValidTestLicenseKey());

        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 12, 31);

        List<Holiday> holidays = HolidaySystem.getHolidays(startDate, endDate);
        assertNotNull(holidays);
        // Germany provider returns empty list (placeholder), so worldwide is also empty
        assertTrue(holidays.isEmpty());
    }

    @Test
    void testIsPublicHoliday_GermanyRegistered() {
        HolidaySystem.setLicenseKey(createValidTestLicenseKey());

        LocalDate date = LocalDate.of(2024, 1, 1);
        boolean isPublicHoliday = HolidaySystem.isPublicHoliday(date, CountryCode.DE);

        // Should return false since Germany provider returns empty list (placeholder)
        assertFalse(isPublicHoliday);
    }

    @Test
    void testIsPublicHoliday_WithSubdivision_NullSubdivision_ThrowsException() {
        HolidaySystem.setLicenseKey(createValidTestLicenseKey());

        LocalDate date = LocalDate.of(2024, 1, 1);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> HolidaySystem.isPublicHoliday(date, CountryCode.DE, null)
        );

        assertTrue(exception.getMessage().contains("subdivisionCode is null"));
    }

    @Test
    void testTryGetHolidayProvider_ReturnsOptional() {
        HolidaySystem.setLicenseKey(createValidTestLicenseKey());

        var optionalProvider = HolidaySystem.tryGetHolidayProvider(CountryCode.DE);
        assertNotNull(optionalProvider);
        // Germany is registered, so should return present Optional
        assertTrue(optionalProvider.isPresent());
    }

    @Test
    void testTryGetHolidayProvider_WithoutLicense_ThrowsException() {
        HolidaySystem.setLicenseKey(null);

        assertThrows(
                LicenseKeyException.class,
                () -> HolidaySystem.tryGetHolidayProvider(CountryCode.DE)
        );
    }
}
