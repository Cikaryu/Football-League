package com.uts.uasmobprogug214;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uts.uasmobprogug214.models.ModelLeaguesList;
import com.uts.uasmobprogug214.models.ResultLeagueLists;
import com.uts.uasmobprogug214.models.ResultResults;
import com.uts.uasmobprogug214.models.ModelResults;

import java.util.ArrayList;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ResultFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResultFragment extends Fragment {
    Context ctx;
    Button btn1;
    RecyclerView recyclerView1;
    ApiInterface apiService;
    ResultResults result;
    ResultLeagueLists leagueListsBody;
    List<ModelResults> data1;
    List<ModelLeaguesList> dataLeagueLists;
    RecyclerViewResult adapter;
    Spinner spinLeagues;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ResultFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ResultFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ResultFragment newInstance(String param1, String param2) {
        ResultFragment fragment = new ResultFragment();
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
        View view = inflater.inflate(R.layout.fragment_result, container, false);
        apiService = ApiClient.getClient().create(ApiInterface.class);

        // Inisialisasi elemen UI
        btn1 = view.findViewById(R.id.btnSubmit);
        recyclerView1 = view.findViewById(R.id.recyclerView1);
        spinLeagues = view.findViewById(R.id.spinLeagues);

        ArrayAdapter<CharSequence> leaguesAdapter = ArrayAdapter.createFromResource(
                ctx,
                R.array.leagues_display,
                android.R.layout.simple_spinner_item);

        leaguesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinLeagues.setAdapter(leaguesAdapter);

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
                loadData();
            }
        });

        // panggil function load data
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

    public void loadData(){
        int selectedPosition = spinLeagues.getSelectedItemPosition();

        // Mendapatkan nilai dari array leagues_values berdasarkan posisi yang dipilih
        String selectedLeagueValue = getResources().getStringArray(R.array.leagues_values)[selectedPosition];

        Call<ResultResults> getResultByTeam = apiService.getResults(selectedLeagueValue);

        getResultByTeam.enqueue(new Callback<ResultResults>() {
            @Override
            public void onResponse(Call<ResultResults> call, Response<ResultResults> response) {
                if (getActivity() == null) {
                    // Fragment not attached to an activity, do nothing
                    return;
                }

                if (response.code() != 200) {
                    Toast.makeText(ctx, "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                } else {
                    if (response.body() != null) {
                        result = response.body();
                        data1 = result.getResult();

                        if (data1 != null && !data1.isEmpty()) {
                            // Iterate through the data1 list
                            for (ModelResults item : data1) {
                                String score = item.getScore();
                                if (score != null) {
                                    Log.d("Score", score);
                                }
                            }

                            // Update the RecyclerViewResult adapter with the new data on the UI thread
                            adapter = new RecyclerViewResult(ctx, data1);
                            recyclerView1.setAdapter(adapter);
                        } else {
                            // Handle the case when the data1 list is null or empty
                            Log.d("ResultFragment", "Data1 list is null or empty");
                            Toast.makeText(ctx, "No results available", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ResultResults> call, Throwable t) {
                Log.e("API_CALL", "Error: " + t.getMessage());
                Toast.makeText(ctx, "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

}