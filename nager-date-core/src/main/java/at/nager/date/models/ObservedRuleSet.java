package at.nager.date.models;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.function.Function;

/**
 * Observed Rule Set
 * Defines rules for calculating the observed date when a holiday falls on a specific day of the week
 */
public class ObservedRuleSet {

    private Function<LocalDate, LocalDate> monday;
    private Function<LocalDate, LocalDate> tuesday;
    private Function<LocalDate, LocalDate> wednesday;
    private Function<LocalDate, LocalDate> thursday;
    private Function<LocalDate, LocalDate> friday;
    private Function<LocalDate, LocalDate> saturday;
    private Function<LocalDate, LocalDate> sunday;

    /**
     * Sets the rule for observing a holiday when it falls on a Monday
     * @param rule the transformation function
     */
    public void setMonday(Function<LocalDate, LocalDate> rule) {
        this.monday = rule;
    }

    /**
     * Sets the rule for observing a holiday when it falls on a Tuesday
     * @param rule the transformation function
     */
    public void setTuesday(Function<LocalDate, LocalDate> rule) {
        this.tuesday = rule;
    }

    /**
     * Sets the rule for observing a holiday when it falls on a Wednesday
     * @param rule the transformation function
     */
    public void setWednesday(Function<LocalDate, LocalDate> rule) {
        this.wednesday = rule;
    }

    /**
     * Sets the rule for observing a holiday when it falls on a Thursday
     * @param rule the transformation function
     */
    public void setThursday(Function<LocalDate, LocalDate> rule) {
        this.thursday = rule;
    }

    /**
     * Sets the rule for observing a holiday when it falls on a Friday
     * @param rule the transformation function
     */
    public void setFriday(Function<LocalDate, LocalDate> rule) {
        this.friday = rule;
    }

    /**
     * Sets the rule for observing a holiday when it falls on a Saturday
     * @param rule the transformation function
     */
    public void setSaturday(Function<LocalDate, LocalDate> rule) {
        this.saturday = rule;
    }

    /**
     * Sets the rule for observing a holiday when it falls on a Sunday
     * @param rule the transformation function
     */
    public void setSunday(Function<LocalDate, LocalDate> rule) {
        this.sunday = rule;
    }

    /**
     * Gets the rule for Monday
     * @return the rule function, or null if not set
     */
    public Function<LocalDate, LocalDate> getMonday() {
        return monday;
    }

    /**
     * Gets the rule for Tuesday
     * @return the rule function, or null if not set
     */
    public Function<LocalDate, LocalDate> getTuesday() {
        return tuesday;
    }

    /**
     * Gets the rule for Wednesday
     * @return the rule function, or null if not set
     */
    public Function<LocalDate, LocalDate> getWednesday() {
        return wednesday;
    }

    /**
     * Gets the rule for Thursday
     * @return the rule function, or null if not set
     */
    public Function<LocalDate, LocalDate> getThursday() {
        return thursday;
    }

    /**
     * Gets the rule for Friday
     * @return the rule function, or null if not set
     */
    public Function<LocalDate, LocalDate> getFriday() {
        return friday;
    }

    /**
     * Gets the rule for Saturday
     * @return the rule function, or null if not set
     */
    public Function<LocalDate, LocalDate> getSaturday() {
        return saturday;
    }

    /**
     * Gets the rule for Sunday
     * @return the rule function, or null if not set
     */
    public Function<LocalDate, LocalDate> getSunday() {
        return sunday;
    }

    /**
     * Gets the observed date for a given date, according to the rules
     * @param givenDate the original date of the holiday
     * @return the observed date based on the rules, or null if no rule is defined for that day
     */
    public LocalDate getObservedDate(LocalDate givenDate) {
        if (givenDate == null) {
            return null;
        }

        DayOfWeek dayOfWeek = givenDate.getDayOfWeek();
        Function<LocalDate, LocalDate> rule = null;

        switch (dayOfWeek) {
            case MONDAY:
                rule = this.monday;
                break;
            case TUESDAY:
                rule = this.tuesday;
                break;
            case WEDNESDAY:
                rule = this.wednesday;
                break;
            case THURSDAY:
                rule = this.thursday;
                break;
            case FRIDAY:
                rule = this.friday;
                break;
            case SATURDAY:
                rule = this.saturday;
                break;
            case SUNDAY:
                rule = this.sunday;
                break;
        }

        return rule != null ? rule.apply(givenDate) : null;
    }
}
