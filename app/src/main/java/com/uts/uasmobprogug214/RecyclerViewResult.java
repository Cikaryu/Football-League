package com.uts.uasmobprogug214;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uts.uasmobprogug214.models.ModelResults;

import java.util.List;

public class RecyclerViewResult extends RecyclerView.Adapter<RecyclerViewResult.ViewHolder>{
    Context ctx;
    private static ClickListener clickListener;
    List<ModelResults> data;

    // Constructor for the RecyclerViewResult class
    public RecyclerViewResult (Context context, List<ModelResults> dataResults){
        ctx = context;
        data = dataResults;
    }

    // Interface for click listener
    public interface ClickListener {
        void onItemClick(int position, View view);
    }

    // ViewHolder class for holding the views
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txtDate, txtAway, txtScore, txtHome;
        // Constructor for the ViewHolder
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtAway = itemView.findViewById(R.id.txtAway);
            txtScore = itemView.findViewById(R.id.txtScore);
            txtHome = itemView.findViewById(R.id.txtHome);
            itemView.setOnClickListener(this);
        }
        // Implementation of onClick method for item click
        @Override
        public void onClick(View view) {
            clickListener.onItemClick(getAdapterPosition(), view);
        }
    }

    // onCreateViewHolder method to create a new ViewHolder instance
    @NonNull
    @Override
    public RecyclerViewResult.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v = inflater.inflate(R.layout.list_data_result, parent, false);
        return new ViewHolder(v);
    }

    // onBindViewHolder method to bind data to the ViewHolder
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewResult.ViewHolder holder, int position) {
        ModelResults result = data.get(position);
        Log.d("RecyclerViewResult", "Binding data: " + result.toString());
        holder.txtDate.setText(result.getDate());
        holder.txtAway.setText(result.getAway());
        String score = result.getScore();
        if (score != null && !score.equals("undefined-undefined")) {
            holder.txtScore.setText(result.getScore());
        } else {
            holder.txtScore.setText("- - -");
        }
        holder.txtHome.setText(result.getHome());
    }
    // getItemCount method to get the total number of items in the data list
    @Override
    public int getItemCount() {
        return data.size();
    }

}
