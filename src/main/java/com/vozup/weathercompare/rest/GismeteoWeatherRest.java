package com.vozup.weathercompare.rest;

import com.vozup.weathercompare.db.GismeteoSitesRepository;
import com.vozup.weathercompare.sites.WeatherInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class GismeteoWeatherRest implements WeatherRest{
    @Autowired
    GismeteoSitesRepository gismeteoRepository;

    @Override
    @RequestMapping(value = "/weather/gismeteo/current/{id}")
    public WeatherInfo getCurrentWeatherFromSinoptik(@PathVariable(value = "id") String id) throws IOException {
        return null;
    }


    //FIXME
    @Override
    @RequestMapping(value = "/weather/gismeteo/fewdays/{id}", method = RequestMethod.GET)
    public List<List<WeatherInfo>> getWeatherOnFewDaysFromSinoptik(@PathVariable(value = "id") String id,
                                                                   @RequestParam(value = "days", defaultValue = "1") int days) throws IOException {
        return null;
    }
}
