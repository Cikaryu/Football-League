package com.uts.uasmobprogug214;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.uts.uasmobprogug214.models.League;

import java.util.List;

public class ReyclerViewLeagueCustomAdapter extends RecyclerView.Adapter<ReyclerViewLeagueCustomAdapter.ViewHolder> {
    private List<League> leagueList;

    public ReyclerViewLeagueCustomAdapter(List<League> leagueList) {
        this.leagueList = leagueList;
    }

    public void setLeagueList(List<League> newLeagueList) {
        this.leagueList = newLeagueList;
        notifyDataSetChanged(); // Notify the adapter that the data has changed
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_data_league, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        League league = leagueList.get(position);

        // Set data to views
        holder.txtRank.setText(league.getRank());
        holder.txtTeam.setText(league.getTeam());
        holder.txtWin.setText(league.getWin());
        holder.txtLose.setText(league.getLose());
        holder.txtPoints.setText(league.getPoint());
        holder.txtPlay.setText(league.getPlay());
    }

    @Override
    public int getItemCount() {
        return leagueList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtRank, txtTeam, txtWin, txtLose, txtPoints, txtPlay;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtRank = itemView.findViewById(R.id.txtRank);
            txtTeam = itemView.findViewById(R.id.txtTeam);
            txtWin = itemView.findViewById(R.id.txtWin);
            txtLose = itemView.findViewById(R.id.txtLose);
            txtPoints = itemView.findViewById(R.id.txtPoints);
            txtPlay = itemView.findViewById(R.id.txtPlay);
        }
    }
}

