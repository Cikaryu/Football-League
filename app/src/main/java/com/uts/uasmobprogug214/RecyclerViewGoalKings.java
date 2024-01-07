package com.uts.uasmobprogug214;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.uts.uasmobprogug214.models.GoalKings;

import java.util.List;

public class RecyclerViewGoalKings extends RecyclerView.Adapter<RecyclerViewGoalKings.ViewHolder> {

    Context ctx;
    private static Clicklistener clickListener;
    List<GoalKings> data;

    public RecyclerViewGoalKings(Context context, List<GoalKings> dataGoalKings) {
        ctx = context;
        data = dataGoalKings;
    }

    public void setOnItemClickListener(Clicklistener clickListener) {
        RecyclerViewGoalKings.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_goal_kings, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GoalKings goalKings = data.get(position);

        // Set the data to your TextViews here
        holder.textViewColumn1.setText(goalKings.getName());
        holder.textViewColumn2.setText(goalKings.getPlay());
        holder.textViewColumn3.setText(goalKings.getGoals());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textViewColumn1, textViewColumn2, textViewColumn3;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewColumn1 = itemView.findViewById(R.id.textViewColumnName);
            textViewColumn2 = itemView.findViewById(R.id.textViewColumnPlay);
            textViewColumn3 = itemView.findViewById(R.id.textViewColumnGoals);


            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) {
                clickListener.onItemClick(getAdapterPosition(), view);
            }
        }
    }
    public interface Clicklistener {
        void onItemClick(int position, View v);
    }
}
