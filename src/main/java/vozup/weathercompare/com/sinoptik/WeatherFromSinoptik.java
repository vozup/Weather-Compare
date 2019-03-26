package vozup.weathercompare.com.sinoptik;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import vozup.weathercompare.com.consts.WeatherInfoTitle;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class WeatherFromSinoptik {
    private String city;

    public WeatherFromSinoptik(String city) {
        this.city = city;
    }

    /**
     * Парсинг погоды на текущее время
     * @return погодa на текущее время
     * @throws IOException
     */
    public WeatherInfo getCurrentWeather() throws IOException {
        WeatherInfo currentWeather;

        Document doc = Jsoup.connect("https://sinoptik.ua/погода-" + city.toLowerCase()).get();
        Elements weatherDetails = doc.select(".rSide .weatherDetails");
        Elements current = weatherDetails.select(".cur");

        currentWeather = currentWeatherData(current.text().split(" "));
        return currentWeather;
    }

    /**
     * Парсинг погоды на несколько дней
     * @param countOfDays количество дней, включительно с сегоднешним(1 - только сегодня)
     * @return погода на несколько дней
     * @throws IOException
     */
    public List<List<WeatherInfo>> getWeatherOnFewDays(Integer countOfDays) throws IOException {
        List<List<WeatherInfo>> weatherOnFewDays = new ArrayList<>();
        if (countOfDays == 1){
            weatherOnFewDays.add(getWeatherOnDay(LocalDate.now().toString()));
            return weatherOnFewDays;
        }else {
            for (int i =0; i< countOfDays; i++){
                weatherOnFewDays.add(getWeatherOnDay(LocalDate.now().plusDays(i).toString()));
            }
            return weatherOnFewDays;
        }
    }


    //TODO For All Add Data, Length
    //

    /**
     * Конвертация строк с данными из сайта в общий класс
     * @param data строка с данными о погоде на текущее время
     * @return
     */
    private WeatherInfo currentWeatherData(String[] data){
        WeatherInfo.Builder weatherInfo = WeatherInfo.newBuilder();

        weatherInfo.setDate(new Date().toString());
        weatherInfo.setTime(data[0] + data[1]);
        weatherInfo.setTemperature(data[3]);
        weatherInfo.setTemperatureFeeling(data[4]);
        weatherInfo.setAtmospherePressure(Integer.parseInt(data[5]));
        weatherInfo.setWet(Integer.parseInt(data[6]));
        //FIXME Direction
        weatherInfo.setWind(new Wind(Double.valueOf(data[7]), "Direction"));
        weatherInfo.setProbabilityPrecipitation(data[8]);

        return weatherInfo.build();
    }

    /**
     * Получить погоду на один день
     * @param date дата погоды
     * @return список WeatherInfo на выбраный день
     * @WeatherInfo данные о погоде на каждый час
     * @throws IOException
     */
    private  List<WeatherInfo> getWeatherOnDay(String date) throws IOException {
        Document doc = Jsoup.connect("https://sinoptik.ua/погода-" + city.toLowerCase() + "/" + date).get();
        Map<WeatherInfoTitle, List<String>> weatherOnDay = new HashMap<>();

        Elements weatherDetails = doc.select(".rSide .weatherDetails");
        Element temperature = weatherDetails.select(".temperature").get(0);
        Element temperatureSens = weatherDetails.select(".temperatureSens").get(0);
        Element gray = weatherDetails.select(".gray").not(".time").get(0);
        Element wind = weatherDetails.select(".gray").not(".time").get(1);
        Element time = weatherDetails.select(".gray").get(0);
        Element wet = weatherDetails.select(".gray").not(".time").next("tr").get(0);
        Element rain = weatherDetails.select(".gray").not(".time").next("tr").get(1);

        weatherOnDay.put(WeatherInfoTitle.TEMPERATURE, Arrays.asList(temperature.text().split(" ")));
        weatherOnDay.put(WeatherInfoTitle.TEMPERATURE_FEELING, Arrays.asList(temperatureSens.text().split(" ")));
        weatherOnDay.put(WeatherInfoTitle.ATMOSPHERE_PRESSUREA, Arrays.asList(gray.text().split(" ")));
        weatherOnDay.put(WeatherInfoTitle.WIND, Arrays.asList(wind.text().split(" ")));
        weatherOnDay.put(WeatherInfoTitle.WET, Arrays.asList(wet.text().split(" ")));
        weatherOnDay.put(WeatherInfoTitle.PROBABILITY_PRECIPITATION, Arrays.asList(rain.text().split(" ")));
        weatherOnDay.put(WeatherInfoTitle.TIME,
                Arrays.asList(time.text().replaceAll(" :", ":").split(" ")));

        List<WeatherInfo> weatherInfos = new ArrayList<>();
        int count = weatherOnDay.get(WeatherInfoTitle.TIME).size();

        for (int i=0; i<count; i++){
            WeatherInfo.Builder weatherInfo = WeatherInfo.newBuilder();

            weatherInfo.setTime(weatherOnDay.get(WeatherInfoTitle.TIME).get(i));
            weatherInfo.setDate(date);
            weatherInfo.setWind(new Wind(Double.parseDouble(weatherOnDay.get(WeatherInfoTitle.WIND).get(i)),
                    "Direction"));
            weatherInfo.setWet(Integer.parseInt(weatherOnDay.get(WeatherInfoTitle.WET).get(i)));
            weatherInfo.setProbabilityPrecipitation(weatherOnDay.get(WeatherInfoTitle.PROBABILITY_PRECIPITATION).get(i));
            weatherInfo.setAtmospherePressure(Integer.parseInt(weatherOnDay.get(WeatherInfoTitle.ATMOSPHERE_PRESSUREA).get(i)));
            weatherInfo.setTemperature(weatherOnDay.get(WeatherInfoTitle.TEMPERATURE).get(i));
            weatherInfo.setTemperatureFeeling(weatherOnDay.get(WeatherInfoTitle.TEMPERATURE_FEELING).get(i));

            weatherInfos.add(weatherInfo.build());
        }

        return weatherInfos;
    }

    public void testing(String date) throws IOException {
        Document doc = Jsoup.connect("https://sinoptik.ua/погода-" + city.toLowerCase() + "/" + date).get();

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

        System.out.print("\nTemperatureш: ");
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
