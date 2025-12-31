package at.nager.date.models;

import java.time.LocalDate;

/**
 * License Info
 * <p>
 * Contains information about a validated license key.
 * </p>
 */
public class LicenseInfo {

    private String owner;
    private LocalDate validUntil;

    /**
     * Gets the license owner
     *
     * @return the owner name
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Sets the license owner
     *
     * @param owner the owner name
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * Gets the license expiration date
     *
     * @return the expiration date
     */
    public LocalDate getValidUntil() {
        return validUntil;
    }

    /**
     * Sets the license expiration date
     *
     * @param validUntil the expiration date
     */
    public void setValidUntil(LocalDate validUntil) {
        this.validUntil = validUntil;
    }

    /**
     * Checks if the license is still valid
     *
     * @return true if the license is valid, false if expired
     */
    public boolean isValid() {
        if (validUntil == null) {
            return false;
        }
        return !LocalDate.now().isAfter(validUntil);
    }
}
