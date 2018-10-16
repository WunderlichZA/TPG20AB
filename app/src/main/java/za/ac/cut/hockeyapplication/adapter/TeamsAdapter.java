package za.ac.cut.hockeyapplication.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import za.ac.cut.hockeyapplication.R;
import za.ac.cut.hockeyapplication.model.Team;

public class TeamsAdapter extends RecyclerView.Adapter<TeamsAdapter.TeamViewHolder> {

    private TeamClickListener teamClickListener;
    private List<Team> teams = new ArrayList<>();

    public TeamsAdapter(TeamClickListener teamClickListener) {
        this.teamClickListener = teamClickListener;
    }

    @NonNull
    @Override
    public TeamsAdapter.TeamViewHolder onCreateViewHolder(
            @NonNull ViewGroup viewGroup, int position
    ) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.team_row_layout, viewGroup, false);
        return new TeamsAdapter.TeamViewHolder(view, teamClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamsAdapter.TeamViewHolder viewHolder, int position) {
        viewHolder.bind(teams.get(position));
    }

    @Override
    public int getItemCount() {
        return teams.size();
    }

    public void setData(List<Team> teams) {
        this.teams = teams;
        notifyDataSetChanged();
    }

    public interface TeamClickListener {
        void onTeamClick(Team team);
    }

    class TeamViewHolder extends RecyclerView.ViewHolder {

        private TeamClickListener teamClickListener;
        private TextView name;
        private TextView coach;
        private Team team;

        public TeamViewHolder(@NonNull View itemView, TeamClickListener clickListener) {
            super(itemView);

            this.teamClickListener = clickListener;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (teamClickListener != null && team != null) {
                        teamClickListener.onTeamClick(team);
                    }
                }
            });
            itemView.setClickable(teamClickListener != null);
            name = itemView.findViewById(R.id.name);
            coach = itemView.findViewById(R.id.coach);
        }

        public void bind(Team team) {
            this.team = team;
            name.setText(team.getTeamName());
            if (team.getCoach() != null) {
                coach.setText("Coach: " + team.getCoach().getFullName());
            }
        }
    }
}
