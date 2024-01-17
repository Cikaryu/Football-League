package com.uts.uasmobprogug214.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelLeaguesList {
    @SerializedName("league")
    @Expose
    private String league;

    @SerializedName("key")
    @Expose
    private String key;

    public String getLeague() { return league; }
    public void setLeague(String league) {
        this.league = league;
    }

    public String getKey() { return key; }
    public void setKey(String key) {
        this.key = key;
    }
}
