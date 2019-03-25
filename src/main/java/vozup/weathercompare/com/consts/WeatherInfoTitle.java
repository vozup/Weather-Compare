package vozup.weathercompare.com.consts;

public enum WeatherInfoTitle {
    TEMPERATURE("Температура"), TIME("Час"), DATE("Дата"), TEMPERATURE_FEELING("Відчувається як"),
    ATMOSPHERE_PRESSUREA("Атмосферний тиск"),WET("Вологість"), WIND("Вітер"),
    PROBABILITY_PRECIPITATION("Вірогідність осадів"), SUNRISE("Схід"), SUNSET("Захід");

    private String name;

    WeatherInfoTitle(String name) {
        this.name = name;
    }

    public String getValue(){
        return this.name;
    }

}
