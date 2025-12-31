package at.nager.date.providers.holiday;

import at.nager.date.CountryCode;
import at.nager.date.helpers.CollectionHelper;
import at.nager.date.models.HolidaySpecification;
import at.nager.date.models.HolidayTypes;
import at.nager.date.providers.AbstractHolidayProvider;
import at.nager.date.providers.ISubdivisionCodesProvider;
import at.nager.date.providers.religious.ICatholicProvider;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.EnumSet;

/**
 * Germany Holiday Provider
 */
public final class GermanyHolidayProvider extends AbstractHolidayProvider implements ISubdivisionCodesProvider {

    private final ICatholicProvider catholicProvider;

    /**
     * Creates a new Germany holiday provider
     *
     * @param catholicProvider the Catholic provider for Easter-based holidays
     */
    public GermanyHolidayProvider(ICatholicProvider catholicProvider) {
        super(CountryCode.DE);
        this.catholicProvider = catholicProvider;
    }

    @Override
    public Map<String, String> getSubdivisionCodes() {
        Map<String, String> subdivisionCodes = new HashMap<>();
        subdivisionCodes.put("DE-BW", "Baden-Württemberg");
        subdivisionCodes.put("DE-BY", "Bayern");
        subdivisionCodes.put("DE-BE", "Berlin");
        subdivisionCodes.put("DE-BB", "Brandenburg");
        subdivisionCodes.put("DE-HB", "Bremen");
        subdivisionCodes.put("DE-HH", "Hamburg");
        subdivisionCodes.put("DE-HE", "Hessen");
        subdivisionCodes.put("DE-MV", "Mecklenburg-Vorpommern");
        subdivisionCodes.put("DE-NI", "Niedersachsen");
        subdivisionCodes.put("DE-NW", "Nordrhein-Westfalen");
        subdivisionCodes.put("DE-RP", "Rheinland-Pfalz");
        subdivisionCodes.put("DE-SL", "Saarland");
        subdivisionCodes.put("DE-SN", "Sachsen");
        subdivisionCodes.put("DE-ST", "Sachsen-Anhalt");
        subdivisionCodes.put("DE-SH", "Schleswig-Holstein");
        subdivisionCodes.put("DE-TH", "Thüringen");
        return subdivisionCodes;
    }

    protected List<HolidaySpecification> getHolidaySpecifications(int year) {
        List<HolidaySpecification> holidaySpecifications = new ArrayList<>();

        // Fixed holidays
        HolidaySpecification newYear = new HolidaySpecification();
        newYear.setId("NEWYEARSDAY-01");
        newYear.setDate(LocalDate.of(year, 1, 1));
        newYear.setEnglishName("New Year's Day");
        newYear.setLocalName("Neujahr");
        newYear.setHolidayTypes(EnumSet.of(HolidayTypes.PUBLIC));
        holidaySpecifications.add(newYear);

        HolidaySpecification epiphany = new HolidaySpecification();
        epiphany.setId("EPIPHANY-01");
        epiphany.setDate(LocalDate.of(year, 1, 6));
        epiphany.setEnglishName("Epiphany");
        epiphany.setLocalName("Heilige Drei Könige");
        epiphany.setHolidayTypes(EnumSet.of(HolidayTypes.PUBLIC));
        epiphany.setSubdivisionCodes(new String[]{"DE-BW", "DE-BY", "DE-ST"});
        holidaySpecifications.add(epiphany);

        HolidaySpecification labourDay = new HolidaySpecification();
        labourDay.setId("LABOURDAY-01");
        labourDay.setDate(LocalDate.of(year, 5, 1));
        labourDay.setEnglishName("Labour Day");
        labourDay.setLocalName("Tag der Arbeit");
        labourDay.setHolidayTypes(EnumSet.of(HolidayTypes.PUBLIC));
        holidaySpecifications.add(labourDay);

        HolidaySpecification assumptionDay = new HolidaySpecification();
        assumptionDay.setId("ASSUMPTIONDAY-01");
        assumptionDay.setDate(LocalDate.of(year, 8, 15));
        assumptionDay.setEnglishName("Assumption Day");
        assumptionDay.setLocalName("Mariä Himmelfahrt");
        assumptionDay.setHolidayTypes(EnumSet.of(HolidayTypes.PUBLIC));
        assumptionDay.setSubdivisionCodes(new String[]{"DE-SL"});
        holidaySpecifications.add(assumptionDay);

        HolidaySpecification germanUnityDay = new HolidaySpecification();
        germanUnityDay.setId("GERMANUNITYDAY-01");
        germanUnityDay.setDate(LocalDate.of(year, 10, 3));
        germanUnityDay.setEnglishName("German Unity Day");
        germanUnityDay.setLocalName("Tag der Deutschen Einheit");
        germanUnityDay.setHolidayTypes(EnumSet.of(HolidayTypes.PUBLIC));
        holidaySpecifications.add(germanUnityDay);

        HolidaySpecification allSaintsDay = new HolidaySpecification();
        allSaintsDay.setId("ALLSAINTSDAY-01");
        allSaintsDay.setDate(LocalDate.of(year, 11, 1));
        allSaintsDay.setEnglishName("All Saints' Day");
        allSaintsDay.setLocalName("Allerheiligen");
        allSaintsDay.setHolidayTypes(EnumSet.of(HolidayTypes.PUBLIC));
        allSaintsDay.setSubdivisionCodes(new String[]{"DE-BW", "DE-BY", "DE-NW", "DE-RP", "DE-SL"});
        holidaySpecifications.add(allSaintsDay);

        HolidaySpecification christmasDay = new HolidaySpecification();
        christmasDay.setId("CHRISTMASDAY-01");
        christmasDay.setDate(LocalDate.of(year, 12, 25));
        christmasDay.setEnglishName("Christmas Day");
        christmasDay.setLocalName("Erster Weihnachtstag");
        christmasDay.setHolidayTypes(EnumSet.of(HolidayTypes.PUBLIC));
        holidaySpecifications.add(christmasDay);

        HolidaySpecification stStephensDay = new HolidaySpecification();
        stStephensDay.setId("STSTEPHENSDAY-01");
        stStephensDay.setDate(LocalDate.of(year, 12, 26));
        stStephensDay.setEnglishName("St. Stephen's Day");
        stStephensDay.setLocalName("Zweiter Weihnachtstag");
        stStephensDay.setHolidayTypes(EnumSet.of(HolidayTypes.PUBLIC));
        holidaySpecifications.add(stStephensDay);

        // Easter-based holidays
        holidaySpecifications.add(this.catholicProvider.goodFriday("Karfreitag", year));
        holidaySpecifications.add(this.catholicProvider.easterSundaySpecification("Ostersonntag", year)
                .withSubdivisionCodes("DE-BB"));
        holidaySpecifications.add(this.catholicProvider.easterMonday("Ostermontag", year));
        holidaySpecifications.add(this.catholicProvider.ascensionDay("Christi Himmelfahrt", year));
        holidaySpecifications.add(this.catholicProvider.pentecost("Pfingstsonntag", year)
                .withSubdivisionCodes("DE-BB"));
        holidaySpecifications.add(this.catholicProvider.whitMonday("Pfingstmontag", year));
        holidaySpecifications.add(this.catholicProvider.corpusChristi("Fronleichnam", year)
                .withSubdivisionCodes("DE-BW", "DE-BY", "DE-HE", "DE-NW", "DE-RP", "DE-SL"));

        // Year-conditional holidays
        CollectionHelper.addIfNotNull(holidaySpecifications, this.internationalWomensDay(year));
        CollectionHelper.addIfNotNull(holidaySpecifications, this.prayerDay(year));
        CollectionHelper.addIfNotNull(holidaySpecifications, this.liberationDay(year));
        holidaySpecifications.add(this.reformationDay(year));
        CollectionHelper.addIfNotNull(holidaySpecifications, this.worldChildrensDay(year));

        return holidaySpecifications;
    }

    private HolidaySpecification worldChildrensDay(int year) {
        if (year >= 2019) {
            HolidaySpecification spec = new HolidaySpecification();
            spec.setId("WORLDCHILDRENSDAY-01");
            spec.setDate(LocalDate.of(year, 9, 20));
            spec.setEnglishName("World Children's Day");
            spec.setLocalName("Weltkindertag");
            spec.setHolidayTypes(EnumSet.of(HolidayTypes.PUBLIC));
            spec.setSubdivisionCodes(new String[]{"DE-TH"});
            return spec;
        }
        return null;
    }

    private HolidaySpecification internationalWomensDay(int year) {
        String id = "INTERNATIONALWOMENSDAY-01";
        String localName = "Internationaler Frauentag";
        String englishName = "International Women's Day";

        if (year >= 2019 && year <= 2022) {
            HolidaySpecification spec = new HolidaySpecification();
            spec.setId(id);
            spec.setDate(LocalDate.of(year, 3, 8));
            spec.setEnglishName(englishName);
            spec.setLocalName(localName);
            spec.setHolidayTypes(EnumSet.of(HolidayTypes.PUBLIC));
            spec.setSubdivisionCodes(new String[]{"DE-BE"});
            return spec;
        }

        if (year >= 2023) {
            HolidaySpecification spec = new HolidaySpecification();
            spec.setId(id);
            spec.setDate(LocalDate.of(year, 3, 8));
            spec.setEnglishName(englishName);
            spec.setLocalName(localName);
            spec.setHolidayTypes(EnumSet.of(HolidayTypes.PUBLIC));
            spec.setSubdivisionCodes(new String[]{"DE-BE", "DE-MV"});
            return spec;
        }

        return null;
    }

    private HolidaySpecification reformationDay(int year) {
        String id = "REFORMATIONDAY-01";
        String localName = "Reformationstag";
        String englishName = "Reformation Day";

        if (year == 2017) {
            // In commemoration of the 500th anniversary of the beginning of the Reformation,
            // it was unique as a whole German holiday
            HolidaySpecification spec = new HolidaySpecification();
            spec.setId(id);
            spec.setDate(LocalDate.of(year, 10, 31));
            spec.setEnglishName(englishName);
            spec.setLocalName(localName);
            spec.setHolidayTypes(EnumSet.of(HolidayTypes.PUBLIC));
            return spec;
        }

        List<String> subdivisionCodes = new ArrayList<>();
        subdivisionCodes.add("DE-BB");
        subdivisionCodes.add("DE-MV");
        subdivisionCodes.add("DE-SN");
        subdivisionCodes.add("DE-ST");
        subdivisionCodes.add("DE-TH");

        if (year >= 2018) {
            subdivisionCodes.add("DE-HB");
            subdivisionCodes.add("DE-HH");
            subdivisionCodes.add("DE-NI");
            subdivisionCodes.add("DE-SH");
        }

        HolidaySpecification spec = new HolidaySpecification();
        spec.setId(id);
        spec.setDate(LocalDate.of(year, 10, 31));
        spec.setEnglishName(englishName);
        spec.setLocalName(localName);
        spec.setHolidayTypes(EnumSet.of(HolidayTypes.PUBLIC));
        spec.setSubdivisionCodes(subdivisionCodes.toArray(new String[0]));
        return spec;
    }

    private HolidaySpecification prayerDay(int year) {
        LocalDate dayOfPrayer = this.catholicProvider.adventSunday(year).minusDays(11);
        String id = "REPENTANCEANDPRAYERDAY-01";
        String localName = "Buß- und Bettag";
        String englishName = "Repentance and Prayer Day";

        if (year >= 1934 && year < 1939) {
            HolidaySpecification spec = new HolidaySpecification();
            spec.setId(id);
            spec.setDate(dayOfPrayer);
            spec.setEnglishName(englishName);
            spec.setLocalName(localName);
            spec.setHolidayTypes(EnumSet.of(HolidayTypes.PUBLIC));
            return spec;
        } else if (year >= 1945 && year <= 1980) {
            HolidaySpecification spec = new HolidaySpecification();
            spec.setId(id);
            spec.setDate(dayOfPrayer);
            spec.setEnglishName(englishName);
            spec.setLocalName(localName);
            spec.setHolidayTypes(EnumSet.of(HolidayTypes.PUBLIC));
            spec.setSubdivisionCodes(new String[]{
                "DE-BW", "DE-BE", "DE-HB", "DE-HH", "DE-HE",
                "DE-NI", "DE-NW", "DE-RP", "DE-SL", "DE-SH"
            });
            return spec;
        } else if (year >= 1981 && year <= 1989) {
            HolidaySpecification spec = new HolidaySpecification();
            spec.setId(id);
            spec.setDate(dayOfPrayer);
            spec.setEnglishName(englishName);
            spec.setLocalName(localName);
            spec.setHolidayTypes(EnumSet.of(HolidayTypes.PUBLIC));
            spec.setSubdivisionCodes(new String[]{
                "DE-BW", "DE-BY", "DE-BE", "DE-HB", "DE-HH", "DE-HE",
                "DE-NI", "DE-NW", "DE-RP", "DE-SL", "DE-SH"
            });
            return spec;
        } else if (year >= 1990 && year <= 1994) {
            HolidaySpecification spec = new HolidaySpecification();
            spec.setId(id);
            spec.setDate(dayOfPrayer);
            spec.setEnglishName(englishName);
            spec.setLocalName(localName);
            spec.setHolidayTypes(EnumSet.of(HolidayTypes.PUBLIC));
            return spec;
        } else if (year >= 1995) {
            HolidaySpecification spec = new HolidaySpecification();
            spec.setId(id);
            spec.setDate(dayOfPrayer);
            spec.setEnglishName(englishName);
            spec.setLocalName(localName);
            spec.setHolidayTypes(EnumSet.of(HolidayTypes.PUBLIC));
            spec.setSubdivisionCodes(new String[]{"DE-SN"});
            return spec;
        }

        return null;
    }

    private HolidaySpecification liberationDay(int year) {
        if (year == 2020 || year == 2025) {
            HolidaySpecification spec = new HolidaySpecification();
            spec.setId("LIBERATIONDAY-01");
            spec.setDate(LocalDate.of(year, 5, 8));
            spec.setEnglishName("Liberation Day");
            spec.setLocalName("Tag der Befreiung");
            spec.setHolidayTypes(EnumSet.of(HolidayTypes.PUBLIC));
            spec.setSubdivisionCodes(new String[]{"DE-BE"});
            return spec;
        }
        return null;
    }

    @Override
    public List<String> getSources() {
        List<String> sources = new ArrayList<>();
        sources.add("https://de.wikipedia.org/wiki/Gesetzliche_Feiertage_in_Deutschland");
        sources.add("https://pardok.parlament-berlin.de/starweb/adis/citat/VT/19/gvbl/g24280460.pdf");
        return sources;
    }
}
