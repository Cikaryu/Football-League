package com.uts.uasmobprogug214.models;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.annotations.SerializedName;


import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class League {
    @SerializedName("rank")
    @Expose
    private String rank;
    @SerializedName("lose")
    @Expose
    private String lose;
    @SerializedName("win")
    @Expose
    private String win;
    @SerializedName("play")
    @Expose
    private String play;
    @SerializedName("point")
    @Expose
    private String point;
    @SerializedName("team")
    @Expose
    private String team;

    // Tambahan: Constructor kosong untuk GSON
    public League() {
    }

    // Tambahan: Constructor dengan parameter untuk inisialisasi
    public League(String rank, String lose, String win, String play, String point, String team) {
        this.rank = rank;
        this.lose = lose;
        this.win = win;
        this.play = play;
        this.point = point;
        this.team = team;
    }

    public String getRank() {
        return rank != null ? rank : "";
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getLose() {
        return lose != null ? lose : "";
    }

    public void setLose(String lose) {
        this.lose = lose;
    }

    public String getWin() {
        return win != null ? win : "";
    }

    public void setWin(String win) {
        this.win = win;
    }

    public String getPlay() {
        return play != null ? play : "";
    }

    public void setPlay(String play) {
        this.play = play;
    }

    public String getPoint() {
        return point != null ? point : "";
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getTeam() {
        return team != null ? team : "";
    }

    public void setTeam(String team) {
        this.team = team;
    }
}

