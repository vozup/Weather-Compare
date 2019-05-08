package com.vozup.weathercompare.sites.gismeteo;

import com.vozup.weathercompare.sites.WeatherFunctional;
import com.vozup.weathercompare.sites.WeatherInfo;

import java.io.IOException;
import java.util.List;

public class WeatherFromGismeteo implements WeatherFunctional {
    private String city;

    public WeatherFromGismeteo(String city) {
        this.city = city;
    }

    @Override
    public WeatherInfo getCurrentWeather() throws IOException {
        return null;
    }

    @Override
    public List<List<WeatherInfo>> getWeatherOnFewDays(Integer countOfDays) throws IOException {
        return null;
    }
}
