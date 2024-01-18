package com.uts.uasmobprogug214.models;

import javax.annotation.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class ModelGoalKings {

    @SerializedName("goals")
    @Expose
    private String goals;
    @SerializedName("name")
    @Expose
    private String name;

    public String getGoals() {
        return goals;
    }
    public void setGoals(String goals) { this.goals = goals; }
    public String getName() {
        return name;
    }
    public void setName(String name) { this.name = name; }
}
