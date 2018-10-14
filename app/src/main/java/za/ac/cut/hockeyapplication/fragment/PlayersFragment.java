package za.ac.cut.hockeyapplication.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.backendless.Backendless;
import com.backendless.async.callback.BackendlessCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;

import java.util.List;

import za.ac.cut.hockeyapplication.R;
import za.ac.cut.hockeyapplication.activity.SelectPlayerActivity;
import za.ac.cut.hockeyapplication.adapter.PlayersAdapter;
import za.ac.cut.hockeyapplication.model.Player;

public class PlayersFragment extends BaseFragment implements PlayersAdapter.PlayerClickListener {
    public static final String TAG = PlayersFragment.class.getSimpleName();
    public static final String EXTRA_VIEWABLE = "EXTRA_VIEWABLE";

    private PlayersAdapter adapter;
    private boolean viewable = false;

    public static PlayersFragment newInstance(boolean viewable) {
        Bundle args = new Bundle();
        args.putBoolean(EXTRA_VIEWABLE, viewable);
        PlayersFragment fragment = new PlayersFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState
    ) {
        View rootView = inflater.inflate(R.layout.fragment_players, container, false);

        if (getArguments().containsKey(EXTRA_VIEWABLE)) {
            viewable = getArguments().getBoolean(EXTRA_VIEWABLE);
        }

        // Init recycler view
        RecyclerView recyclerView = rootView.findViewById(R.id.players_recycler_view);
        adapter = new PlayersAdapter(this);
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    @Override
    public String getTitle() {
        return TAG;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadPlayers();
    }

    private void loadPlayers() {
        showLoadingProgress();

        DataQueryBuilder query = DataQueryBuilder.create();
        query.setRelated("team");
        Backendless.Persistence.of(Player.class).find(query, new BackendlessCallback<List<Player>>() {
            @Override
            public void handleResponse(List<Player> players) {
                hideLoadingProgress();
                if (players != null && !players.isEmpty()) {
                    adapter.setData(players);
                } else {
                    // TODO
                    Log.e(TAG, "no players found");
                }
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                hideLoadingProgress();
                // TODO
                if (fault != null) {
                    Log.e(TAG, "Error: " + fault.getMessage());
                }
            }
        });
    }

    @Override
    public void onPlayerClick(Player player) {
        if (player != null) {
            if(viewable) {
                // TODO
            } else if(requireActivity() instanceof SelectPlayerActivity) {
                ((SelectPlayerActivity) requireActivity()).setResult(player);
            }
        }
    }
}
