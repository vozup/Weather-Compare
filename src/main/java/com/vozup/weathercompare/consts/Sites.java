package com.vozup.weathercompare.consts;

public enum Sites {
    SINOPTIK("Sinoptik"), GISMETEO("Gismeteo");

    private String site;

    Sites(String site) {
        this.site = site;
    }

    public String getValue(){
        return this.site;
    }
}
