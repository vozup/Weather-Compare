package vozup.weathercompare.com.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import vozup.weathercompare.com.sinoptik.Cities;

import java.util.HashMap;

@RestController
public class Common {
    @Autowired
    private Cities cities;

    @GetMapping("/sinoptik/cities")
    public @ResponseBody HashMap<String, String> citiesAndUrls(){
        return cities.getAllCities();
    }

}
