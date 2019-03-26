package vozup.weathercompare.com.consts;

import org.springframework.web.context.annotation.ApplicationScope;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import java.util.Locale;
import java.util.ResourceBundle;

@Named
@ApplicationScope
public class Locales {
    private ResourceBundle mybundle;

    @PostConstruct
    public void init(){
        System.out.println("Current Locale: " + Locale.getDefault());
        mybundle = ResourceBundle.getBundle("labels");
    }

    public ResourceBundle getMybundle() {
        return mybundle;
    }
}
