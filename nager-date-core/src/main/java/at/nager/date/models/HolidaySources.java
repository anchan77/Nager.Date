package at.nager.date.models;

/**
 * The source of holiday
 * Note: Despite the [Flags] attribute in C#, this enum uses sequential values (0,1,2,3)
 * rather than proper bitmask values. Mirroring C# behavior exactly.
 */
public enum HolidaySources {
    /**
     * Undefined Holidays
     */
    UNDEFINED_HOLIDAY(0),

    /**
     * Historical Holidays
     */
    HISTORICAL_HOLIDAYS(1),

    /**
     * Cultural Holidays
     */
    CULTURAL_HOLIDAYS(2),

    /**
     * Religious Holidays
     */
    RELIGIOUS_HOLIDAYS(3);

    private final int code;

    HolidaySources(int code) {
        this.code = code;
    }

    /**
     * Gets the code for this holiday source
     * @return the code (0-3)
     */
    public int getCode() {
        return code;
    }

    /**
     * Gets a HolidaySources from its code value
     * @param code the code value (0-3)
     * @return the corresponding HolidaySources
     * @throws IllegalArgumentException if code is not valid
     */
    public static HolidaySources fromCode(int code) {
        for (HolidaySources source : values()) {
            if (source.code == code) {
                return source;
            }
        }
        throw new IllegalArgumentException("Invalid HolidaySources code: " + code);
    }
}
