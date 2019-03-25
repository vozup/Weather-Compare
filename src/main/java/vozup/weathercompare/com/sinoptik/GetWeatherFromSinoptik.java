package vozup.weathercompare.com.sinoptik;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.context.annotation.SessionScope;

import javax.inject.Named;
import java.io.IOException;

@Named("weather")
@SessionScope
public class GetWeatherFromSinoptik {
    private String city;

    public void weatherNow() throws IOException {
        Document doc = Jsoup.connect("https://sinoptik.ua/погода-" + city.toLowerCase()).get();
        Elements weatherDetails = doc.select(".rSide .weatherDetails");
        Elements temperature = weatherDetails.select(".temperature");
        Elements temperatureSens = doc.select(".temperatureSens");
        Element gray = doc.select(".gray").not(".time").get(0);
        Element wind = doc.select(".gray").not(".time").get(1);

        Element wet = doc.select(".gray").not(".time").next("tr").get(0);
        Element rain = doc.select(".gray").not(".time").next("tr").get(1);

        System.out.print("Temperature: ");
        for (Element elem : temperature) {
            if (elem.nextElementSibling().hasClass(".cur")){
                System.out.print("Cur: ");
            }
            System.out.print(elem.text() + " ");
        }
        System.out.print("\nTemperature Sensetive: ");
        for (Element elem : temperatureSens) {
            System.out.print(elem.text() + " ");
        }
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
