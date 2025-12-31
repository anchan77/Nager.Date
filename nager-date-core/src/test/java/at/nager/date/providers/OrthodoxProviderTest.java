package at.nager.date.providers;

import at.nager.date.helpers.OrthodoxEasterSundayHelper;
import at.nager.date.providers.religious.OrthodoxProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test for Orthodox Provider
 */
public class OrthodoxProviderTest {

    @ParameterizedTest
    @CsvSource({
        "1800, 4, 20",
        "1810, 4, 29",
        "1820, 4, 9",
        "1830, 4, 18",
        "1840, 4, 26",
        "1850, 5, 5",
        "1860, 4, 15",
        "1870, 4, 24",
        "1880, 5, 2",
        "1890, 4, 13",
        "1900, 4, 22",
        "1910, 5, 1",
        "1920, 4, 11",
        "1930, 4, 20",
        "1940, 4, 28",
        "1950, 4, 9",
        "1960, 4, 17",
        "1970, 4, 26",
        "1980, 4, 6",
        "1990, 4, 15",
        "2001, 4, 15",
        "2002, 5, 5",
        "2003, 4, 27",
        "2004, 4, 11",
        "2005, 5, 1",
        "2006, 4, 23",
        "2007, 4, 8",
        "2008, 4, 27",
        "2009, 4, 19",
        "2010, 4, 4",
        "2011, 4, 24",
        "2012, 4, 15",
        "2013, 5, 5",
        "2014, 4, 20",
        "2015, 4, 12",
        "2016, 5, 1",
        "2017, 4, 16",
        "2018, 4, 8",
        "2019, 4, 28",
        "2020, 4, 19",
        "2021, 5, 2",
        "2022, 4, 24",
        "2023, 4, 16",
        "2024, 5, 5",
        "2025, 4, 20",
        "2026, 4, 12",
        "2027, 5, 2",
        "2028, 4, 16",
        "2029, 4, 8",
        "2030, 4, 28",
        "2050, 4, 17",
        "2075, 4, 7",
        "2090, 4, 23",
        "2091, 4, 8",
        "2092, 4, 27",
        "2093, 4, 19",
        "2094, 4, 11",
        "2095, 4, 24",
        "2096, 4, 15",
        "2097, 5, 5",
        "2098, 4, 27",
        "2099, 4, 12",
        "2100, 5, 2",
        "2101, 4, 24",
        "2102, 4, 9",
        "2110, 4, 13",
        "2120, 4, 21",
        "2130, 4, 30",
        "2140, 4, 10"
    })
    public void checkEasterSunday(int year, int month, int day) {
        OrthodoxProvider orthodoxProvider = new OrthodoxProvider();

        LocalDate easterSunday = orthodoxProvider.easterSunday(year);
        assertEquals(LocalDate.of(year, month, day), easterSunday);
    }

    @Test
    public void compareComplexWithSimpleCalculation() {
        OrthodoxProvider orthodoxProvider = new OrthodoxProvider();

        for (int year = 1583; year <= 3399; year++) {
            LocalDate easterSunday1 = OrthodoxEasterSundayHelper.calculateEasterSunday(year);
            LocalDate easterSunday2 = orthodoxProvider.easterSunday(year);

            assertEquals(easterSunday1, easterSunday2, "Failed for year " + year);
        }
    }
}
