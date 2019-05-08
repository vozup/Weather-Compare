package com.vozup.weathercompare.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(collectionResourceRel = "GismeteoCities", path = "GismeteoCities")
public interface GismeteoSitesRepository extends JpaRepository<GismeteoCitiesEntity, Long> {
    @RestResource(path = "findUrlByCity", rel = "FindUrlMethod")
    @Query("SELECT c.url FROM GismeteoCitiesEntity c WHERE c.city = :city")
    String findUrlByCityEquals(@Param("city")String city);
}
