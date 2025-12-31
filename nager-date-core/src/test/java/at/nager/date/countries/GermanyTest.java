package at.nager.date.countries;

import at.nager.date.CountryCode;
import at.nager.date.HolidaySystem;
import at.nager.date.WeekendSystem;
import at.nager.date.models.Holiday;
import at.nager.date.providers.religious.CatholicProvider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for Germany Holiday Provider
 */
class GermanyTest {

    @BeforeAll
    static void setUpLicense() {
        // Set a valid test license key for testing
        HolidaySystem.setLicenseKey("dGVzdC1saWNlbnNlLWtleS1mb3ItdGVzdGluZy1wdXJwb3Nlcw==");
    }

    @Test
    void testGermanyCorpusChristi() {
        int yearToTest = 2017;

        CatholicProvider catholicProvider = new CatholicProvider();
        List<Holiday> publicHolidays = HolidaySystem.getHolidays(yearToTest, CountryCode.DE);
        LocalDate easterSunday = catholicProvider.easterSunday(yearToTest);
        Holiday corpusChristi = publicHolidays.stream()
                .filter(x -> x.getLocalName().equals("Fronleichnam"))
                .findFirst()
                .orElseThrow();
        assertEquals(easterSunday.plusDays(60), corpusChristi.getDate());
    }

    @Test
    void testGermanyCorpusChristi2017() {
        int yearToTest = 2017;

        List<Holiday> publicHolidays = HolidaySystem.getHolidays(yearToTest, CountryCode.DE);
        Holiday corpusChristi = publicHolidays.stream()
                .filter(x -> x.getLocalName().equals("Fronleichnam"))
                .findFirst()
                .orElseThrow();
        LocalDate expectedDate = LocalDate.of(yearToTest, 6, 15);
        assertEquals(expectedDate, corpusChristi.getDate());
    }

    @Test
    void testGermanyCorpusChristi2026() {
        int yearToTest = 2026;

        List<Holiday> publicHolidays = HolidaySystem.getHolidays(yearToTest, CountryCode.DE);
        Holiday corpusChristi = publicHolidays.stream()
                .filter(x -> x.getLocalName().equals("Fronleichnam"))
                .findFirst()
                .orElseThrow();
        LocalDate expectedDate = LocalDate.of(yearToTest, 6, 4);
        assertEquals(expectedDate, corpusChristi.getDate());
    }

    @ParameterizedTest
    @CsvSource({
        "2019, 2021, 2020",
        "2024, 2026, 2025"
    })
    void testGermanyLiberationDay(int startYear, int endYear, int expectedYear) {
        List<Holiday> publicHolidays = HolidaySystem.getHolidays(
                LocalDate.of(startYear, 5, 8),
                LocalDate.of(endYear, 5, 8),
                CountryCode.DE);

        List<Holiday> liberationDays = publicHolidays.stream()
                .filter(x -> x.getLocalName().equals("Tag der Befreiung"))
                .collect(Collectors.toList());
        Holiday liberationDay = liberationDays.stream().findFirst().orElse(null);

        assertEquals(1, liberationDays.size());
        assertNotNull(liberationDay);
        assertEquals(LocalDate.of(expectedYear, 5, 8), liberationDay.getDate());
        assertNotNull(liberationDay.getSubdivisionCodes());
        assertEquals(1, liberationDay.getSubdivisionCodes().length);
        assertEquals("DE-BE", liberationDay.getSubdivisionCodes()[0]);
    }

    @Test
    void testGermanyIsOfficialPublicHolidayByCountyWithCountySpecificEpiphany2017() {
        boolean isPublicHolidayInBW = HolidaySystem.isPublicHoliday(LocalDate.of(2017, 1, 6), CountryCode.DE, "DE-BW");
        boolean isPublicHolidayInNW = HolidaySystem.isPublicHoliday(LocalDate.of(2017, 1, 6), CountryCode.DE, "DE-NW");

        assertTrue(isPublicHolidayInBW);
        assertFalse(isPublicHolidayInNW);
    }

    @Test
    void testGermanyIsOfficialPublicHolidayByCountyWithGlobalChristmasDay2017() {
        boolean isPublicHolidayInBW = HolidaySystem.isPublicHoliday(LocalDate.of(2017, 12, 25), CountryCode.DE, "DE-BW");

        assertTrue(isPublicHolidayInBW);
    }

    @Test
    void testGermanyIsOfficialPublicHolidayByCountyWithCountySpecificWorldChildrensDay() {
        boolean isPublicHolidayInTH2018 = HolidaySystem.isPublicHoliday(LocalDate.of(2018, 9, 20), CountryCode.DE, "DE-TH");
        boolean isPublicHolidayInTH2019 = HolidaySystem.isPublicHoliday(LocalDate.of(2019, 9, 20), CountryCode.DE, "DE-TH");
        boolean isPublicHolidayInTH2020 = HolidaySystem.isPublicHoliday(LocalDate.of(2020, 9, 20), CountryCode.DE, "DE-TH");

        assertFalse(isPublicHolidayInTH2018);
        assertTrue(isPublicHolidayInTH2019);
        assertTrue(isPublicHolidayInTH2020);
    }

    @Test
    void testGermanyIsOfficialPublicHolidayByCountyLiberationDay() {
        String countyCodeBerlin = "DE-BE";

        boolean isPublicHolidayInBerlin2019 = HolidaySystem.isPublicHoliday(LocalDate.of(2019, 5, 8), CountryCode.DE, countyCodeBerlin);
        boolean isPublicHolidayInBerlin2020 = HolidaySystem.isPublicHoliday(LocalDate.of(2020, 5, 8), CountryCode.DE, countyCodeBerlin);
        boolean isPublicHolidayInBerlin2021 = HolidaySystem.isPublicHoliday(LocalDate.of(2021, 5, 8), CountryCode.DE, countyCodeBerlin);

        assertFalse(isPublicHolidayInBerlin2019);
        assertTrue(isPublicHolidayInBerlin2020);
        assertFalse(isPublicHolidayInBerlin2021);
    }

    @ParameterizedTest
    @CsvSource({
        "2018, 10, 8, false",
        "2018, 10, 9, false",
        "2018, 10, 10, false",
        "2018, 10, 11, false",
        "2018, 10, 12, false",
        "2018, 10, 13, true",
        "2018, 10, 14, true"
    })
    void checksThatUniversalWeekendIsUsed(int year, int month, int day, boolean expectedIsWeekend) {
        LocalDate date = LocalDate.of(year, month, day);
        boolean isWeekend = WeekendSystem.isWeekend(date, CountryCode.DE);
        assertEquals(expectedIsWeekend, isWeekend);
    }
}
