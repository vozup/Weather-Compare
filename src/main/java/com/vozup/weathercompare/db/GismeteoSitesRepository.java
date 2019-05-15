package com.vozup.weathercompare.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "GismeteoCities", path = "GismeteoCities")
public interface GismeteoSitesRepository extends JpaRepository<GismeteoCitiesEntity, Long> {

}
