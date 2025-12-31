package at.nager.date;

/**
 * License Key Exception
 * <p>
 * Thrown when license validation fails or when operations are attempted without a valid license.
 * </p>
 */
public class LicenseKeyException extends RuntimeException {

    /**
     * Constructs a new LicenseKeyException with the specified detail message.
     * The message is automatically appended with GitHub sponsors information.
     *
     * @param message the detail message describing the license issue
     */
    public LicenseKeyException(String message) {
        super(message + " - As a GitHub sponsor of this project you will receive a license key, https://github.com/sponsors/nager");
    }
}
