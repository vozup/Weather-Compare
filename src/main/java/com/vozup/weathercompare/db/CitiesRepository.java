package com.vozup.weathercompare.db;

import com.vozup.weathercompare.common.StringResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.HashMap;
import java.util.List;

@RepositoryRestResource(collectionResourceRel = "SinoptikCities", path = "SinoptikCities")
public interface CitiesRepository extends JpaRepository<CitiesEntity, Long> {
    @RestResource(path = "findUrlByCity", rel = "FindUrlMethod")
    @Query("SELECT c.url FROM CitiesEntity c WHERE c.city = :city")
    String findUrlByCityEquals(@Param("city")String city);

    @RestResource(path = "findEntityByCity", rel = "FindEntityMethod")
    CitiesEntity findFirstByCityEquals(@Param("city") String city);
}
