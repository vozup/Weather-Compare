package com.vozup.weathercompare.rest;

import com.vozup.weathercompare.db.SinoptikCitiesRepository;
import com.vozup.weathercompare.sites.WeatherInfo;
import com.vozup.weathercompare.sites.sinoptik.WeatherFromSinoptik;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class SinoptikWeatherRest {
    @Autowired
    SinoptikCitiesRepository sinoptikRepository;

    @RequestMapping(value = "/weather/sinoptik/current/{id}")
    public @ResponseBody WeatherInfo getCurrentWeatherFromSinoptik(@PathVariable(value = "id") Long id) throws IOException {
        System.out.println("Current func debug");
        WeatherFromSinoptik w = new WeatherFromSinoptik(id, sinoptikRepository);
        return w.getCurrentWeather();
    }

    @RequestMapping(value = "/weather/sinoptik/fewdays/{id}", method = RequestMethod.GET)
    public @ResponseBody List<List<WeatherInfo>> getWeatherOnFewDaysFromSinoptik(@PathVariable(value = "id") Long id,
                                                                                @RequestParam(value = "days", defaultValue = "1") int days)
                                                                                throws IOException {
        System.out.println("FewDays func debug");
        WeatherFromSinoptik w = new WeatherFromSinoptik(id, sinoptikRepository);
        return w.getWeatherOnFewDays(days);
    }
}
