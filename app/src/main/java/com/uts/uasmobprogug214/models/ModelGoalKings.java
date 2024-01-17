package com.uts.uasmobprogug214.models;

import javax.annotation.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class ModelGoalKings {
    @SerializedName("play")
    @Expose
    private String play;
    @SerializedName("goals")
    @Expose
    private String goals;
    @SerializedName("name")
    @Expose
    private String name;

    public String getPlay() {
        return play;
    }
    public void setPlay(String play) {
        this.play = play;
    }
    public String getGoals() {
        return goals;
    }
    public void setGoals(String goals) { this.goals = goals; }
    public String getName() {
        return name;
    }
    public void setName(String name) { this.name = name; }
}
