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
import za.ac.cut.hockeyapplication.model.Opponent;

public class OpponentsAdapter extends RecyclerView.Adapter<OpponentsAdapter.OpponentsViewHolder> {

    private OpponentsClickListener opponentsClickListener;
    private List<Opponent> opponents = new ArrayList<>();

    public OpponentsAdapter(OpponentsClickListener opponentsClickListener) {
        this.opponentsClickListener = opponentsClickListener;
    }

    @NonNull
    @Override
    public OpponentsAdapter.OpponentsViewHolder onCreateViewHolder(
            @NonNull ViewGroup viewGroup, int position
    ) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.opponents_row_layout, viewGroup, false);
        return new OpponentsAdapter.OpponentsViewHolder(view, opponentsClickListener);
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

    public interface OpponentsClickListener {
        void onOpponentClick(Opponent opponent);
    }

    class OpponentsViewHolder extends RecyclerView.ViewHolder {

        private OpponentsClickListener opponentsClickListener;
        private TextView name;
        private Opponent opponent;

        public OpponentsViewHolder(@NonNull View itemView, OpponentsClickListener clickListener) {
            super(itemView);

            this.opponentsClickListener = clickListener;
            name = itemView.findViewById(R.id.name);
            name.setClickable(opponentsClickListener != null);
            name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (opponentsClickListener != null && opponent != null) {
                        opponentsClickListener.onOpponentClick(opponent);
                    }
                }
            });
        }

        public void bind(Opponent opponent) {
            this.opponent = opponent;
            name.setText(opponent.getName());
        }
    }
}
