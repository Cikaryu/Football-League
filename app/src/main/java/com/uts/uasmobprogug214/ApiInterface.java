package com.uts.uasmobprogug214;

import com.uts.uasmobprogug214.models.LeaguesList;
import com.uts.uasmobprogug214.models.Results;
import com.uts.uasmobprogug214.models.ResultResults;
import com.uts.uasmobprogug214.models.League;
import com.uts.uasmobprogug214.models.GoalKings;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiInterface {

    String API_KEY = "apikey 5o7fnd3jCelT09m1cUxdbG:7CffHc97mdnTMfbEmNhLo0";

    @Headers({"Content-Type: application/json",
            "Authorization: " + API_KEY
    })
    @GET("sport/leaguesList")
    Call<LeaguesList> getLeaguesList(@Query("data.league") String dataLeaguesList);

    @Headers({"Content-Type: application/json",
            "Authorization: " + API_KEY
    })
    @GET("sport/results")
    Call<ResultResults> getResults(@Query("data.league") String dataResult);

    @Headers({"Content-Type: application/json",
            "Authorization: " + API_KEY
    })
    @GET("sport/league")
    Call<League> getLeague(@Query("data.league") String dataLeague);

    @Headers({"Content-Type: application/json",
            "Authorization: " + API_KEY
    })
    @GET("sport/goalKings")
    Call<List<GoalKings>> getGoalKings(@Query("data.league") String dataGoalKings) ;
}
