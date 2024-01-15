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
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uts.uasmobprogug214.models.ResultResults;
import com.uts.uasmobprogug214.models.Results;

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
    List<Results> data1;
    RecyclerViewResult adapter;
    Spinner spinTeam;

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

        // Inisialisasi elemen UI
        btn1 = view.findViewById(R.id.btnSubmit);
        recyclerView1 = view.findViewById(R.id.recyclerView1);
        spinTeam = view.findViewById(R.id.spinTeam);

        // Isi Spinner Teams
        ArrayAdapter<CharSequence> teamsAdapter = ArrayAdapter.createFromResource(
                ctx,
                R.array.teams,
                android.R.layout.simple_spinner_item);
        teamsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinTeam.setAdapter(teamsAdapter);

        teamsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinTeam.setAdapter(teamsAdapter);

        LinearLayoutManager manager = new LinearLayoutManager(ctx);
        recyclerView1.setLayoutManager(manager);
        recyclerView1.setHasFixedSize(true);

        if (adapter != null) {
            adapter = null;
            data1.clear();
        }

        apiService = ApiClient.getClient().create(ApiInterface.class);

        // panggil function load data
        loadData();
        return view;
    }


    public void loadData(){
        String selectedTeam = spinTeam.getSelectedItem().toString();

        Call<ResultResults> getResultByTeam = apiService.getResults(selectedTeam);

        getResultByTeam.enqueue(new Callback<ResultResults>() {
            @Override
            public void onResponse(Call<ResultResults> call, Response<ResultResults> response) {
                if (response.code() != 200){
                    Toast.makeText(ctx, "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                } else {
                    if (response.body() == null){

                    } else {
                        result = response.body();
                        data1 = result.getResult();
                        Log.d("data Results", data1.toString());
                        adapter = new RecyclerViewResult(ctx, data1);
                        recyclerView1.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResultResults> call, Throwable t) {
                Toast.makeText(ctx, "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}