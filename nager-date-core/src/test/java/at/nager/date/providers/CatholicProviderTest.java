package at.nager.date.providers;

import at.nager.date.providers.religious.CatholicProvider;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test for Catholic Provider
 */
public class CatholicProviderTest {

    @ParameterizedTest
    @CsvSource({
        "1800, 4, 13",
        "1810, 4, 22",
        "1820, 4, 2",
        "1830, 4, 11",
        "1840, 4, 19",
        "1850, 3, 31",
        "1860, 4, 8",
        "1870, 4, 17",
        "1880, 3, 28",
        "1890, 4, 6",
        "1900, 4, 15",
        "1910, 3, 27",
        "1920, 4, 4",
        "1930, 4, 20",
        "1940, 3, 24",
        "1950, 4, 9",
        "1960, 4, 17",
        "1970, 3, 29",
        "1980, 4, 6",
        "1990, 4, 15",
        "2000, 4, 23",
        "2001, 4, 15",
        "2002, 3, 31",
        "2003, 4, 20",
        "2004, 4, 11",
        "2005, 3, 27",
        "2006, 4, 16",
        "2007, 4, 8",
        "2008, 3, 23",
        "2009, 4, 12",
        "2010, 4, 4",
        "2011, 4, 24",
        "2012, 4, 8",
        "2013, 3, 31",
        "2014, 4, 20",
        "2015, 4, 5",
        "2016, 3, 27",
        "2017, 4, 16",
        "2018, 4, 1",
        "2019, 4, 21",
        "2020, 4, 12",
        "2021, 4, 4",
        "2022, 4, 17",
        "2023, 4, 9",
        "2024, 3, 31",
        "2025, 4, 20",
        "2026, 4, 5",
        "2027, 3, 28",
        "2028, 4, 16",
        "2029, 4, 1",
        "2030, 4, 21",
        "2040, 4, 1",
        "2050, 4, 10",
        "2060, 4, 18",
        "2070, 3, 30",
        "2080, 4, 7",
        "2090, 4, 16",
        "2100, 3, 28",
        "2110, 4, 6",
        "2200, 4, 6"
    })
    public void checkEasterSunday(int year, int month, int day) {
        CatholicProvider catholicProvider = new CatholicProvider();

        LocalDate easterSunday = catholicProvider.easterSunday(year);
        assertEquals(LocalDate.of(year, month, day), easterSunday);
    }
}
