package at.nager.date.models;

/**
 * Occurrence
 */
public enum Occurrence {
    /**
     * First
     */
    FIRST(1),

    /**
     * Second
     */
    SECOND(2),

    /**
     * Third
     */
    THIRD(3),

    /**
     * Fourth
     */
    FOURTH(4);

    private final int value;

    Occurrence(int value) {
        this.value = value;
    }

    /**
     * Gets the numeric value of the occurrence
     * @return the numeric value
     */
    public int getValue() {
        return value;
    }
}
