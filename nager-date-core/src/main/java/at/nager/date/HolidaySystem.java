package at.nager.date;

import at.nager.date.helpers.CountryCodeHelper;
import at.nager.date.helpers.LicenseHelper;
import at.nager.date.models.Holiday;
import at.nager.date.models.HolidayTypes;
import at.nager.date.models.LicenseCheckStatus;
import at.nager.date.models.LicenseInfo;
import at.nager.date.providers.IHolidayProvider;
import at.nager.date.providers.ISubdivisionCodesProvider;
import at.nager.date.providers.NoHolidaysHolidayProvider;
import at.nager.date.providers.holiday.GermanyHolidayProvider;
import at.nager.date.providers.religious.CatholicProvider;
import at.nager.date.providers.religious.ICatholicProvider;
import at.nager.date.providers.religious.IOrthodoxProvider;
import at.nager.date.providers.religious.OrthodoxProvider;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Holiday System
 * <p>
 * Static facade providing the primary API for querying holidays and managing holiday providers.
 * Supports license-based access control and lazy provider initialization.
 * </p>
 * <p>
 * Design Decision #5 (Provider Registry: Lazy Initialization Mechanism):
 * This implementation uses ConcurrentHashMap with computeIfAbsent for atomic lazy initialization.
 * This approach provides thread-safe lazy loading without explicit locking, leveraging Java's
 * built-in concurrency primitives for clean and efficient initialization.
 * </p>
 */
public final class HolidaySystem {

    private static final String COUNTRY_CODE_PARSING_ERROR = "Country code %s is not valid according to ISO 3166-1 ALPHA-2";

    // Singleton religious providers - shared across all country providers
    private static final ICatholicProvider catholicProvider = new CatholicProvider();
    private static final IOrthodoxProvider orthodoxProvider = new OrthodoxProvider();

    // Provider registry with lazy initialization
    // Using ConcurrentHashMap for thread-safe lazy initialization via computeIfAbsent
    private static final Map<CountryCode, Supplier<IHolidayProvider>> holidayProviders;
    private static final Map<CountryCode, IHolidayProvider> providerCache = new ConcurrentHashMap<>();

    // License management fields
    private static String licenseKey = null;
    private static LicenseCheckStatus licenseCheckStatus = LicenseCheckStatus.NOT_CHECKED;

    // Static initializer to populate the provider registry
    static {
        Map<CountryCode, Supplier<IHolidayProvider>> providers = new HashMap<>();

        // For Milestone 1, only Germany is registered
        // GermanyHolidayProvider is a placeholder stub that will be fully implemented in Task 8
        // Remaining countries will be added in Milestones 2-4
        providers.put(CountryCode.DE, () -> new GermanyHolidayProvider(catholicProvider));

        // TODO: Add remaining country providers in future milestones:
        // providers.put(CountryCode.AD, () -> new AndorraHolidayProvider(catholicProvider));
        // providers.put(CountryCode.AL, () -> new AlbaniaHolidayProvider(catholicProvider, orthodoxProvider));
        // providers.put(CountryCode.AM, () -> new ArmeniaHolidayProvider(catholicProvider));
        // ... (continue for all other countries as per C# implementation)

        holidayProviders = Collections.unmodifiableMap(providers);
    }

    /**
     * Private constructor to prevent instantiation
     */
    private HolidaySystem() {
        throw new UnsupportedOperationException("Static utility class cannot be instantiated");
    }

    /**
     * Set the License Key
     * <p>
     * As a GitHub sponsor of nager, you will receive a license key.
     * See: https://github.com/sponsors/nager
     * </p>
     *
     * @param key the license key to set
     */
    public static void setLicenseKey(String key) {
        licenseKey = key;
        licenseCheckStatus = LicenseCheckStatus.NOT_CHECKED;
    }

    /**
     * Check and validate the license key
     * <p>
     * Performs license validation and updates the license check status.
     * Called automatically on first provider access.
     * </p>
     *
     * @param key the license key to validate
     */
    private static void checkLicense(String key) {
        if (key == null || key.isEmpty()) {
            licenseCheckStatus = LicenseCheckStatus.NOT_CONFIGURED;
            return;
        }

        LicenseInfo licenseInfo = LicenseHelper.checkLicenseKey(key);
        if (licenseInfo == null) {
            licenseCheckStatus = LicenseCheckStatus.INVALID;
            return;
        }

        if (licenseInfo.getValidUntil().isBefore(LocalDate.now())) {
            licenseCheckStatus = LicenseCheckStatus.EXPIRED;
            return;
        }

        licenseCheckStatus = LicenseCheckStatus.VALID;
    }

    /**
     * Get the holiday provider for the specified country
     *
     * @param countryCode Country Code as string (ISO 3166-1 ALPHA-2)
     * @return Holiday provider for given country
     * @throws IllegalArgumentException if the country code is not recognized as valid
     */
    public static IHolidayProvider getHolidayProvider(String countryCode) {
        Optional<CountryCode> parsedCountryCode = CountryCodeHelper.tryParseCountryCode(countryCode);
        if (!parsedCountryCode.isPresent()) {
            throw new IllegalArgumentException(String.format(COUNTRY_CODE_PARSING_ERROR, countryCode));
        }

        return getHolidayProvider(parsedCountryCode.get());
    }

    /**
     * Get the holiday provider for the specified country
     *
     * @param countryCode Country Code (ISO 3166-1 ALPHA-2)
     * @return Holiday provider for given country
     */
    public static IHolidayProvider getHolidayProvider(CountryCode countryCode) {
        return tryGetHolidayProvider(countryCode)
                .orElse(NoHolidaysHolidayProvider.getInstance());
    }

    /**
     * Try to get the holiday provider for the specified country
     * <p>
     * Performs license validation on first call. Throws LicenseKeyException
     * if license is invalid, expired, or not configured.
     * </p>
     *
     * @param countryCode Country Code (ISO 3166-1 ALPHA-2)
     * @return Optional containing the provider if available, empty Optional otherwise
     * @throws LicenseKeyException if the license key is invalid, expired, or missing
     */
    public static Optional<IHolidayProvider> tryGetHolidayProvider(CountryCode countryCode) {
        // Check license on first access
        if (licenseCheckStatus == LicenseCheckStatus.NOT_CHECKED) {
            checkLicense(licenseKey);
        }

        // Validate license status
        switch (licenseCheckStatus) {
            case VALID:
                break;
            case NOT_CONFIGURED:
                throw new LicenseKeyException("No LicenseKey");
            case INVALID:
                throw new LicenseKeyException("Invalid LicenseKey");
            case EXPIRED:
                throw new LicenseKeyException("Expired LicenseKey");
            default:
                throw new LicenseKeyException("Unknown LicenseKey Check Status");
        }

        // Get provider from registry with lazy initialization
        Supplier<IHolidayProvider> providerSupplier = holidayProviders.get(countryCode);
        if (providerSupplier == null) {
            return Optional.empty();
        }

        // Use computeIfAbsent for thread-safe lazy initialization
        IHolidayProvider provider = providerCache.computeIfAbsent(countryCode, k -> providerSupplier.get());
        return Optional.of(provider);
    }

    // ===== Holidays for a given year =====

    /**
     * Get holidays of a given year
     *
     * @param year        The year
     * @param countryCode Country Code as string (ISO 3166-1 ALPHA-2)
     * @return List of holidays for given country and year
     * @throws IllegalArgumentException if the country code is not recognized as valid
     */
    public static List<Holiday> getHolidays(int year, String countryCode) {
        Optional<CountryCode> parsedCountryCode = CountryCodeHelper.tryParseCountryCode(countryCode);
        if (!parsedCountryCode.isPresent()) {
            throw new IllegalArgumentException(String.format(COUNTRY_CODE_PARSING_ERROR, countryCode));
        }

        return getHolidays(year, parsedCountryCode.get());
    }

    /**
     * Get holidays of a given year
     *
     * @param year        The year
     * @param countryCode Country Code (ISO 3166-1 ALPHA-2)
     * @return List of holidays for given country and year
     */
    public static List<Holiday> getHolidays(int year, CountryCode countryCode) {
        IHolidayProvider provider = getHolidayProvider(countryCode);
        return provider.getHolidays(year);
    }

    // ===== Holidays for a date range =====

    /**
     * Get holidays of a given date range for the specified country
     *
     * @param startDate   The start date of the range
     * @param endDate     The end date of the range
     * @param countryCode The country code as string (ISO 3166-1 ALPHA-2)
     * @return List of holidays for the specified country and date range
     * @throws IllegalArgumentException if the country code is not recognized as valid
     */
    public static List<Holiday> getHolidays(LocalDate startDate, LocalDate endDate, String countryCode) {
        Optional<CountryCode> parsedCountryCode = CountryCodeHelper.tryParseCountryCode(countryCode);
        if (!parsedCountryCode.isPresent()) {
            throw new IllegalArgumentException(String.format(COUNTRY_CODE_PARSING_ERROR, countryCode));
        }

        return getHolidays(startDate, endDate, parsedCountryCode.get());
    }

    /**
     * Get holidays of a given date range for the specified country
     *
     * @param startDate   The start date of the range
     * @param endDate     The end date of the range
     * @param countryCode The country code (ISO 3166-1 ALPHA-2)
     * @return List of holidays for the specified country and date range
     * @throws IllegalArgumentException if end date is before start date
     */
    public static List<Holiday> getHolidays(LocalDate startDate, LocalDate endDate, CountryCode countryCode) {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("endDate is before startDate");
        }

        List<Holiday> holidays = new ArrayList<>();
        int currentYear = startDate.getYear();
        int endYear = endDate.getYear();

        while (currentYear <= endYear) {
            List<Holiday> yearHolidays = getHolidays(currentYear, countryCode);
            for (Holiday holiday : yearHolidays) {
                LocalDate holidayDate = holiday.getDate();
                if (!holidayDate.isBefore(startDate) && !holidayDate.isAfter(endDate)) {
                    holidays.add(holiday);
                }
            }
            currentYear++;
        }

        return holidays;
    }

    /**
     * Get Worldwide holidays of a given date range
     * <p>
     * Returns holidays from all supported countries within the date range.
     * </p>
     *
     * @param startDate The start date
     * @param endDate   The end date
     * @return List of holidays for all countries in the date range
     */
    public static List<Holiday> getHolidays(LocalDate startDate, LocalDate endDate) {
        List<Holiday> holidays = new ArrayList<>();

        for (CountryCode countryCode : holidayProviders.keySet()) {
            holidays.addAll(getHolidays(startDate, endDate, countryCode));
        }

        return holidays;
    }

    // ===== Check if a date is a Public Holiday =====

    /**
     * Get a holiday filter predicate
     * <p>
     * Creates a predicate that filters holidays based on observed date, holiday types,
     * and optional subdivision codes.
     * </p>
     *
     * @param date             The date to match
     * @param holidayTypes     The holiday types to include
     * @param subdivisionCode  Optional subdivision code (can be null)
     * @return Predicate for filtering holidays
     */
    private static Predicate<Holiday> getHolidayFilter(
            LocalDate date,
            EnumSet<HolidayTypes> holidayTypes,
            String subdivisionCode) {
        return holiday -> {
            // Check observed date matches
            if (!holiday.getObservedDate().equals(date)) {
                return false;
            }

            // Check subdivision code if specified
            if (subdivisionCode != null) {
                String[] subdivisionCodes = holiday.getSubdivisionCodes();
                if (subdivisionCodes == null) {
                    return false;
                }
                boolean hasSubdivision = false;
                for (String code : subdivisionCodes) {
                    if (code.equals(subdivisionCode)) {
                        hasSubdivision = true;
                        break;
                    }
                }
                if (!hasSubdivision) {
                    return false;
                }
            }

            // Check holiday types - at least one type must match
            EnumSet<HolidayTypes> holidayTypesSet = holiday.getHolidayTypes();
            if (holidayTypesSet == null) {
                return false;
            }
            for (HolidayTypes type : holidayTypes) {
                if (holidayTypesSet.contains(type)) {
                    return true;
                }
            }
            return false;
        };
    }

    /**
     * Check if a given date is a Public Holiday
     *
     * @param date        The date
     * @param countryCode Country Code as string (ISO 3166-1 ALPHA-2)
     * @return True if given date is public holiday in given country, false otherwise
     * @throws IllegalArgumentException if the country code is not recognized as valid
     */
    public static boolean isPublicHoliday(LocalDate date, String countryCode) {
        Optional<CountryCode> parsedCountryCode = CountryCodeHelper.tryParseCountryCode(countryCode);
        if (!parsedCountryCode.isPresent()) {
            throw new IllegalArgumentException(String.format(COUNTRY_CODE_PARSING_ERROR, countryCode));
        }

        return isPublicHoliday(date, parsedCountryCode.get());
    }

    /**
     * Check if a given date is a Public Holiday
     *
     * @param date        The date
     * @param countryCode Country Code (ISO 3166-1 ALPHA-2)
     * @return True if given date is public holiday in given country, false otherwise
     */
    public static boolean isPublicHoliday(LocalDate date, CountryCode countryCode) {
        List<Holiday> holidays = getHolidays(date.getYear(), countryCode);
        return holidays.stream()
                .anyMatch(getHolidayFilter(date, EnumSet.of(HolidayTypes.PUBLIC), null));
    }

    /**
     * Check if a given date is a Public Holiday
     *
     * @param date             The date to check
     * @param countryCode      Country Code (ISO 3166-1 ALPHA-2)
     * @param subdivisionCode  Subdivision code of a country
     * @return True if given date is public holiday in given country and subdivision, false otherwise
     * @throws IllegalArgumentException if the subdivision code is null or invalid
     */
    public static boolean isPublicHoliday(LocalDate date, CountryCode countryCode, String subdivisionCode) {
        if (subdivisionCode == null) {
            throw new IllegalArgumentException("subdivisionCode is null");
        }

        IHolidayProvider provider = getHolidayProvider(countryCode);
        if (provider instanceof ISubdivisionCodesProvider) {
            ISubdivisionCodesProvider subdivisionProvider = (ISubdivisionCodesProvider) provider;
            if (!subdivisionProvider.getSubdivisionCodes().containsKey(subdivisionCode)) {
                throw new IllegalArgumentException("Invalid subdivisionCode " + subdivisionCode);
            }
        }

        List<Holiday> holidays = getHolidays(date.getYear(), countryCode);
        return holidays.stream()
                .anyMatch(getHolidayFilter(date, EnumSet.of(HolidayTypes.PUBLIC), subdivisionCode));
    }

    // ===== Check if a date is a Holiday (any type) =====

    /**
     * Check if a given date is a Holiday of specified types
     * <p>
     * Returns true if at least one holiday matching the specified types exists on the given date.
     * </p>
     *
     * @param date         The date
     * @param countryCode  Country Code (ISO 3166-1 ALPHA-2)
     * @param holidayTypes The holiday types to check for
     * @return True if given date is a holiday of the specified types in given country, false otherwise
     */
    public static boolean isHoliday(LocalDate date, CountryCode countryCode, EnumSet<HolidayTypes> holidayTypes) {
        List<Holiday> holidays = getHolidays(date.getYear(), countryCode);
        return holidays.stream()
                .anyMatch(getHolidayFilter(date, holidayTypes, null));
    }

    /**
     * Get all holidays for a specific date
     * <p>
     * Returns all holidays that match the specified date and holiday types.
     * </p>
     *
     * @param date         The date
     * @param countryCode  Country Code (ISO 3166-1 ALPHA-2)
     * @param holidayTypes The holiday types to include
     * @return List of holidays for given date and types
     */
    public static List<Holiday> getHolidaysForDate(LocalDate date, CountryCode countryCode, EnumSet<HolidayTypes> holidayTypes) {
        List<Holiday> holidays = getHolidays(date.getYear(), countryCode);
        return holidays.stream()
                .filter(getHolidayFilter(date, holidayTypes, null))
                .collect(Collectors.toList());
    }
}
