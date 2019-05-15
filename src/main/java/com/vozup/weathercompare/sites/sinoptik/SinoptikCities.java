package com.vozup.weathercompare.sites.sinoptik;

import com.vozup.weathercompare.db.SinoptikCitiesEntity;
import com.vozup.weathercompare.db.SinoptikCitiesRepository;
import com.vozup.weathercompare.sites.CommonSite;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;

/**
 * Класс для парсинга всех городов и url которые им соответствуют
 * с сайта sinoptik.ua
 */
public class SinoptikCities extends CommonSite {
    private String slash = "/";

    private HashMap<String, String> hrefAndRegion;
    private HashMap<String, String> allCities;

    public SinoptikCities(SinoptikCitiesRepository repository) throws IOException {
        hrefAndRegion = new HashMap<>();
        allCities = new HashMap<>();

        initHrefAndRegion();
        initAllCities(repository);
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
    private void initAllCities(SinoptikCitiesRepository repository){
        hrefAndRegion.forEach((region, url) -> {
            for (char ch = 'А'; ch < 'Я'; ch++){
                Document doc = null;
                try {
                    doc = Jsoup.connect(url + slash + ch).get();
                    Elements col4Cities = doc.select(".col4");

                    for (Element el : col4Cities.select("li")){
                        SinoptikCitiesEntity sinoptikCitiesEntity = new SinoptikCitiesEntity();
                        String city = el.select("a").text();
                        city = city.replaceAll("^[а-я]+\\s", "");
                        sinoptikCitiesEntity.setCity(city);
                        String fullRegion = el.select("span").text() + " " + region;
                        sinoptikCitiesEntity.setRegion(fullRegion);
                        sinoptikCitiesEntity.setFullLocation(city + " " + fullRegion);
                        sinoptikCitiesEntity.setUrl("https:" + el.select("a").attr("href"));
                        repository.save(sinoptikCitiesEntity);
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
