package com.uts.uasmobprogug214;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.uts.uasmobprogug214.models.ModelLeague;

import java.util.List;

public class RecyclerViewLeague extends RecyclerView.Adapter<RecyclerViewLeague.ViewHolder> {
    List<ModelLeague> data;
    private static ClickListener clickListener;
    Context ctx;

    public RecyclerViewLeague(Context context, List<ModelLeague> dataLeague) {
        ctx = context;
        data = dataLeague;
    }

    public void setOnItemClickListener(ClickListener clickListener){
        RecyclerViewLeague.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txtRank, txtTeam, txtWin, txtLose, txtPoints, txtPlay;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtRank = itemView.findViewById(R.id.txtRank);
            txtTeam = itemView.findViewById(R.id.txtTeam);
            txtWin = itemView.findViewById(R.id.txtWin);
            txtLose = itemView.findViewById(R.id.txtLose);
            txtPoints = itemView.findViewById(R.id.txtPoints);
            txtPlay = itemView.findViewById(R.id.txtPlay);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            clickListener.onItemClick(getAdapterPosition(), view);
        }
    }

    @NonNull
    @Override
    public RecyclerViewLeague.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v = inflater.inflate(R.layout.list_data_league, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ModelLeague modelLeague = data.get(position);

        // Set data to views
        holder.txtRank.setText(modelLeague.getRank());
        holder.txtTeam.setText(modelLeague.getTeam());
        holder.txtWin.setText(modelLeague.getWin());
        holder.txtLose.setText(modelLeague.getLose());
        holder.txtPoints.setText(modelLeague.getPoint());
        holder.txtPlay.setText(modelLeague.getPlay());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}


