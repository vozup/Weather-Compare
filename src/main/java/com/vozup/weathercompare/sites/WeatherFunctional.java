package com.vozup.weathercompare.sites;

import com.vozup.weathercompare.sites.sinoptik.WeatherInfo;

import java.io.IOException;
import java.util.List;

public interface WeatherFunctional {
    /**
     * Парсинг погоды на текущее время
     * @return погодa на текущее время
     * @throws IOException
     */
    WeatherInfo getCurrentWeather() throws IOException;
    /**
     * Парсинг погоды на несколько дней
     * @param countOfDays количество дней, включительно с сегоднешним(1 - только сегодня)
     * @return погода на несколько дней
     * @throws IOException
     */
    List<List<WeatherInfo>> getWeatherOnFewDays(Integer countOfDays) throws IOException;
}
