package com.uts.uasmobprogug214;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.LENGTH_SHORT;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uts.uasmobprogug214.models.ModelLeague;
import com.uts.uasmobprogug214.models.ResultLeague;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;


public class LeagueFragment extends Fragment {
    Context ctx;
    Button btn1;
    RecyclerView recyclerView;
    ReyclerViewLeagueCustomAdapter leagueAdapter;
    ApiInterface apiService;
    ResultLeague resultLeague;
    List<ModelLeague> data1;
    Spinner spinteam, spinleague;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_league, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        spinteam = view.findViewById(R.id.spinTeam);
        spinleague = view.findViewById(R.id.spinLeague);
        return view;

        ctx = getActivity();
        ArrayAdapter<CharSequence> leaguesAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.leagues,
                android.R.layout.simple_spinner_item);
        leaguesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinleague.setAdapter(leaguesAdapter);

        ArrayAdapter<CharSequence> teamsAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.teams,
                android.R.layout.simple_spinner_item
        );
        teamsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinteam.setAdapter(teamsAdapter);

        LinearLayoutManager manager = new LinearLayoutManager(ctx);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);

        data1 = new ArrayList<>();

        apiService = ApiClient.getClient().create(ApiInterface.class);
        loadData();
        return view;
    }
    public void loadData() {
        //String selectedLeagues = spinleague.getSelectedItem().toString();
        String selectedTeam = spinteam.getSelectedItem().toString();
        Call<ResultLeague> getLeague = apiService.getLeague(selectedTeam);
        getLeague.enqueue(new Callback<ResultLeague>() {
            @Override
            public void onResponse(Call<ResultLeague> call, Response<ResultLeague> response) {
                if (response.code() != 200) {
                    Toast.makeText(ctx, "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                } else {
                    if (response.body() == null) {

                    } else {
                        resultLeague = response.body();
                        data1 = resultLeague.getResult();
                    }
                }

            }

            @Override
            public void onFailure(Call<ResultLeague> call, Throwable t) {
                Toast.makeText(ctx, "Error: " + t.getMessage(), LENGTH_LONG).show();
            }
        });
    }
}