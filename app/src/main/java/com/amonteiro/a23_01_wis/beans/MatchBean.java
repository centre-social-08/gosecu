package com.amonteiro.a23_01_wis.beans;

import java.util.Date;
import java.util.Objects;

public class MatchBean {
    private String id;
    private String nameTeam1;
    private String nameTeam2;
    private String idUserCreator;
    private int scoreTeam1;
    private int scoreTeam2;
    private long time;

    public MatchBean() {
    }

    public MatchBean(String nameTeam1, String nameTeam2) {
        this(null, nameTeam1, nameTeam2, null, 0,0,new Date().getTime());
    }

    public MatchBean(String id, String nameTeam1, String nameTeam2, String idUserCreator, int scoreTeam1, int scoreTeam2, long time) {
        this.id = id;
        this.nameTeam1 = nameTeam1;
        this.nameTeam2 = nameTeam2;
        this.idUserCreator = idUserCreator;
        this.scoreTeam1 = scoreTeam1;
        this.scoreTeam2 = scoreTeam2;
        this.time = time;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameTeam1() {
        return nameTeam1;
    }

    public void setNameTeam1(String nameTeam1) {
        this.nameTeam1 = nameTeam1;
    }

    public String getNameTeam2() {
        return nameTeam2;
    }

    public void setNameTeam2(String nameTeam2) {
        this.nameTeam2 = nameTeam2;
    }

    public String getIdUserCreator() {
        return idUserCreator;
    }

    public void setIdUserCreator(String idUserCreator) {
        this.idUserCreator = idUserCreator;
    }

    public int getScoreTeam1() {
        return scoreTeam1;
    }

    public void setScoreTeam1(int scoreTeam1) {
        this.scoreTeam1 = scoreTeam1;
    }

    public int getScoreTeam2() {
        return scoreTeam2;
    }

    public void setScoreTeam2(int scoreTeam2) {
        this.scoreTeam2 = scoreTeam2;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}