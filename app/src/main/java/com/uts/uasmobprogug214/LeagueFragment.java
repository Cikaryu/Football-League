package com.uts.uasmobprogug214;

import static android.widget.Toast.LENGTH_LONG;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.uts.uasmobprogug214.models.ModelLeague;
import com.uts.uasmobprogug214.models.ModelLeaguesList;
import com.uts.uasmobprogug214.models.ResultLeague;
import com.uts.uasmobprogug214.models.ResultLeagueLists;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LeagueFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LeagueFragment extends Fragment {
    Context ctx;
    Button btn1;
    RecyclerView recyclerView;
    ReyclerViewLeagueCustomAdapter leagueAdapter;
    ApiInterface apiService;
    ResultLeague resultLeague;
    List<ModelLeague> data1;
    Spinner  spinLeagues;
    TextView txtLeague;
    ResultLeagueLists leagueListsBody;
    List<ModelLeaguesList> dataLeagueLists;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LeagueFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LeagueFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LeagueFragment newInstance(String param1, String param2) {
        LeagueFragment fragment = new LeagueFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        ctx = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_league, container, false);
        apiService = ApiClient.getClient().create(ApiInterface.class);
        btn1 = view.findViewById(R.id.btnSave);
        recyclerView = view.findViewById(R.id.recyclerView);
        spinLeagues = view.findViewById(R.id.spinLeague);
        txtLeague = view.findViewById(R.id.txtNamaLeague);


        loadDataLeagueList();
        /*ArrayAdapter<CharSequence> leaguesAdapter = ArrayAdapter.createFromResource(
                ctx,
                R.array.leaguesList,
                android.R.layout.simple_spinner_item);
        leaguesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinleague.setAdapter(leaguesAdapter);*/

        LinearLayoutManager manager = new LinearLayoutManager(ctx);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);

        if (leagueAdapter != null) {
            leagueAdapter = null;
            data1.clear();
        }

        btn1.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }
        }));
        loadData();
        return view;
    }

    public void loadDataLeagueList() {
        Call<ResultLeagueLists> getLeagueList = apiService.getLeagueList();
        getLeagueList.enqueue(new Callback<ResultLeagueLists>() {
            @Override
            public void onResponse(Call<ResultLeagueLists> call, Response<ResultLeagueLists> response) {
                if (getActivity() == null) {
                    // Fragment not attached to an activity, do nothing
                    return;
                }

                if (response.code() != 200) {
                    Toast.makeText(ctx, "Error:" + response.code(), Toast.LENGTH_SHORT).show();
                } else {
                    if (response.body() != null) {
                        leagueListsBody = response.body();
                        dataLeagueLists = leagueListsBody.getResult();

                        if (dataLeagueLists != null) {
                            for (ModelLeaguesList item : dataLeagueLists) {
                                String key = item.getKey();
                                if (key != null) {
                                    Log.d("Key", key);
                                }
                            }

                            List<String> displayList = new ArrayList<>();
                            for (ModelLeaguesList item : dataLeagueLists) {
                                displayList.add(item.getKey());
                            }

                            ArrayAdapter<String> leagueListAdapter = new ArrayAdapter<>(
                                    ctx, android.R.layout.simple_spinner_item, displayList
                            );

                            leagueListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinLeagues.setAdapter(leagueListAdapter);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ResultLeagueLists> call, Throwable t) {
                Log.e("API_CALL", "Error: " + t.getMessage());
                Toast.makeText(ctx, "Error:" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void loadData() {
        String selectedLeague = "ingiltere-premier-ligi";
        txtLeague.setText(selectedLeague);
        if (spinLeagues.getSelectedItem() != null) {
            selectedLeague = spinLeagues.getSelectedItem().toString();
            txtLeague.setText(selectedLeague);
        }
//        String selectedLeague = spinLeagues.getSelectedItem().toString();
//        txtLeague.setText(selectedLeague);
        Call <ResultLeague> getLeague = apiService.getLeague(selectedLeague);
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
                        leagueAdapter = new ReyclerViewLeagueCustomAdapter(ctx, data1);
                        recyclerView.setAdapter(leagueAdapter);
                    }
                }

            }

            @Override
            public void onFailure(Call<ResultLeague> call, Throwable t) {
                Log.e("API_CALL", "Error: " + t.getMessage());
                Toast.makeText(ctx, "Error: " + t.getMessage(), LENGTH_LONG).show();
            }
        });
    }
}