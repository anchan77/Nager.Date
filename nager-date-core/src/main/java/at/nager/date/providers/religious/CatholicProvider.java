package at.nager.date.providers.religious;

import at.nager.date.models.HolidaySpecification;
import at.nager.date.models.HolidayTypes;
import at.nager.date.models.ObservedRuleSet;

import java.time.LocalDate;
import java.util.EnumSet;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Catholic Provider
 */
public class CatholicProvider implements ICatholicProvider {

    private final ConcurrentHashMap<Integer, LocalDate> cache = new ConcurrentHashMap<>();

    @Override
    public LocalDate easterSunday(int year) {
        return cache.computeIfAbsent(year, y -> {
            // Computus algorithm for calculating Easter Sunday
            // http://stackoverflow.com/questions/2510383/how-can-i-calculate-what-date-good-friday-falls-on-given-a-year

            int g = y % 19;
            int c = y / 100;
            int h = (c - c / 4 - (8 * c + 13) / 25 + 19 * g + 15) % 30;
            int i = h - h / 28 * (1 - h / 28 * (29 / (h + 1)) * ((21 - g) / 11));

            int day = i - (y + y / 4 + i + 2 - c + c / 4) % 7 + 28;
            int month = 3;

            if (day > 31) {
                month++;
                day -= 31;
            }

            return LocalDate.of(y, month, day);
        });
    }

    @Override
    public LocalDate adventSunday(int year) {
        LocalDate christmasDate = LocalDate.of(year, 12, 24);
        int daysToAdvent = 21 + christmasDate.getDayOfWeek().getValue();

        return christmasDate.minusDays(daysToAdvent);
    }

    @Override
    public HolidaySpecification maundyThursday(String localName, int year, ObservedRuleSet observedRuleSet) {
        LocalDate easterSundayDate = this.easterSunday(year);

        HolidaySpecification spec = new HolidaySpecification();
        spec.setId("CMAUNDYTHURSDAY-01");
        spec.setDate(easterSundayDate.minusDays(3));
        spec.setEnglishName("Maundy Thursday");
        spec.setLocalName(localName);
        spec.setHolidayTypes(EnumSet.of(HolidayTypes.PUBLIC));
        spec.setObservedRuleSet(observedRuleSet);
        return spec;
    }

    @Override
    public HolidaySpecification goodFriday(String localName, int year, ObservedRuleSet observedRuleSet) {
        LocalDate easterSundayDate = this.easterSunday(year);

        HolidaySpecification spec = new HolidaySpecification();
        spec.setId("CGOODFRIDAY-01");
        spec.setDate(easterSundayDate.minusDays(2));
        spec.setEnglishName("Good Friday");
        spec.setLocalName(localName);
        spec.setHolidayTypes(EnumSet.of(HolidayTypes.PUBLIC));
        spec.setObservedRuleSet(observedRuleSet);
        return spec;
    }

    @Override
    public HolidaySpecification easterSaturday(String localName, int year, ObservedRuleSet observedRuleSet) {
        LocalDate easterSundayDate = this.easterSunday(year);

        HolidaySpecification spec = new HolidaySpecification();
        spec.setId("CHOLYSATURDAY-01");
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
        spec.setId("CEASTERSUNDAY-01");
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
        spec.setId("CEASTERMONDAY-01");
        spec.setDate(easterSundayDate.plusDays(1));
        spec.setEnglishName("Easter Monday");
        spec.setLocalName(localName);
        spec.setHolidayTypes(EnumSet.of(HolidayTypes.PUBLIC));
        spec.setObservedRuleSet(observedRuleSet);
        return spec;
    }

    @Override
    public HolidaySpecification ascensionDay(String localName, int year, ObservedRuleSet observedRuleSet) {
        LocalDate easterSundayDate = this.easterSunday(year);

        HolidaySpecification spec = new HolidaySpecification();
        spec.setId("CASCENSIONDAY-01");
        spec.setDate(easterSundayDate.plusDays(39));
        spec.setEnglishName("Ascension Day");
        spec.setLocalName(localName);
        spec.setHolidayTypes(EnumSet.of(HolidayTypes.PUBLIC));
        spec.setObservedRuleSet(observedRuleSet);
        return spec;
    }

    @Override
    public HolidaySpecification pentecost(String localName, int year, ObservedRuleSet observedRuleSet) {
        LocalDate easterSundayDate = this.easterSunday(year);

        HolidaySpecification spec = new HolidaySpecification();
        spec.setId("CPENTECOST-01");
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
        spec.setId("CWHITMONDAY-01");
        spec.setDate(easterSundayDate.plusDays(50));
        spec.setEnglishName("Whit Monday");
        spec.setLocalName(localName);
        spec.setHolidayTypes(EnumSet.of(HolidayTypes.PUBLIC));
        spec.setObservedRuleSet(observedRuleSet);
        return spec;
    }

    @Override
    public HolidaySpecification corpusChristi(String localName, int year, ObservedRuleSet observedRuleSet) {
        LocalDate easterSundayDate = this.easterSunday(year);

        HolidaySpecification spec = new HolidaySpecification();
        spec.setId("CCORPUSCHRISTI-01");
        spec.setDate(easterSundayDate.plusDays(60));
        spec.setEnglishName("Corpus Christi");
        spec.setLocalName(localName);
        spec.setHolidayTypes(EnumSet.of(HolidayTypes.PUBLIC));
        spec.setObservedRuleSet(observedRuleSet);
        return spec;
    }
}
