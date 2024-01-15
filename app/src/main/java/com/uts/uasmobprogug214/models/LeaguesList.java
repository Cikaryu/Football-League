package com.uts.uasmobprogug214.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LeaguesList {
    @SerializedName("league")
    @Expose
    private List<ModelLeague> modelLeagues;

    public List<ModelLeague> getLeagues() {
        return modelLeagues;
    }

    public void setLeagues(List<ModelLeague> modelLeagues) {
        this.modelLeagues = modelLeagues;
    }
}
