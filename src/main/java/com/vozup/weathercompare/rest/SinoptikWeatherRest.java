package com.vozup.weathercompare.rest;

import org.springframework.web.bind.annotation.*;
import com.vozup.weathercompare.sites.sinoptik.WeatherFromSinoptik;
import com.vozup.weathercompare.sites.WeatherInfo;

import java.io.IOException;
import java.util.List;

@RestController
public class SinoptikWeatherRest {

    @RequestMapping(value = "/weather/sinoptik/current")
    public @ResponseBody WeatherInfo getCurrentWeatherFromSinoptik(@RequestParam(value = "city") String city) throws IOException {
        System.out.println("Current func debug");
        WeatherFromSinoptik w = new WeatherFromSinoptik(city);
        return w.getCurrentWeather();
    }

    @RequestMapping(value = "/weather/sinoptik/fewdays", method = RequestMethod.GET)
    public @ResponseBody List<List<WeatherInfo>> getWeatherOnFewDaysFromSinoptik(@RequestParam(value = "city") String city,
                                                                 @RequestParam(value = "days") int days) throws IOException {
        System.out.println("FewDays func debug");
        WeatherFromSinoptik w = new WeatherFromSinoptik(city);
        return w.getWeatherOnFewDays(days);
    }
}
