package com.uts.uasmobprogug214;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uts.uasmobprogug214.models.ModelGoalKings;

import java.util.List;

public class RecyclerViewGoalKings extends RecyclerView.Adapter<RecyclerViewGoalKings.ViewHolder> {

    Context ctx;

    private static ClickListener clickListener;
    List<ModelGoalKings> data;

    public RecyclerViewGoalKings(Context context, List<ModelGoalKings> dataGoalKings) {
        ctx = context;
        data = dataGoalKings;
    }



    public void setOnItemClickListener(ClickListener clickListener) {
        RecyclerViewGoalKings.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView1, textView2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.textViewPLayer);
            textView2 = itemView.findViewById(R.id.textViewGoal);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            clickListener.onItemClick(getAdapterPosition(), view);
        }
    }
    @NonNull
    @Override
    public RecyclerViewGoalKings.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext()
        );
        View view = inflater.inflate(R.layout.data_goal_kings, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ModelGoalKings goalKings = data.get(position);
        // Set the data to your TextViews here
        holder.textView1.setText(goalKings.getName());
        holder.textView2.setText(goalKings.getGoals());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    }


