package io.seoLeir.socialmedia.dto.publication;

public enum PeriodType {
    DAILY("daily"),
    WEEKLY("weekly"),
    MONTHLY("monthly"),
    YEARLY("yearly"),
    ALLTIME("alltime");
    private final String value;

    PeriodType(String period) {
        this.value = period;
    }

    @Override
    public String toString() {
        return value;
    }

    public String getValue() {
        return value;
    }
}
