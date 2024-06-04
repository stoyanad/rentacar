package org.example.rc.domain;

/**
 * Enumeration of the rental package.
 */
public enum RentPackageEnum {
    DAILY("Daily"),
    WEEKLY("Weekly"),
    MONTHLY("Monthly"),
    YEARLY("Yearly");

    private final String name;

    RentPackageEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
