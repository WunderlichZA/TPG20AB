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

import java.util.ArrayList;
import java.util.List;

import za.ac.cut.hockeyapplication.R;
import za.ac.cut.hockeyapplication.adapter.TeamsAdapter;
import za.ac.cut.hockeyapplication.model.Team;

public class TeamsFragment extends BaseFragment {

    private TeamsAdapter adapter;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState
    ) {
        View rootView = inflater.inflate(R.layout.fragment_teams, container, false);

        // Init recycler view
        RecyclerView recyclerView = rootView.findViewById(R.id.teams_recycler_view);
        adapter = new TeamsAdapter(new ArrayList<Team>());
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    @Override
    public String getTitle() {
        return "Teams";
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
                    Log.e("Teams", "no teams found");
                }
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                hideLoadingProgress();
                // TODO
                if(fault != null) {
                    Log.e("Teams", "Error: " + fault.getMessage());
                }
            }
        });
    }

}
