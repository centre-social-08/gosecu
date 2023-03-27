package com.amonteiro.a23_01_wis.beans;

public class WeatherBean {

    private String name;

    private WindBean wind;
    private TempBean main;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WindBean getWind() {
        return wind;
    }

    public void setWind(WindBean wind) {
        this.wind = wind;
    }

    public TempBean getMain() {
        return main;
    }

    public void setMain(TempBean main) {
        this.main = main;
    }
}
