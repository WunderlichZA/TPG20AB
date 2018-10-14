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
import za.ac.cut.hockeyapplication.adapter.OpponentsAdapter;
import za.ac.cut.hockeyapplication.model.Opponent;

public class OpponentsFragment extends BaseFragment {

    private OpponentsAdapter adapter;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState
    ) {
        View rootView = inflater.inflate(R.layout.fragment_opponents, container, false);

        // Init recycler view
        RecyclerView recyclerView = rootView.findViewById(R.id.opponents_recycler_view);
        adapter = new OpponentsAdapter(new ArrayList<Opponent>());
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    @Override
    public String getTitle() {
        return "Opponents";
    }

    @Override
    public void onResume() {
        super.onResume();
        loadOpponents();
    }

    private void loadOpponents() {
        showLoadingProgress();
        Backendless.Data.of(Opponent.class).find(new BackendlessCallback<List<Opponent>>() {
            @Override
            public void handleResponse(List<Opponent> opponents) {
                hideLoadingProgress();
                if (opponents != null && !opponents.isEmpty()) {
                    adapter.setData(opponents);
                } else {
                    // TODO
                    Log.e("Opponents", "no teams found");
                }
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                hideLoadingProgress();
                // TODO
                if (fault != null) {
                    Log.e("Opponents", "Error: " + fault.getMessage());
                }
            }
        });
    }
}
