package at.nager.date.models;

/**
 * Month
 */
public enum Month {
    /**
     * January
     */
    JANUARY(1),

    /**
     * February
     */
    FEBRUARY(2),

    /**
     * March
     */
    MARCH(3),

    /**
     * April
     */
    APRIL(4),

    /**
     * May
     */
    MAY(5),

    /**
     * June
     */
    JUNE(6),

    /**
     * July
     */
    JULY(7),

    /**
     * August
     */
    AUGUST(8),

    /**
     * September
     */
    SEPTEMBER(9),

    /**
     * October
     */
    OCTOBER(10),

    /**
     * November
     */
    NOVEMBER(11),

    /**
     * December
     */
    DECEMBER(12);

    private final int value;

    Month(int value) {
        this.value = value;
    }

    /**
     * Gets the numeric value of the month (1-12)
     * @return the numeric value
     */
    public int getValue() {
        return value;
    }

    /**
     * Gets a Month from its numeric value
     * @param value the numeric value (1-12)
     * @return the corresponding Month
     * @throws IllegalArgumentException if value is not between 1 and 12
     */
    public static Month fromValue(int value) {
        for (Month month : values()) {
            if (month.value == value) {
                return month;
            }
        }
        throw new IllegalArgumentException("Invalid month value: " + value);
    }
}
