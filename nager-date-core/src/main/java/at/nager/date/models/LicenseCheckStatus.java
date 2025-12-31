package at.nager.date.models;

/**
 * License Check Status
 */
public enum LicenseCheckStatus {
    /**
     * License key is missing or not configured
     */
    NOT_CONFIGURED,

    /**
     * License key has not been checked
     */
    NOT_CHECKED,

    /**
     * License key is invalid
     */
    INVALID,

    /**
     * License key has expired
     */
    EXPIRED,

    /**
     * License key is valid
     */
    VALID
}
