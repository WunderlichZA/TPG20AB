package za.ac.cut.hockeyapplication.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import za.ac.cut.hockeyapplication.R;
import za.ac.cut.hockeyapplication.model.Opponent;

public class OpponentsAdapter extends RecyclerView.Adapter<OpponentsAdapter.OpponentsViewHolder> {

    private List<Opponent> opponents;

    public OpponentsAdapter(List<Opponent> opponents) {
        this.opponents = opponents;
    }

    @NonNull
    @Override
    public OpponentsAdapter.OpponentsViewHolder onCreateViewHolder(
            @NonNull ViewGroup viewGroup, int position
    ) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.opponents_row_layout, viewGroup, false);
        return new OpponentsAdapter.OpponentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull OpponentsAdapter.OpponentsViewHolder viewHolder, int position
    ) {
        viewHolder.bind(opponents.get(position));
    }

    @Override
    public int getItemCount() {
        return opponents.size();
    }

    public void setData(List<Opponent> opponents) {
        this.opponents = opponents;
        notifyDataSetChanged();
    }

    class OpponentsViewHolder extends RecyclerView.ViewHolder {

        private TextView name;

        public OpponentsViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
        }

        public void bind(Opponent opponent) {
            name.setText(opponent.getName());
        }
    }
}
