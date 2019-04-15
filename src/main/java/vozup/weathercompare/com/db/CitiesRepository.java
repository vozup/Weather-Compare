package vozup.weathercompare.com.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vozup.weathercompare.com.common.StringResult;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "cities", path = "cities")
public interface CitiesRepository extends JpaRepository<CitiesEntity, Long> {
    @RestResource(path = "findUrlByCity", rel = "FindUrlMethod")
    @Query("SELECT c.url FROM CitiesEntity c WHERE c.city = :city")
    String findUrlByCityEquals(@Param("city")String city);

    @RestResource(path = "findEntityByCity", rel = "FindEntityMethod")
    CitiesEntity findFirstByCityEquals(@Param("city") String city);

    //FIXME QUERY
    @Query("SELECT DISTINCT new vozup.weathercompare.com.common.StringResult(c.city) FROM CitiesEntity c")
    List<StringResult> findAllCities();

    @Query("SELECT DISTINCT new vozup.weathercompare.com.common.StringResult(c.url) FROM CitiesEntity c")
    List<StringResult> findAllUrls();
}
