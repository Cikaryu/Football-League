package com.uts.uasmobprogug214.models;

import javax.annotation.Generated;
import jakarta.validation.Valid;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
@Generated("jsonschema2pojo")
public class Results {
    @SerializedName("score")
    @Expose
    private String score;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("away")
    @Expose
    private String away;
    @SerializedName("home")
    @Expose
    private String home;

    public String getScore() { return score; }
    public void setScore(String score) {
        this.score = score;
    }
    public String getDate() { return date; }
    public void setDate(String date) {
        this.date = date;
    }
    public String getAway() { return away; }
    public void setAway(String away) {
        this.away = away;
    }
    public String getHome() { return home; }
    public void setHome(String home) {this.home = home;
    }
}
