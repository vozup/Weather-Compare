package com.vozup.weathercompare.sinoptik;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.vozup.weathercompare.db.CitiesEntity;
import com.vozup.weathercompare.db.CitiesRepository;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Класс для парсинга всех городов и url которые им соответствуют
 * с сайта sinoptik.ua
 */
@Controller
public class Cities {
    private final CitiesRepository repository;

    private String slash = "/";

    private HashMap<String, String> hrefAndRegion;
    private HashMap<String, String> allCities;

    @Autowired
    public Cities(CitiesRepository repository) throws IOException {
        hrefAndRegion = new HashMap<>();
        allCities = new HashMap<>();
        this.repository = repository;

        String propertyFileName = "app.properties";
        Properties property = new Properties();

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propertyFileName);
        property.load(inputStream);

        //FIXME FOR DEBUG
        if (property.getProperty("app.isfirststart").equals("yes")){
                initHrefAndRegion();
                initAllCities();
        }
    }

    /**
     * Получить области и соответствующие url
     * @throws IOException
     */
    private void initHrefAndRegion() throws IOException {
        Document doc = Jsoup.connect("https://sinoptik.ua/украина").get();

        Elements allRegions = doc.getElementsMatchingOwnText("Области Украины").next();

        for (Element el : allRegions.select("ul li a")){
            hrefAndRegion.put(
                    el.text(),
                    "https:" + el.attr("href"));
        }

        //hrefAndRegion.forEach((k, v) -> System.out.println("Key: " + k + " Value: " + v));
    }

    /**
     *  Получить города и соответствующие url
     */
    //FIXME Update regex
    private void initAllCities(){
        hrefAndRegion.forEach((region, url) -> {
            for (char ch = 'А'; ch < 'Я'; ch++){
                Document doc = null;
                try {
                    doc = Jsoup.connect(url + slash + ch).get();
                    Elements col4Cities = doc.select(".col4");

                    for (Element el : col4Cities.select("li")){
                        CitiesEntity citiesEntity = new CitiesEntity();
                        citiesEntity.setCity(el.text().replaceAll("^[а-я]+\\s", "")+ " " + region);
                        citiesEntity.setUrl("https:" + el.select("a").attr("href"));
                        repository.save(citiesEntity);
//                      allCities.put(
//                                el.text().replaceAll("^[а-я]+\\s", "") + " " + region,
//                                "https:" + el.select("a").attr("href"));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        //allCities.forEach((k, v) -> System.out.println("Key: " + k + " Value: " + v));
    }

    public HashMap<String, String> getAllCities() {
        return allCities;
    }
}
