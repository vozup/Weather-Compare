package com.vozup.weathercompare.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "SinoptikCities", path = "SinoptikCities")
public interface SinoptikCitiesRepository extends JpaRepository<SinoptikCitiesEntity, Long> {

}
