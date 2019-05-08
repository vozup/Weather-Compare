package com.vozup.weathercompare.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(collectionResourceRel = "SinoptikCities", path = "SinoptikCities")
public interface SinoptikCitiesRepository extends JpaRepository<SinoptikCitiesEntity, Long> {
    @RestResource(path = "findUrlByCity", rel = "FindUrlMethod")
    @Query("SELECT c.url FROM SinoptikCitiesEntity c WHERE c.city = :city")
    String findUrlByCityEquals(@Param("city")String city);

    @RestResource(path = "findEntityByCity", rel = "FindEntityMethod")
    SinoptikCitiesEntity findFirstByCityEquals(@Param("city") String city);
}
