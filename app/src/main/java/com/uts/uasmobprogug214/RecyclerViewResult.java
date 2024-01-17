package com.uts.uasmobprogug214;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uts.uasmobprogug214.models.ModelResults;

import java.util.List;

public class RecyclerViewResult extends RecyclerView.Adapter<RecyclerViewResult.ViewHolder>{
    Context ctx;
    private static ClickListener clickListener;
    List<ModelResults> data;

    public RecyclerViewResult (Context context, List<ModelResults> dataResults){
        ctx = context;
        data = dataResults;
    }

    public void setOnItemClickListener(ClickListener clickListener){
        RecyclerViewResult.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View view);
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txtDate, txtAway, txtScore, txtHome;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtAway = itemView.findViewById(R.id.txtAway);
            txtScore = itemView.findViewById(R.id.txtScore);
            txtHome = itemView.findViewById(R.id.txtHome);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            clickListener.onItemClick(getAdapterPosition(), view);
        }
    }

    @NonNull
    @Override
    public RecyclerViewResult.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v = inflater.inflate(R.layout.result_detail_data, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewResult.ViewHolder holder, int position) {
        ModelResults result = data.get(position);
        holder.txtDate.setText(result.getDate());
        holder.txtAway.setText(result.getAway());
        holder.txtScore.setText(result.getScore());
        holder.txtHome.setText(result.getHome());
    }
    @Override
    public int getItemCount() {
        return data.size();
    }
}
