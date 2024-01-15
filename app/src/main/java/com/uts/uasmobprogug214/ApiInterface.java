package com.uts.uasmobprogug214;

import com.uts.uasmobprogug214.models.LeaguesList;
import com.uts.uasmobprogug214.models.ResultGoalKings;
import com.uts.uasmobprogug214.models.Results;
import com.uts.uasmobprogug214.models.League;
import com.uts.uasmobprogug214.models.GoalKings;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiInterface {

    String API_KEY = "apikey 2JejIXfRlSfpEmuH3WAk8t:3o0BdH9jpX5M766J3NikCe";

    @Headers({"Content-Type: application/json",
            "Authorization: " + API_KEY
    })
    @GET("sport/leaguesList")
    Call<LeaguesList> getLeaguesList(@Query("data.league") String dataLeaguesList);

    @Headers({"Content-Type: application/json",
            "Authorization: " + API_KEY
    })
    @GET("sport/results")
    Call<Results> getResults(@Query("data.league") String dataResult);

    @Headers({"Content-Type: application/json",
            "Authorization: " + API_KEY
    })
    @GET("sport/league")
    Call<League> getLeague(@Query("data.league") String dataLeague);

    @Headers({"Content-Type: application/json",
            "Authorization: " + API_KEY
    })
    @GET("sport/goalKings")
    Call <ResultGoalKings> getGoalKings(@Query("data.league") String dataGoalKings) ;
}
