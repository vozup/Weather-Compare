package com.vozup.weathercompare.sites.gismeteo;

import com.vozup.weathercompare.db.GismeteoCitiesEntity;
import com.vozup.weathercompare.db.GismeteoSitesRepository;
import com.vozup.weathercompare.sites.CommonSite;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Logger;

public class GismeteoCities extends CommonSite {
    private static Logger log = Logger.getLogger(GismeteoCities.class.getName());

    private HashMap<String, String> hrefAndRegion;
    private HashMap<String, String> hrefAndRegionExt;

    public GismeteoCities(GismeteoSitesRepository repository) throws IOException {
        hrefAndRegion = new HashMap<>();
        hrefAndRegionExt = new HashMap<>();

        initHrefAndRegion();
        initAllCities(repository);
    }

    /**
     * Получить области и соответствующие url
     * @throws IOException
     */
    private void initHrefAndRegion() throws IOException {
        Document doc = Jsoup.connect("https://www.gismeteo.ua/catalog/ukraine/").get();

        Elements allRegions = doc.getElementsByClass("districts subregions wrap").select(".group");

        for (Element el : allRegions.select("ul li a")){
            hrefAndRegion.put(
                    el.text(),
                    "https://www.gismeteo.ua" + el.attr("href"));
        }

        hrefAndRegion.forEach((region, url) -> {
            Document docExt = null;
                try {
                    docExt = Jsoup.connect(url).get();
                    Elements citiesInRegion = docExt.getElementsByClass("districts subregions wrap")
                            .select(".group").select("ul li a");
                    for (Element el : citiesInRegion) {
                        String district = el.text();
                        hrefAndRegionExt.put(
                                district + " " + region,
                                "https://www.gismeteo.ua" + el.attr("href"));
                    }
                } catch (IOException e) {
                    log.warning("initHrefAndRegion error");
                    e.printStackTrace();
                }
        });
    }

    /**
     *  Получить города и соответствующие url
     */
    //FIXME Update regex
    private void initAllCities(GismeteoSitesRepository repository){
        hrefAndRegionExt.forEach((region, url) -> {
                Document doc = null;
                try {
                    doc = Jsoup.connect(url).get();
                    Elements citiesInRegion = doc.select(".group").select("ul li a");

                    for (Element el : citiesInRegion){
                        GismeteoCitiesEntity gismeteoCitiesEntity = new GismeteoCitiesEntity();
                        String city = el.text();
                        gismeteoCitiesEntity.setCity(city);
                        gismeteoCitiesEntity.setRegion(region);
                        gismeteoCitiesEntity.setFullLocation(city + " " + region);
                        gismeteoCitiesEntity.setUrl("https://www.gismeteo.ua" + el.attr("href"));
                        repository.save(gismeteoCitiesEntity);
                    }
                } catch (IOException e) {
                    log.warning("initAllCities error");
                    e.printStackTrace();
                }
        });
    }
}
