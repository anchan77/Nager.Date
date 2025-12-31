package at.nager.date;

import at.nager.date.models.Holiday;
import at.nager.date.models.HolidayTypes;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.EnumSet;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Logic Test
 * <p>
 * Tests core business logic in the Holiday model, particularly the isNationalHoliday() method
 * which determines whether a holiday is national based on the presence of subdivision codes.
 * </p>
 */
class LogicTest {

    @Test
    void checkHolidayNationalWork() {
        // Test that a holiday without subdivision codes is considered national
        Holiday publicHoliday = new Holiday();
        publicHoliday.setDate(LocalDate.of(2020, 1, 30));
        publicHoliday.setEnglishName("Test");
        publicHoliday.setLocalName("Test");
        publicHoliday.setCountryCode(CountryCode.AT);
        publicHoliday.setHolidayTypes(EnumSet.of(HolidayTypes.PUBLIC));

        assertTrue(publicHoliday.isNationalHoliday(),
                "Holiday without subdivision codes should be national");

        // Test that a holiday with subdivision codes is NOT considered national
        Holiday publicHolidayWithSubdivisionCodes = new Holiday();
        publicHolidayWithSubdivisionCodes.setDate(LocalDate.of(2020, 1, 30));
        publicHolidayWithSubdivisionCodes.setEnglishName("Test");
        publicHolidayWithSubdivisionCodes.setLocalName("Test");
        publicHolidayWithSubdivisionCodes.setCountryCode(CountryCode.AT);
        publicHolidayWithSubdivisionCodes.setHolidayTypes(EnumSet.of(HolidayTypes.PUBLIC));
        publicHolidayWithSubdivisionCodes.setSubdivisionCodes(new String[]{"AT-1"});

        assertFalse(publicHolidayWithSubdivisionCodes.isNationalHoliday(),
                "Holiday with subdivision codes should NOT be national");
    }
}
