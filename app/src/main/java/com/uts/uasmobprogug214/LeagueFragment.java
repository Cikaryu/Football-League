package com.uts.uasmobprogug214;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uts.uasmobprogug214.models.League;
import com.uts.uasmobprogug214.models.LeaguesList;

import com.uts.uasmobprogug214.ReyclerViewLeagueCustomAdapter;
import com.uts.uasmobprogug214.ApiClient;
import com.uts.uasmobprogug214.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;


public class LeagueFragment extends Fragment {

    private RecyclerView recyclerView;
    private ReyclerViewLeagueCustomAdapter leagueAdapter;

    // ...

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_league, container, false);

        // Find the RecyclerView in the layout
        recyclerView = view.findViewById(R.id.recyclerView);

        // Set the layout manager for the RecyclerView (in this case, LinearLayoutManager)
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Create a new instance of LeagueAdapter and set it to the RecyclerView
        leagueAdapter = new ReyclerViewLeagueCustomAdapter(new ArrayList<>());
        recyclerView.setAdapter(leagueAdapter);

        // Call the method to fetch data from the API and update the RecyclerView
        fetchDataFromApi();

        // Return the inflated view for the fragment
        return view;
    }

    // Method to fetch data from the API using Retrofit
    private void fetchDataFromApi() {
        // Create an instance of ApiInterface using ApiClient
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        // Create a Retrofit call to get data from the API
        Call<LeaguesList> call = apiInterface.getLeaguesList("sport/leaguesList");

        // Enqueue the call to execute asynchronously
        call.enqueue(new Callback<LeaguesList>() {
            @Override
            public void onResponse(Call<LeaguesList> call, Response<LeaguesList> response) {
                if (response.isSuccessful()) {
                    // Log or print the data received
                    Log.d("API Response", "Data: " + response.body());

                    // Update the adapter with the received data
                    List<League> leagues = response.body().getLeagues();
                    Log.d("API Response", "Number of leagues: " + leagues.size());

                    leagueAdapter.setLeagueList(leagues);
                }
            }

            @Override
            public void onFailure(Call<LeaguesList> call, Throwable t) {
                // Handle failure (e.g., network issues, server errors)
                // You might want to implement appropriate error handling here
                Log.e("API Request", "Failed: " + t.getMessage());
            }
        });
    }
}

