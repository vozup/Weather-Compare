package vozup.weathercompare.com.consts;

public enum WeatherInfoTitle {
    TEMPERATURE("Temperature"), TIME("Time"), DATE("Date"), TEMPERATURE_FEELING("TemperatureFeeling"),
    ATMOSPHERE_PRESSUREA("AtmospherePressure"),WET("Wet"), WIND("Wind"),
    PROBABILITY_PRECIPITATION("Probability Precipitation"), SUNRISE("Sunrise"), SUNSET("Sunset");

    private String name;

    WeatherInfoTitle(String name) {
        this.name = name;
    }

    public String getValue(){
        return this.name;
    }

}
