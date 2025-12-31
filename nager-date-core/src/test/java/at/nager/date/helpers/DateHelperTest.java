package at.nager.date.helpers;

import at.nager.date.models.Month;
import at.nager.date.models.Occurrence;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Date Helper Test
 * <p>
 * Tests for all DateHelper methods to ensure correct date calculations.
 * Ported from C# MSTest to JUnit Jupiter.
 * </p>
 */
class DateHelperTest {

    @Test
    void checkFindDay() {
        LocalDate result = DateHelper.findDay(2017, 1, 1, DayOfWeek.FRIDAY);
        assertEquals(LocalDate.of(2017, 1, 6), result);

        result = DateHelper.findDay(2017, 1, 2, DayOfWeek.FRIDAY);
        assertEquals(LocalDate.of(2017, 1, 6), result);

        result = DateHelper.findDay(2017, 1, 3, DayOfWeek.FRIDAY);
        assertEquals(LocalDate.of(2017, 1, 6), result);

        result = DateHelper.findDay(2017, 1, 4, DayOfWeek.FRIDAY);
        assertEquals(LocalDate.of(2017, 1, 6), result);

        result = DateHelper.findDay(2017, 1, 5, DayOfWeek.FRIDAY);
        assertEquals(LocalDate.of(2017, 1, 6), result);

        result = DateHelper.findDay(2017, 1, 6, DayOfWeek.FRIDAY);
        assertEquals(LocalDate.of(2017, 1, 6), result);

        result = DateHelper.findDay(2017, 1, 7, DayOfWeek.FRIDAY);
        assertEquals(LocalDate.of(2017, 1, 13), result);

        result = DateHelper.findDay(2017, 1, 8, DayOfWeek.FRIDAY);
        assertEquals(LocalDate.of(2017, 1, 13), result);

        result = DateHelper.findDay(2017, 1, 9, DayOfWeek.FRIDAY);
        assertEquals(LocalDate.of(2017, 1, 13), result);

        result = DateHelper.findDay(2017, 1, 10, DayOfWeek.FRIDAY);
        assertEquals(LocalDate.of(2017, 1, 13), result);

        result = DateHelper.findDay(2017, 1, 11, DayOfWeek.FRIDAY);
        assertEquals(LocalDate.of(2017, 1, 13), result);

        result = DateHelper.findDay(2017, 1, 12, DayOfWeek.FRIDAY);
        assertEquals(LocalDate.of(2017, 1, 13), result);

        result = DateHelper.findDay(2017, 1, 13, DayOfWeek.FRIDAY);
        assertEquals(LocalDate.of(2017, 1, 13), result);

        result = DateHelper.findDay(2017, 1, 14, DayOfWeek.WEDNESDAY);
        assertEquals(LocalDate.of(2017, 1, 18), result);

        result = DateHelper.findDay(2022, 1, 1, DayOfWeek.MONDAY);
        assertEquals(LocalDate.of(2022, 1, 3), result);

        result = DateHelper.findDay(2022, 1, 1, DayOfWeek.TUESDAY);
        assertEquals(LocalDate.of(2022, 1, 4), result);
    }

    @Test
    void checkFindDayBefore() {
        LocalDate result = DateHelper.findDayBefore(2018, 5, 25, DayOfWeek.MONDAY);
        assertEquals(LocalDate.of(2018, 5, 21), result);

        result = DateHelper.findDayBefore(2018, 1, 9, DayOfWeek.MONDAY);
        assertEquals(LocalDate.of(2018, 1, 8), result);

        result = DateHelper.findDayBefore(2018, 1, 8, DayOfWeek.MONDAY);
        assertEquals(LocalDate.of(2018, 1, 1), result);

        result = DateHelper.findDayBefore(2018, 1, 12, DayOfWeek.FRIDAY);
        assertEquals(LocalDate.of(2018, 1, 5), result);
    }

    @Test
    void checkFindDayBetween1() {
        LocalDate result = DateHelper.findDayBetween(2019, 7, 1, 2019, 7, 7, DayOfWeek.TUESDAY);
        assertNotNull(result);
        assertEquals(LocalDate.of(2019, 7, 2), result);
    }

    @Test
    void checkFindDayBetween2() {
        LocalDate result = DateHelper.findDayBetween(2019, 7, 1, 2019, 7, 7, DayOfWeek.WEDNESDAY);
        assertNotNull(result);
        assertEquals(LocalDate.of(2019, 7, 3), result);
    }

    @Test
    void checkFindDayBetween3() {
        LocalDate result = DateHelper.findDayBetween(2019, 7, 1, 2019, 7, 7, DayOfWeek.FRIDAY);
        assertNotNull(result);
        assertEquals(LocalDate.of(2019, 7, 5), result);
    }

    @Test
    void checkFindDayBetween4() {
        LocalDate result = DateHelper.findDayBetween(2019, 7, 1, 2019, 7, 7, DayOfWeek.SATURDAY);
        assertNotNull(result);
        assertEquals(LocalDate.of(2019, 7, 6), result);
    }

    @Test
    void checkFindDayBetween5() {
        LocalDate result = DateHelper.findDayBetween(2022, 8, 25, 2022, 8, 28, DayOfWeek.TUESDAY);
        assertNull(result);
    }

    @Test
    void checkFindLastDay() {
        // Test finding last Monday of May 2017 (May 29, 2017)
        LocalDate result = DateHelper.findLastDay(2017, Month.MAY, DayOfWeek.MONDAY);
        assertEquals(LocalDate.of(2017, 5, 29), result);

        // Test finding last Friday of December 2017 (December 29, 2017)
        result = DateHelper.findLastDay(2017, Month.DECEMBER, DayOfWeek.FRIDAY);
        assertEquals(LocalDate.of(2017, 12, 29), result);

        // Test finding last Sunday of February 2020 (leap year - February 23, 2020)
        result = DateHelper.findLastDay(2020, 2, DayOfWeek.SUNDAY);
        assertEquals(LocalDate.of(2020, 2, 23), result);

        // Test finding last Thursday of November 2017 (Thanksgiving - November 30, 2017)
        result = DateHelper.findLastDay(2017, 11, DayOfWeek.THURSDAY);
        assertEquals(LocalDate.of(2017, 11, 30), result);
    }

    @Test
    void checkFindDayWithOccurrence() {
        // Test finding 1st Monday of January 2017 (January 2, 2017)
        LocalDate result = DateHelper.findDay(2017, Month.JANUARY, DayOfWeek.MONDAY, Occurrence.FIRST);
        assertEquals(LocalDate.of(2017, 1, 2), result);

        // Test finding 3rd Monday of January 2017 (January 16, 2017 - MLK Day)
        result = DateHelper.findDay(2017, Month.JANUARY, DayOfWeek.MONDAY, Occurrence.THIRD);
        assertEquals(LocalDate.of(2017, 1, 16), result);

        // Test finding 4th Thursday of November 2017 (Thanksgiving - November 23, 2017)
        result = DateHelper.findDay(2017, Month.NOVEMBER, DayOfWeek.THURSDAY, Occurrence.FOURTH);
        assertEquals(LocalDate.of(2017, 11, 23), result);

        // Test finding 2nd Sunday of May 2017 (Mother's Day - May 14, 2017)
        result = DateHelper.findDay(2017, Month.MAY, DayOfWeek.SUNDAY, Occurrence.SECOND);
        assertEquals(LocalDate.of(2017, 5, 14), result);

        // Test finding 5th Monday when it doesn't exist (should return null)
        result = DateHelper.findDay(2017, 2, DayOfWeek.MONDAY, 5);
        assertNull(result);
    }

    @Test
    void checkFindDayWithOccurrenceEdgeCases() {
        // Test invalid occurrence (0) - should throw exception
        assertThrows(IllegalArgumentException.class, () -> {
            DateHelper.findDay(2017, 1, DayOfWeek.MONDAY, 0);
        });

        // Test invalid occurrence (6) - should throw exception
        assertThrows(IllegalArgumentException.class, () -> {
            DateHelper.findDay(2017, 1, DayOfWeek.MONDAY, 6);
        });

        // Test 5th occurrence that exists (January 2017 has 5 Mondays)
        LocalDate result = DateHelper.findDay(2017, 1, DayOfWeek.MONDAY, 5);
        assertEquals(LocalDate.of(2017, 1, 30), result);

        // Test 5th occurrence that doesn't exist (February 2017 has only 4 Mondays)
        result = DateHelper.findDay(2017, 2, DayOfWeek.MONDAY, 5);
        assertNull(result);
    }

    @Test
    void checkFindDayBeforeWithMonthEnum() {
        // Test findDayBefore with Month enum
        LocalDate result = DateHelper.findDayBefore(2018, Month.MAY, 25, DayOfWeek.MONDAY);
        assertEquals(LocalDate.of(2018, 5, 21), result);

        result = DateHelper.findDayBefore(2018, Month.JANUARY, 9, DayOfWeek.MONDAY);
        assertEquals(LocalDate.of(2018, 1, 8), result);
    }

    @Test
    void checkFindDayWithMonthEnum() {
        // Test findDay with Month enum
        LocalDate result = DateHelper.findDay(2017, Month.JANUARY, 1, DayOfWeek.FRIDAY);
        assertEquals(LocalDate.of(2017, 1, 6), result);

        result = DateHelper.findDay(2022, Month.JANUARY, 1, DayOfWeek.MONDAY);
        assertEquals(LocalDate.of(2022, 1, 3), result);
    }
}
