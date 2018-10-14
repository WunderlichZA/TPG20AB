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

import java.util.List;

import za.ac.cut.hockeyapplication.R;
import za.ac.cut.hockeyapplication.activity.SelectTeamActivity;
import za.ac.cut.hockeyapplication.adapter.TeamsAdapter;
import za.ac.cut.hockeyapplication.model.Team;

public class TeamsFragment extends BaseFragment implements TeamsAdapter.TeamClickListener {
    public static final String TAG = TeamsFragment.class.getSimpleName();
    public static final String EXTRA_SELECTABLE = "EXTRA_SELECTABLE";

    private TeamsAdapter adapter;

    public static TeamsFragment newInstance(boolean selectable) {
        Bundle args = new Bundle();
        args.putBoolean(EXTRA_SELECTABLE, selectable);
        TeamsFragment fragment = new TeamsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState
    ) {
        View rootView = inflater.inflate(R.layout.fragment_teams, container, false);

        boolean selectable = false;
        if (getArguments().containsKey(EXTRA_SELECTABLE)) {
            selectable = getArguments().getBoolean(EXTRA_SELECTABLE);
        }

        // Init recycler view
        RecyclerView recyclerView = rootView.findViewById(R.id.teams_recycler_view);
        adapter = new TeamsAdapter(selectable ? this : null);
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
        loadTeams();
    }

    private void loadTeams() {
        showLoadingProgress();
        Backendless.Data.of(Team.class).find(new BackendlessCallback<List<Team>>() {
            @Override
            public void handleResponse(List<Team> teams) {
                hideLoadingProgress();
                if (teams != null && !teams.isEmpty()) {
                    adapter.setData(teams);
                } else {
                    // TODO
                    Log.e(TAG, "no teams found");
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
    public void onTeamClick(Team team) {
        if (team != null && requireActivity() instanceof SelectTeamActivity) {
            ((SelectTeamActivity) requireActivity()).setResult(team);
        }
    }
}
