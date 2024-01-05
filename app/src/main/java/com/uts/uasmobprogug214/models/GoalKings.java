package com.uts.uasmobprogug214.models;

import javax.annotation.Generated;
import jakarta.validation.Valid;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Generated("jsonschema2pojo")
public class GoalKings {
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
