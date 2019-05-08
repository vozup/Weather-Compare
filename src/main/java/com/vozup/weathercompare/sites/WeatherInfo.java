package com.vozup.weathercompare.sites;

//FIXME Time, Date
public class WeatherInfo {
    private String time;
    private String date;
    private String temperature;
    private String temperatureFeeling;
    private Integer atmospherePressure;
    private Integer wet;
    private Wind wind;
    //вероятность осадков
    private String probabilityPrecipitation;
    private String sunrise;
    private String sunset;

    private WeatherInfo(){

    }

    public static Builder newBuilder(){
        return new WeatherInfo().new Builder();
    }

    public class Builder{
        private Builder(){

        }
        public Builder setTemperature(String temperature){
            WeatherInfo.this.temperature = temperature;
            return this;
        }

        public Builder setTemperatureFeeling(String temperatureFeeling) {
            WeatherInfo.this.temperatureFeeling = temperatureFeeling;
            return this;
        }

        public Builder setAtmospherePressure(Integer atmospherePressure) {
            WeatherInfo.this.atmospherePressure = atmospherePressure;
            return this;
        }

        public Builder setWet(Integer wet) {
            WeatherInfo.this.wet = wet;
            return this;
        }

        public Builder setWind(Wind wind) {
            WeatherInfo.this.wind = wind;
            return this;
        }

        public Builder setProbabilityPrecipitation(String probabilityPrecipitation) {
            WeatherInfo.this.probabilityPrecipitation = probabilityPrecipitation;
            return this;
        }

        public Builder setSunrise(String sunrise) {
            WeatherInfo.this.sunrise = sunrise;
            return this;
        }

        public Builder setSunset(String sunset) {
            WeatherInfo.this.sunset = sunset;
            return this;
        }

        public Builder setTime(String time) {
            WeatherInfo.this.time = time;
            return this;
        }

        public Builder setDate(String date) {
            WeatherInfo.this.date = date;
            return this;
        }

        public WeatherInfo build(){
            return WeatherInfo.this;
        }
    }

    public String getTemperature() {
        return temperature;
    }

    public String getTemperatureFeeling() {
        return temperatureFeeling;
    }

    public Integer getAtmospherePressure() {
        return atmospherePressure;
    }

    public Integer getWet() {
        return wet;
    }

    public Wind getWind() {
        return wind;
    }

    public String getProbabilityPrecipitation() {
        return probabilityPrecipitation;
    }

    public String getSunrise() {
        return sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }


}
