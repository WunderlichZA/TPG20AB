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
import za.ac.cut.hockeyapplication.model.Player;

public class PlayersAdapter extends RecyclerView.Adapter<PlayersAdapter.PlayerViewHolder> {

    private PlayerClickListener playerClickListener;
    private List<Player> players = new ArrayList<>();

    public PlayersAdapter(PlayerClickListener playerClickListener) {
        this.playerClickListener = playerClickListener;
    }

    @NonNull
    @Override
    public PlayersAdapter.PlayerViewHolder onCreateViewHolder(
            @NonNull ViewGroup viewGroup, int position
    ) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.player_row_layout, viewGroup, false);
        return new PlayersAdapter.PlayerViewHolder(view, playerClickListener);
    }

    @Override
    public void onBindViewHolder(
            @NonNull PlayersAdapter.PlayerViewHolder viewHolder, int position
    ) {
        viewHolder.bind(players.get(position));
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    public void setData(List<Player> players) {
        this.players = players;
        notifyDataSetChanged();
    }

    public interface PlayerClickListener {
        void onPlayerClick(Player player);
    }

    class PlayerViewHolder extends RecyclerView.ViewHolder {

        private PlayerClickListener playerClickListener;
        private TextView name;
        private TextView team;
        private Player player;

        public PlayerViewHolder(@NonNull View itemView, PlayerClickListener clickListener) {
            super(itemView);

            this.playerClickListener = clickListener;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (playerClickListener != null && playerClickListener != null) {
                        playerClickListener.onPlayerClick(player);
                    }
                }
            });
            itemView.setClickable(playerClickListener != null);
            name = itemView.findViewById(R.id.name);
            team = itemView.findViewById(R.id.team);
        }

        public void bind(Player player) {
            this.player = player;
            name.setText(player.getFullName());
            if (player.getTeam() != null) {
                team.setText("Team: " + player.getTeam().getTeamName());
            }
        }
    }
}
