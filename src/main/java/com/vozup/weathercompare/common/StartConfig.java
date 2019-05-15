package com.vozup.weathercompare.common;

import com.vozup.weathercompare.db.GismeteoSitesRepository;
import com.vozup.weathercompare.db.SinoptikCitiesRepository;
import com.vozup.weathercompare.db.SitesRepository;
import com.vozup.weathercompare.sites.InitSitesDb;
import com.vozup.weathercompare.sites.gismeteo.GismeteoCities;
import com.vozup.weathercompare.sites.sinoptik.SinoptikCities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

@Configuration
public class StartConfig {
    @Autowired
    SinoptikCitiesRepository repository;
    @Autowired
    SitesRepository sitesRepository;
    @Autowired
    GismeteoSitesRepository gismeteoCities;
    private Properties property;

    private static Logger log = Logger.getLogger(StartConfig.class.getName());

    public StartConfig(){
        String propertyFileName = "app.properties";
        property = new Properties();

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propertyFileName);
        try {
            property.load(inputStream);
        } catch (IOException e) {
            log.warning("File app.properties don't exist or else!");
            e.printStackTrace();
        }
    }

    /**
     * Если параметр app.isfirststart = yes
     * парсим все города и соответствующие ему URL'S
     * и записываем в базу
     * @return
     * @throws IOException
     */
    @Bean
    public SinoptikCities parsingCitiesFromSinoptik() throws IOException {
        if (property.getProperty("app.init.sinoptik.db").equals("yes")){
            log.info("Start init sinoptik DB");
            return new SinoptikCities(repository);
        }else return null;
    }
    /**
     * Если параметр app.isfirststart = yes
     * парсим все города и соответствующие ему URL'S
     * и записываем в базу
     * @return
     * @throws IOException
     */
    @Bean
    public GismeteoCities parsingCitiesFromGismeteo() throws IOException {
        if (property.getProperty("app.init.gismeteo.db").equals("yes")){
            log.info("Start init gismeteo DB");
            return new GismeteoCities(gismeteoCities);
        }else return null;
    }
    /**
     * Если параметр app.isinitsitesdb = yes
     * записываем в базу сайты с которых будем брать инфу
     * @return
     * @throws IOException
     */
    @Bean
    public InitSitesDb initSitesDb(){
        if (property.getProperty("app.isinit.sites.db").equals("yes")){
            log.info("Start init sites DB");
            return new InitSitesDb(sitesRepository);
        }else return null;
    }

}
