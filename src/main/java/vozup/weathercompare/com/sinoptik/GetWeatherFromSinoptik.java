package vozup.weathercompare.com.sinoptik;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.context.annotation.SessionScope;
import vozup.weathercompare.com.consts.Sites;
import vozup.weathercompare.com.consts.WeatherInfoTitle;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

@Named("weather")
@SessionScope
public class GetWeatherFromSinoptik {
    private String city;
    private Integer countOfDays;
    private String[] selectedSites;
    private List<String> sites;

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

    private WeatherInfo currentWeather;
    private List<WeatherInfo> weatherOnFewDays = new ArrayList<>();

    public String weatherNow() {
        return "goToWeatherCompare";
    }
    //TODO For All Add Data, Length
    //
    private WeatherInfo currentWeatherData(String[] data){
        WeatherInfo.Builder weatherInfo = WeatherInfo.newBuilder();

        weatherInfo.setDate(new Date().toString());
        weatherInfo.setTime(data[0] + data[1]);
        weatherInfo.setTemperature(data[3]);
        weatherInfo.setTemperatureFeeling(data[4]);
        weatherInfo.setAtmospherePressure(Integer.parseInt(data[5]));
        weatherInfo.setWet(Integer.parseInt(data[6]));
        //FIXME
        weatherInfo.setWind(new Wind(Double.valueOf(data[7]), "Direction"));
        weatherInfo.setProbabilityPrecipitation(data[8]);

        return weatherInfo.build();
    }

    public WeatherInfo getCurrentWeather() throws IOException {
        Document doc = Jsoup.connect("https://sinoptik.ua/погода-" + city.toLowerCase()).get();
        Elements weatherDetails = doc.select(".rSide .weatherDetails");
        Elements current = weatherDetails.select(".cur");

        currentWeather = currentWeatherData(current.text().split(" "));
        return currentWeather;
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

    public String[] getSelectedSites() {
        return selectedSites;
    }

    public void setSelectedSites(String[] selectedSites) {
        this.selectedSites = selectedSites;
    }

    public List<String> getSites() {
        return sites;
    }

    public void testing() throws IOException {
        Document doc = Jsoup.connect("https://sinoptik.ua/погода-" + city.toLowerCase()).get();

        Elements weatherDetails = doc.select(".rSide .weatherDetails");
        Element temperature = weatherDetails.select(".temperature").get(0);
        Element temperatureSens = weatherDetails.select(".temperatureSens").get(0);
        Element gray = weatherDetails.select(".gray").not(".time").get(0);
        Element wind = weatherDetails.select(".gray").not(".time").get(1);
        Element time = weatherDetails.select(".gray").get(0);
        Element wet = weatherDetails.select(".gray").not(".time").next("tr").get(0);
        Element rain = weatherDetails.select(".gray").not(".time").next("tr").get(1);

        System.out.print("Time: ");
        System.out.print(time.text() + " ");

        System.out.print("\nTemperature: ");
        System.out.print(temperature.text() + " ");

        System.out.print("\nTemperature Sensetive: ");
        System.out.print(temperatureSens.text() + " ");

        System.out.print("\nWind: ");
        System.out.print(wind.text() + " ");

        System.out.print("\nGray: ");
        System.out.print(gray.text() + " ");

        System.out.print("\nWet: ");
        System.out.print(wet.text() + " ");

        System.out.print("\nRain: ");
        System.out.print(rain.text() + " ");
    }
}
