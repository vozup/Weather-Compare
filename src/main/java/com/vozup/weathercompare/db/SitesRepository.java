package com.vozup.weathercompare.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "Sites", path = "Sites")
public interface SitesRepository extends JpaRepository<SitesEntity, Long> {
    boolean existsBySite(String site);
}
