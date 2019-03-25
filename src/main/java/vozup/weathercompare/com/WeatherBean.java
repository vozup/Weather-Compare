package vozup.weathercompare.com;

import org.springframework.web.context.annotation.SessionScope;
import vozup.weathercompare.com.consts.Sites;
import vozup.weathercompare.com.sinoptik.WeatherFromSinoptik;
import vozup.weathercompare.com.sinoptik.WeatherInfo;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named("weather")
@SessionScope
public class WeatherBean {
    private String city;
    //Add valid 1,3,5,7
    private Integer countOfDays;
    private List<String> selectedSites;
    private List<String> sites;

    private WeatherInfo currentWeather;
    private List<List<WeatherInfo>> weatherOnFewDays;

    @PostConstruct
    public void init(){
        //Init sites URL
        //TODO i3.properties
        sites = new ArrayList<>();
        for(Sites site : Sites.values()){
            sites.add(site.getSite());
        }
        //Init column titles
        //TODO i3.properties
    }

    public String compareWeather() throws IOException {
        WeatherFromSinoptik w = new WeatherFromSinoptik(city);
        currentWeather = w.getCurrentWeather();
        weatherOnFewDays = w.getWeatherOnFewDays(countOfDays);

        return "goToWeatherCompare";
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getCountOfDays() {
        return countOfDays;
    }

    public void setCountOfDays(Integer countOfDays) {
        this.countOfDays = countOfDays;
    }

    public List<String> getSelectedSites() {
        return selectedSites;
    }

    public void setSelectedSites(List<String> selectedSites) {
        this.selectedSites = selectedSites;
    }

    public List<String> getSites() {
        return sites;
    }

    public WeatherInfo getCurrentWeather() {
        return currentWeather;
    }

    public List<List<WeatherInfo>> getWeatherOnFewDays() {
        return weatherOnFewDays;
    }
}
