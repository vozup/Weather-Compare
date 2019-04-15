package vozup.weathercompare.com.db;

import org.springframework.cglib.core.GeneratorStrategy;
import vozup.weathercompare.com.common.StringResult;

import javax.persistence.Id;

import javax.annotation.Generated;
import javax.persistence.*;

@Entity
@Table
public class CitiesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "CITY")
    private String city;
    @Column(name = "URL")
    private String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
