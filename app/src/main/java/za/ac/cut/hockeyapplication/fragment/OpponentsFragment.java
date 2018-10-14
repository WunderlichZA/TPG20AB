package za.ac.cut.hockeyapplication.fragment;

import android.content.Intent;
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
import za.ac.cut.hockeyapplication.activity.SelectOpponentActivity;
import za.ac.cut.hockeyapplication.adapter.OpponentsAdapter;
import za.ac.cut.hockeyapplication.model.Opponent;

public class OpponentsFragment extends BaseFragment implements OpponentsAdapter.OpponentsClickListener {
    public static final String TAG = OpponentsFragment.class.getSimpleName();
    public static final String EXTRA_SELECTABLE = "EXTRA_SELECTABLE";

    private OpponentsAdapter adapter;

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
        View rootView = inflater.inflate(R.layout.fragment_opponents, container, false);

        boolean selectable = false;
        if (getArguments().containsKey(EXTRA_SELECTABLE)) {
            selectable = getArguments().getBoolean(EXTRA_SELECTABLE);
        }

        // Init recycler view
        RecyclerView recyclerView = rootView.findViewById(R.id.opponents_recycler_view);
        adapter = new OpponentsAdapter(selectable ? this : null);
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
                    Log.e(TAG, "no opponents found");
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e("Add Opp", "onActivityResult");
    }

    @Override
    public void onOpponentClick(Opponent opponent) {
        if (opponent != null && requireActivity() instanceof SelectOpponentActivity) {
            ((SelectOpponentActivity) requireActivity()).setResult(opponent);
        }
    }
}
