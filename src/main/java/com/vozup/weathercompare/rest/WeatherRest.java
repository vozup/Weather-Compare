package com.vozup.weathercompare.rest;

import com.vozup.weathercompare.sites.WeatherInfo;

import java.io.IOException;
import java.util.List;

public interface WeatherRest {
    WeatherInfo getCurrentWeatherFromSinoptik(String id) throws IOException;
    List<List<WeatherInfo>> getWeatherOnFewDaysFromSinoptik(String id, int days) throws IOException;
}
