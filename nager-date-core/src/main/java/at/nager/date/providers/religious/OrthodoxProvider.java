package at.nager.date.providers.religious;

import at.nager.date.models.HolidaySpecification;
import at.nager.date.models.HolidayTypes;
import at.nager.date.models.ObservedRuleSet;

import java.time.LocalDate;
import java.util.EnumSet;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Orthodox Provider
 */
public class OrthodoxProvider implements IOrthodoxProvider {

    private final ConcurrentHashMap<Integer, LocalDate> cache = new ConcurrentHashMap<>();

    @Override
    public LocalDate easterSunday(int year) {
        int daysToAddForGregorianCalendar = getDaysToAddForGregorianCalendar(year);

        return cache.computeIfAbsent(year, y -> {
            int a = y % 19;
            int b = y % 7;
            int c = y % 4;

            int d = (19 * a + 15) % 30;
            int e = (2 * c + 4 * b - d + 34) % 7;

            int month = (int) Math.floor((d + e + 114.0) / 31);
            int day = ((d + e + 114) % 31) + 1;

            return LocalDate.of(y, month, day).plusDays(daysToAddForGregorianCalendar);
        });
    }

    /**
     * Get the number of days to add to convert Julian calendar date to Gregorian calendar date
     * @param year the year
     * @return the number of days to add
     */
    private int getDaysToAddForGregorianCalendar(int year) {
        if (year >= 1583 && year <= 1699) {
            return 10;
        } else if (year >= 1700 && year <= 1799) {
            return 11;
        } else if (year >= 1800 && year <= 1899) {
            return 12;
        } else if (year >= 1900 && year <= 2099) {
            return 13;
        } else if (year >= 2100 && year <= 2199) {
            return 14;
        } else if (year >= 2200 && year <= 2299) {
            return 15;
        } else if (year >= 2300 && year <= 2499) {
            return 16;
        } else if (year >= 2500 && year <= 2599) {
            return 17;
        } else if (year >= 2600 && year <= 2699) {
            return 18;
        } else if (year >= 2700 && year <= 2899) {
            return 19;
        } else if (year >= 2900 && year <= 2999) {
            return 20;
        } else if (year >= 3000 && year <= 3099) {
            return 21;
        } else if (year >= 3100 && year <= 3299) {
            return 22;
        } else if (year >= 3300 && year <= 3399) {
            return 23;
        } else {
            return 0;
        }
    }

    @Override
    public HolidaySpecification goodFriday(String localName, int year, ObservedRuleSet observedRuleSet) {
        LocalDate easterSundayDate = this.easterSunday(year);

        HolidaySpecification spec = new HolidaySpecification();
        spec.setId("OGOODFRIDAY-01");
        spec.setDate(easterSundayDate.minusDays(2));
        spec.setEnglishName("Good Friday");
        spec.setLocalName(localName);
        spec.setHolidayTypes(EnumSet.of(HolidayTypes.PUBLIC));
        spec.setObservedRuleSet(observedRuleSet);
        return spec;
    }

    @Override
    public HolidaySpecification holySaturday(String localName, int year, ObservedRuleSet observedRuleSet) {
        LocalDate easterSundayDate = this.easterSunday(year);

        HolidaySpecification spec = new HolidaySpecification();
        spec.setId("OHOLYSATURDAY-01");
        spec.setDate(easterSundayDate.minusDays(1));
        spec.setEnglishName("Holy Saturday");
        spec.setLocalName(localName);
        spec.setHolidayTypes(EnumSet.of(HolidayTypes.PUBLIC));
        spec.setObservedRuleSet(observedRuleSet);
        return spec;
    }

    @Override
    public HolidaySpecification easterSundaySpecification(String localName, int year, ObservedRuleSet observedRuleSet) {
        LocalDate easterSundayDate = this.easterSunday(year);

        HolidaySpecification spec = new HolidaySpecification();
        spec.setId("OEASTERSUNDAY-01");
        spec.setDate(easterSundayDate);
        spec.setEnglishName("Easter Sunday");
        spec.setLocalName(localName);
        spec.setHolidayTypes(EnumSet.of(HolidayTypes.PUBLIC));
        spec.setObservedRuleSet(observedRuleSet);
        return spec;
    }

    @Override
    public HolidaySpecification easterMonday(String localName, int year, ObservedRuleSet observedRuleSet) {
        LocalDate easterSundayDate = this.easterSunday(year);

        HolidaySpecification spec = new HolidaySpecification();
        spec.setId("OEASTERMONDAY-01");
        spec.setDate(easterSundayDate.plusDays(1));
        spec.setEnglishName("Easter Monday");
        spec.setLocalName(localName);
        spec.setHolidayTypes(EnumSet.of(HolidayTypes.PUBLIC));
        spec.setObservedRuleSet(observedRuleSet);
        return spec;
    }

    @Override
    public HolidaySpecification pentecost(String localName, int year, ObservedRuleSet observedRuleSet) {
        LocalDate easterSundayDate = this.easterSunday(year);

        HolidaySpecification spec = new HolidaySpecification();
        spec.setId("OPENTECOST-01");
        spec.setDate(easterSundayDate.plusDays(49));
        spec.setEnglishName("Pentecost");
        spec.setLocalName(localName);
        spec.setHolidayTypes(EnumSet.of(HolidayTypes.PUBLIC));
        spec.setObservedRuleSet(observedRuleSet);
        return spec;
    }

    @Override
    public HolidaySpecification whitMonday(String localName, int year, ObservedRuleSet observedRuleSet) {
        LocalDate easterSundayDate = this.easterSunday(year);

        HolidaySpecification spec = new HolidaySpecification();
        spec.setId("OWHITMONDAY-01");
        spec.setDate(easterSundayDate.plusDays(50));
        spec.setEnglishName("Whit Monday");
        spec.setLocalName(localName);
        spec.setHolidayTypes(EnumSet.of(HolidayTypes.PUBLIC));
        spec.setObservedRuleSet(observedRuleSet);
        return spec;
    }
}
