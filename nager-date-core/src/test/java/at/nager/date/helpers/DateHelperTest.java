package at.nager.date.helpers;

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
}
