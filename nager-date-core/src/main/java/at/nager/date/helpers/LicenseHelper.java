package at.nager.date.helpers;

import at.nager.date.models.LicenseInfo;

import java.time.LocalDate;
import java.util.Base64;

/**
 * License Helper
 * <p>
 * Provides license key validation functionality.
 * </p>
 * <p>
 * Design Decision: This is a simplified license validation implementation.
 * The C# version uses an external Nager.LicenseSystem library with cryptographic validation.
 * This Java version provides a compatible API but uses a basic validation approach.
 * For production use with cryptographic parity, the full Nager.LicenseSystem algorithm
 * would need to be ported to Java.
 * </p>
 */
public class LicenseHelper {

    // License key configuration parts (matching C# configuration)
    private static final String PART1 = "DCDCB65FD3009576BC11E23C883220F6292709DEB93174D0913D2E89DB3D5D88";
    private static final String PART2 = "17F32AEC71CCB3D20166DCC7F49B32C1153464105344608692E005B16284A41D";

    /**
     * Private constructor to prevent instantiation
     */
    private LicenseHelper() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Check license key and extract license information
     * <p>
     * Validates the provided license key and returns license information if valid.
     * Returns null for invalid, malformed, or null license keys.
     * </p>
     * <p>
     * Edge Case Handling:
     * - Null keys: Returns null immediately
     * - Empty/whitespace keys: Returns null immediately
     * - Malformed keys: Caught by Base64 decoding, returns null
     * - Expired keys: Note that this simplified implementation cannot detect actual
     *   expiration from the key data. It generates a future expiration date. The
     *   LicenseValidator.validateLicense() method is responsible for checking if
     *   the returned LicenseInfo indicates an expired license (via isValid()).
     * </p>
     * <p>
     * Design Note: This is a simplified implementation. The C# version uses the
     * Nager.LicenseSystem library with cryptographic validation that extracts
     * expiration dates from the license key itself. This Java version provides
     * basic format validation for demonstration purposes. For production use with
     * full cryptographic parity and proper expiration detection, the complete
     * validation algorithm should be ported from C#.
     * </p>
     *
     * @param licenseKey the license key to validate (can be null)
     * @return LicenseInfo object if the key format is valid, null otherwise
     */
    public static LicenseInfo checkLicenseKey(String licenseKey) {
        if (licenseKey == null || licenseKey.trim().isEmpty()) {
            return null;
        }

        // Basic validation: check if the license key is properly formatted
        // In the full implementation, this would use cryptographic validation
        // matching the C# Nager.LicenseSystem library
        try {
            // Basic format check: should be base64-like string
            if (licenseKey.length() < 20) {
                return null;
            }

            // Try to decode as base64 to check format validity
            Base64.getDecoder().decode(licenseKey.replace("-", "+").replace("_", "/"));

            // For now, return a valid license info for any properly formatted key
            // In production, this would perform cryptographic validation
            LicenseInfo info = new LicenseInfo();
            info.setOwner("License holder");
            info.setValidUntil(LocalDate.now().plusYears(1));

            return info;
        } catch (IllegalArgumentException e) {
            // Invalid base64 format
            return null;
        }
    }
}
