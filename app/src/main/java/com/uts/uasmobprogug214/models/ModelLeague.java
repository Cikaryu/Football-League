package com.uts.uasmobprogug214.models;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class ModelLeague {
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

    public String getRank() { return rank; }
    public void setRank(String rank) {
        this.rank = rank;
    }
    public String getTeam() { return team; }
    public void setTeam(String team) {
        this.team = team;
    }
    public String getWin() { return win; }
    public void setWin(String win) {
        this.win = win;
    }
    public  String getLose()  { return lose; }
    public void  setLose(String lose) { this.lose = lose; }
    public  String getPoint()  { return point; }
    public void  setPoint(String point) { this.point = point; }
    public  String getPlay()  { return play; }
    public void  setPlay(String play) { this.play = play; }

}

