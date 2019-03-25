package vozup.weathercompare.com.consts;

public enum Sites {
    SINOPTIK("https://sinoptik.ua/"), GISMETEO("https://www.gismeteo.ua/");

    private String site;

    Sites(String site) {
        this.site = site;
    }

    public String getSite(){
        return site;
    }
}
