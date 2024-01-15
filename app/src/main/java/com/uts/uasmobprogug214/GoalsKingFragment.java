package com.uts.uasmobprogug214;

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

import com.uts.uasmobprogug214.models.GoalKings;
import com.uts.uasmobprogug214.models.ResultGoalKings;
import com.uts.uasmobprogug214.models.Results;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GoalsKingFragment extends Fragment {

    private Context ctx;
    private Button btn1;
    private RecyclerView recyclerView1;
    private ApiInterface apiService;
    private List<GoalKings> data1;
    private ResultGoalKings result;
    private RecyclerViewGoalKings adapter;
    private Spinner spinnertipe;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_goals_king, container, false);
        ctx = rootView.getContext();

        recyclerView1 = rootView.findViewById(R.id.recyclerViewGoalKings);
        btn1 = rootView.findViewById(R.id.buttongoals);
        spinnertipe = rootView.findViewById(R.id.spinnerSearchBy);

        ArrayAdapter<CharSequence> TipeAdapter = ArrayAdapter.createFromResource(
                ctx,
                R.array.Tipe,
                android.R.layout.simple_spinner_item
        );
        TipeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnertipe.setAdapter(TipeAdapter);

        LinearLayoutManager manager = new LinearLayoutManager(ctx);
        recyclerView1.setLayoutManager(manager);
        recyclerView1.setHasFixedSize(true);

        apiService = ApiClient.getClient().create(ApiInterface.class);

        if (adapter != null) {
            adapter = null;
            data1.clear();
        }

        LoadData();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadData();
            }
        });

        return rootView;
    }

    public void LoadData() {
        String selectedType = spinnertipe.getSelectedItem().toString();
        Call<ResultGoalKings> getGoalKings = apiService.getGoalKings(selectedType);
        getGoalKings.enqueue(new Callback<ResultGoalKings>() {
            @Override
            public void onResponse(Call<ResultGoalKings> call, Response<ResultGoalKings> response) {
                if (response.code() != 200) {
                    Toast.makeText(ctx, "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                } else {
                    if (response.body() == null){

                    }else{
                        result = response.body();
                        data1 = result.getResult();
                        adapter = new RecyclerViewGoalKings(ctx, data1);
                        recyclerView1.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResultGoalKings> call, Throwable t) {
                Toast.makeText(ctx, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }





//    private void updateRecyclerView(List<GoalKings> goalKingsList) {
//        if (adapter == null) {
//            adapter = new RecyclerViewGoalKings(ctx, goalKingsList);
//            recyclerView1.setAdapter(adapter);
//        } else {
//            adapter.setGoalKingsList(goalKingsList);
//            adapter.notifyDataSetChanged();
//        }
//    }

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
}