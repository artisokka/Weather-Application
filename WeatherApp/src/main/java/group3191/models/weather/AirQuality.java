package group3191.models.weather;

public enum AirQuality {
    GOOD(1, "Good"),
    FAIR(2, "Fair"),
    MODERATE(3, "Moderate"),
    POOR(4, "Poor"),
    VERY_POOR(5, "Very Poor");

    private final int value;
    private final String description;

    AirQuality(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    // Map an integer value to the corresponding enum constant
    public static AirQuality fromValue(int value) {
        for (AirQuality airQuality : values()) {
            if (airQuality.getValue() == value) {
                return airQuality;
            }
        }
        throw new IllegalArgumentException("Invalid AirQuality value: " + value);
    }
}
