package com.uts.uasmobprogug214;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.uts.uasmobprogug214.models.ModelGoalKings;
import com.uts.uasmobprogug214.models.ModelLeaguesList;
import com.uts.uasmobprogug214.models.ResultGoalKings;
import com.uts.uasmobprogug214.models.ResultLeagueLists;



import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GoalsKingFragment extends Fragment {

    Context ctx;
    Button btn1;
    RecyclerView recyclerView1;
    ApiInterface apiService;
    List<ModelGoalKings> data1;
    ResultGoalKings result;
    RecyclerViewGoalKings adapter;
    Spinner spinnerLeague;
    List<ModelLeaguesList> dataLeagueLists;
    ResultLeagueLists leagueListsBody;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public GoalsKingFragment() {
        // Required empty public constructor
    }

    public static GoalsKingFragment newInstance(String param1, String param2) {
        GoalsKingFragment fragment = new GoalsKingFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goals_king, container, false);

        apiService = ApiClient.getClient().create(ApiInterface.class);
        recyclerView1 = view.findViewById(R.id.recyclerViewGoalKings);
        btn1 = view.findViewById(R.id.buttongoals);
        spinnerLeague = view.findViewById(R.id.spinnerSearchBy);

//        loadDataLeagueList();

        ArrayAdapter<CharSequence> leaguesAdapter = ArrayAdapter.createFromResource(
                ctx,
                R.array.leagues_display,
                android.R.layout.simple_spinner_item);

        leaguesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLeague.setAdapter(leaguesAdapter);

        LinearLayoutManager manager = new LinearLayoutManager(ctx);
        recyclerView1.setLayoutManager(manager);
        recyclerView1.setHasFixedSize(true);


        if (adapter != null) {
            adapter = null;
            data1.clear();
        }


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadData();
            }
        });
        LoadData();
        return view;
    }

//    public void loadDataLeagueList() {
//        Call<ResultLeagueLists> getLeagueList = apiService.getLeagueList();
//        getLeagueList.enqueue(new Callback<ResultLeagueLists>() {
//            @Override
//            public void onResponse(Call<ResultLeagueLists> call, Response<ResultLeagueLists> response) {
//                if (getActivity() == null) {
//                    // Fragment not attached to an activity, do nothing
//                    return;
//                }
//
//                if (response.code() != 200) {
//                    Toast.makeText(ctx, "Error:" + response.code(), Toast.LENGTH_SHORT).show();
//                } else {
//                    if (response.body() != null) {
//                        leagueListsBody = response.body();
//                        dataLeagueLists = leagueListsBody.getResult();
//
//                        if (dataLeagueLists != null) {
//                            for (ModelLeaguesList item : dataLeagueLists) {
//                                String key = item.getKey();
//                                if (key != null) {
//                                    Log.d("Key", key);
//                                }
//                            }
//
//                            List<String> displayList = new ArrayList<>();
//                            for (ModelLeaguesList item : dataLeagueLists) {
//                                displayList.add(item.getKey());
//                            }
//
//                            ArrayAdapter<String> leagueListAdapter = new ArrayAdapter<>(
//                                    ctx, android.R.layout.simple_spinner_item, displayList
//                            );
//
//                            leagueListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                            spinnerLeague.setAdapter(leagueListAdapter);
//                        }
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResultLeagueLists> call, Throwable t) {
//                Log.e("API_CALL", "Error: " + t.getMessage());
//                Toast.makeText(ctx, "Error:" + t.getMessage(), Toast.LENGTH_LONG).show();
//            }
//        });
//    }

    public void LoadData() {
//        String selectedLeague = "ingiltere-premier-ligi";
//        if (spinnerLeague.getSelectedItem() != null) {
//            selectedLeague = spinnerLeague.getSelectedItem().toString();
//        }

        int selectedPosition = spinnerLeague.getSelectedItemPosition();
        String selectedLeagueValue = getResources().getStringArray(R.array.leagues_values)[selectedPosition];

        Call<ResultGoalKings> getGoalKings = apiService.getGoalKings(selectedLeagueValue);
        getGoalKings.enqueue(new Callback<ResultGoalKings>() {
            @Override
            public void onResponse(Call<ResultGoalKings> call, Response<ResultGoalKings> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        result = response.body();

                        // Check the 'success' field in the response
                        if (result.getSuccess()) {
                            data1 = result.getResult();
                            adapter = new RecyclerViewGoalKings(ctx, data1);
                            recyclerView1.setAdapter(adapter);
                            // Show the RecyclerView when 'success' is true
                            showRecyclerView(true);
                        } else {
                            // Hide the RecyclerView when 'success' is false
                            showRecyclerView(false);
                            // Show a message when 'success' is false
                            showMessage("no data !");
                        }

                    } else {
                        // Hide the RecyclerView when the response body is null
                        showRecyclerView(false);
                        // Handle the case when the response body is null
                        showMessage("no data !");
                    }

                } else {
                    // Handle the case when the response code is not 200 (OK)
                    Toast.makeText(ctx, "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                    // Hide the RecyclerView when there is an error
                    showRecyclerView(false);
                }
            }

            @Override
            public void onFailure(Call<ResultGoalKings> call, Throwable t) {
                Log.e("API Error", "Error: " + t.getMessage(), t);
                Toast.makeText(ctx, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void showMessage(String message) {
        Toast.makeText(ctx, message, Toast.LENGTH_SHORT).show();
    }
    private void showRecyclerView(boolean show) {
        if (show) {
            recyclerView1.setVisibility(View.VISIBLE);
        } else {
            recyclerView1.setVisibility(View.GONE);
        }
    }

}