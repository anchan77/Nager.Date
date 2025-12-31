package at.nager.date;

import at.nager.date.helpers.LicenseHelper;
import at.nager.date.models.LicenseCheckStatus;
import at.nager.date.models.LicenseInfo;

import java.util.concurrent.atomic.AtomicReference;

/**
 * License Validator
 * <p>
 * Internal class that manages license state and validation.
 * Provides thread-safe license checking and status management.
 * </p>
 * <p>
 * Integration Note: This class is designed to be called by HolidaySystem
 * (to be implemented in a future task) before provider access. The public
 * methods (setLicenseKey, validateLicense) provide the integration points
 * for the HolidaySystem facade.
 * </p>
 */
class LicenseValidator {

    private static final AtomicReference<String> licenseKey = new AtomicReference<>(null);
    private static final AtomicReference<LicenseCheckStatus> status =
        new AtomicReference<>(LicenseCheckStatus.NOT_CHECKED);

    /**
     * Private constructor to prevent instantiation
     */
    private LicenseValidator() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Set the license key
     * <p>
     * Sets the license key and resets the validation status to NOT_CHECKED.
     * Thread-safe operation using atomic references.
     * </p>
     *
     * @param key the license key to set (can be null)
     */
    static void setLicenseKey(String key) {
        licenseKey.set(key);
        status.set(LicenseCheckStatus.NOT_CHECKED);
    }

    /**
     * Get the current license key
     *
     * @return the current license key, or null if not set
     */
    static String getLicenseKey() {
        return licenseKey.get();
    }

    /**
     * Get the current license check status
     *
     * @return the current license status
     */
    static LicenseCheckStatus getStatus() {
        return status.get();
    }

    /**
     * Validate the current license key
     * <p>
     * Performs license validation and updates the status accordingly.
     * Thread-safe operation. If already checked, returns the cached status.
     * </p>
     *
     * @throws LicenseKeyException if the license is invalid, expired, or not configured
     */
    static void validateLicense() {
        // If already validated, use cached result
        LicenseCheckStatus currentStatus = status.get();
        if (currentStatus != LicenseCheckStatus.NOT_CHECKED) {
            handleStatus(currentStatus);
            return;
        }

        // Perform validation
        String key = licenseKey.get();

        if (key == null || key.trim().isEmpty()) {
            status.set(LicenseCheckStatus.NOT_CONFIGURED);
            throw new LicenseKeyException("License key not configured");
        }

        LicenseInfo info = LicenseHelper.checkLicenseKey(key);

        if (info == null) {
            status.set(LicenseCheckStatus.INVALID);
            throw new LicenseKeyException("Invalid license key");
        }

        if (!info.isValid()) {
            status.set(LicenseCheckStatus.EXPIRED);
            throw new LicenseKeyException("License key has expired");
        }

        status.set(LicenseCheckStatus.VALID);
    }

    /**
     * Handle non-valid license status by throwing appropriate exception
     *
     * @param licenseStatus the license status to handle
     * @throws LicenseKeyException if the status is not VALID
     */
    private static void handleStatus(LicenseCheckStatus licenseStatus) {
        switch (licenseStatus) {
            case VALID:
                // License is valid, no exception
                return;
            case INVALID:
                throw new LicenseKeyException("Invalid license key");
            case EXPIRED:
                throw new LicenseKeyException("License key has expired");
            case NOT_CONFIGURED:
                throw new LicenseKeyException("License key not configured");
            case NOT_CHECKED:
                // Should not reach here, but validate just in case
                validateLicense();
                return;
            default:
                throw new LicenseKeyException("Unknown license status");
        }
    }
}
