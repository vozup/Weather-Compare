package com.vozup.weathercompare.sites;

import com.vozup.weathercompare.consts.Sites;
import com.vozup.weathercompare.db.SitesEntity;
import com.vozup.weathercompare.db.SitesRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class InitSitesDb {
    public InitSitesDb(SitesRepository sitesRepository) {
        for (Sites site : Sites.values()) {
            SitesEntity sitesEntity = new SitesEntity();
            if (!sitesRepository.existsBySite(site.getValue())){
                sitesEntity.setSite(site.getValue());
                sitesRepository.save(sitesEntity);
            }
        }
    }
}
