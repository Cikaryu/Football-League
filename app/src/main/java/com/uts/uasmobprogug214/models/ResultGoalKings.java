package com.uts.uasmobprogug214.models;

import javax.annotation.Generated;
import jakarta.validation.Valid;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Generated("jsonschema2pojo")
public class ResultGoalKings {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("result")
    @Expose
    @Valid
    private List<GoalKings> result;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<GoalKings> getResult() {
        return result;
    }

    public void setResult(List<GoalKings> result) {
        this.result = result;
    }

}
