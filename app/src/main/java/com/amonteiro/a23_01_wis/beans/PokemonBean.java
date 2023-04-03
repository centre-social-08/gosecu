package com.amonteiro.a23_01_wis.beans;

import java.util.List;

public class PokemonBean {

    private String name;
    private List<String> type;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }
}
