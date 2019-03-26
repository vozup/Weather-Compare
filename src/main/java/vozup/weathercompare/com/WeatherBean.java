package vozup.weathercompare.com;

import org.springframework.web.context.annotation.SessionScope;
import vozup.weathercompare.com.consts.Sites;
import vozup.weathercompare.com.sinoptik.WeatherFromSinoptik;
import vozup.weathercompare.com.sinoptik.WeatherInfo;
import vozup.weathercompare.com.sinoptik.Wind;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Named("weather")
@SessionScope
public class WeatherBean {
    private String city;
    //Add valid 1,3,5,7
    private Integer countOfDays;
    private List<String> selectedSites;
    private List<String> allSitesUrl;

    private WeatherInfo currentWeather;
    private List<List<WeatherInfo>> weatherOnFewDays;
    //
    private List<Wind> randomTestList = new ArrayList<>();


    @PostConstruct
    public void init(){
        randomTestList.add(new Wind(1.2, "sss"));
        randomTestList.add(new Wind(1.2, "asas"));
        randomTestList.add(new Wind(1.2, "ffff"));
        randomTestList.add(new Wind(1.2, "ddd"));
        randomTestList.add(new Wind(1.2, "zzz"));
        randomTestList.add(new Wind(1.2, "xxxx"));

        //Init allSitesUrl URL
        //TODO i3.properties
        allSitesUrl = new ArrayList<>();
        for(Sites site : Sites.values()){
            allSitesUrl.add(site.getSite());
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

    public List<String> getAllSitesUrl() {
        return allSitesUrl;
    }

    public WeatherInfo getCurrentWeather() {
        return currentWeather;
    }

    public List<List<WeatherInfo>> getWeatherOnFewDays() {
        return weatherOnFewDays;
    }

    public List<Wind> getRandomTestList() {
        return randomTestList;
    }

    public void setRandomTestList(List<Wind> randomTestList) {
        this.randomTestList = randomTestList;
    }
}
