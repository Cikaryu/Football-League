package com.uts.uasmobprogug214.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.Generated;
@Generated("jsonschema2pojo")
public class ResultLeagueLists {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("result")
    @Expose
    private List<ModelLeaguesList> result;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<ModelLeaguesList> getResult() {
        return result;
    }

    public void setResult(List<ModelLeaguesList> result) {this.result = result;}
}
