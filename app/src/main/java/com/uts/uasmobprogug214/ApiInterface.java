package com.uts.uasmobprogug214;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("sport/sport-api/leaguesList")
    Call<ApiResponse> getResults();
    @GET("sport/sport-api/results")
    Call<ApiResponse> getResults();

    @GET("sport/sport-api/league")
    Call<ApiResponse> getLeagues();

    @GET("sport/sport-api/goalKings")
    Call<ApiResponse> getGoalKings();
}
