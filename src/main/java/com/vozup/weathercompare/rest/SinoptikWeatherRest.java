package com.vozup.weathercompare.rest;

import com.vozup.weathercompare.db.SinoptikCitiesRepository;
import com.vozup.weathercompare.sites.WeatherInfo;
import com.vozup.weathercompare.sites.sinoptik.WeatherFromSinoptik;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class SinoptikWeatherRest implements WeatherRest{
    @Autowired
    SinoptikCitiesRepository sinoptikRepository;

    @Override
    @RequestMapping(value = "/weather/sinoptik/current/{id}")
    public @ResponseBody WeatherInfo getCurrentWeatherFromSinoptik(@PathVariable(value = "id") String id) throws IOException {
        Long longId = Long.valueOf(id);
        WeatherFromSinoptik w = new WeatherFromSinoptik(longId, sinoptikRepository);
        return w.getCurrentWeather();
    }

    @Override
    @RequestMapping(value = "/weather/sinoptik/fewdays/{id}", method = RequestMethod.GET)
    public @ResponseBody List<List<WeatherInfo>> getWeatherOnFewDaysFromSinoptik(@PathVariable(value = "id") String id,
                                                                                @RequestParam(value = "days", defaultValue = "1") int days)
                                                                                throws IOException {
        Long longId = Long.valueOf(id);
        WeatherFromSinoptik w = new WeatherFromSinoptik(longId, sinoptikRepository);
        return w.getWeatherOnFewDays(days);
    }
}
