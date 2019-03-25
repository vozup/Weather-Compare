package vozup.weathercompare.com.jsoup;

import org.springframework.web.context.annotation.SessionScope;
import vozup.weathercompare.com.sinoptik.WeatherInfo;

import javax.inject.Named;


public class WeatherBean {
    private String city;
    private WeatherInfo weatherInfo;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public WeatherInfo getWeatherInfo() {
        return weatherInfo;
    }

    public void setWeatherInfo(WeatherInfo weatherInfo) {
        this.weatherInfo = weatherInfo;
    }
}
