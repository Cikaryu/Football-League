package com.uts.uasmobprogug214;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.uts.uasmobprogug214.models.GoalKings;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GoalsKingFragment extends Fragment {

    Context ctx;
    Button btn1;
    RecyclerView recyclerView1;
    ApiInterface apiService;
    List<GoalKings> data1;
    RecyclerViewGoalKings adapter;
    Spinner spinnertipe;

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


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchGoals();
            }
        });

        return rootView;
    }




    private void searchGoals() {
        String selectedType = spinnertipe.getSelectedItem().toString();
        Call<List<GoalKings>> call = apiService.getGoalKings(selectedType);

         call.enqueue(new Callback<List<GoalKings>>() {
            @Override
            public void onResponse(Call<List<GoalKings>> call, Response<List<GoalKings>> response) {
                if (response.isSuccessful()) {
                    data1 = response.body();
                    updateRecyclerView();
                } else {
                    // Handle error
                }
            }

            @Override
            public void onFailure(Call<List<GoalKings>> call, Throwable t) {
                // Handle failure
            }
         });


    }

    private void updateRecyclerView() {
        adapter = new RecyclerViewGoalKings(ctx, data1);
        recyclerView1.setAdapter(adapter);
    }

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
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
    }


}