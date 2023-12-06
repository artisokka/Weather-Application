package group3191.models.weather;

/**
 * This enum is used to represent the air quality.
 */
public enum AirQuality {
    GOOD(1, "Good"),
    FAIR(2, "Fair"),
    MODERATE(3, "Moderate"),
    POOR(4, "Poor"),
    VERY_POOR(5, "Very Poor");

    private final int value;
    private final String description;

    /**
     * Constructor for the AirQuality enum.
     * @param value The value of the air quality.
     * @param description The description of the air quality.
     */
    AirQuality(int value, String description) {
        this.value = value;
        this.description = description;
    }

    /**
     * This method returns the value of the air quality.
     * @return The value of the air quality.
     */
    public int getValue() {
        return value;
    }

    /**
     * This method returns the description of the air quality.
     * @return The description of the air quality.
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method returns the air quality from a value.
     * @param value The value of the air quality.
     * @return The air quality.
     */
    public static AirQuality fromValue(int value) {
        for (AirQuality airQuality : values()) {
            if (airQuality.getValue() == value) {
                return airQuality;
            }
        }
        throw new IllegalArgumentException("Invalid AirQuality value: " + value);
    }
}
