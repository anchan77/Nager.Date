package at.nager.date.models;

import java.util.EnumSet;

/**
 * The type of holiday (flags enum)
 */
public enum HolidayTypes {
    /**
     * A public holiday, typically designated by the government or public administration
     */
    PUBLIC(1),

    /**
     * A holiday when banks and offices are commonly closed
     */
    BANK(2),

    /**
     * A holiday when schools are closed
     */
    SCHOOL(4),

    /**
     * A holiday when authorities and public institutions are closed
     */
    AUTHORITIES(8),

    /**
     * A holiday when the majority of people have a day off, but not necessarily everyone
     */
    OPTIONAL(16),

    /**
     * An optional celebration that is celebrated by some people but does not involve a paid day off
     */
    OBSERVANCE(32);

    private final int code;

    HolidayTypes(int code) {
        this.code = code;
    }

    /**
     * Gets the bitmask code for this holiday type
     * @return the bitmask code
     */
    public int getCode() {
        return code;
    }

    /**
     * Converts an EnumSet to a bitmask integer
     * @param types the EnumSet of holiday types
     * @return the combined bitmask value
     */
    public static int toBitmask(EnumSet<HolidayTypes> types) {
        int result = 0;
        for (HolidayTypes type : types) {
            result |= type.code;
        }
        return result;
    }

    /**
     * Converts a bitmask integer to an EnumSet
     * @param bitmask the bitmask value
     * @return the EnumSet representing the types
     */
    public static EnumSet<HolidayTypes> fromBitmask(int bitmask) {
        EnumSet<HolidayTypes> result = EnumSet.noneOf(HolidayTypes.class);
        for (HolidayTypes type : values()) {
            if ((bitmask & type.code) != 0) {
                result.add(type);
            }
        }
        return result;
    }

    /**
     * Checks if a bitmask contains a specific holiday type
     * @param bitmask the bitmask to check
     * @param type the type to look for
     * @return true if the type is present in the bitmask
     */
    public static boolean contains(int bitmask, HolidayTypes type) {
        return (bitmask & type.code) != 0;
    }
}
