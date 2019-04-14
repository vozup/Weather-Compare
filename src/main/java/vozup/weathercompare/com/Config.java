package vozup.weathercompare.com;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vozup.weathercompare.com.sinoptik.Cities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
public class Config {
    /**
     * Если программа запускается в первый раз собираем список городов с сайта
     * @return
     * @throws IOException
     */
    @Bean
    public Cities allCities() throws IOException {
        String propertyFileName = "app.properties";
        Properties property = new Properties();

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propertyFileName);
        property.load(inputStream);

        //FIXME FOR DEBUG
        if (property.getProperty("app.isfirststart").equals("yes")){
            return new Cities(false);
        }else {
            //Пустой класс
            return new Cities(true);
        }
    }
}
