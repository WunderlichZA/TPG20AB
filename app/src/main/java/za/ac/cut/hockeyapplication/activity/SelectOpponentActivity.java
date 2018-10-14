package za.ac.cut.hockeyapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import za.ac.cut.hockeyapplication.R;
import za.ac.cut.hockeyapplication.fragment.OpponentsFragment;
import za.ac.cut.hockeyapplication.model.Opponent;

public class SelectOpponentActivity extends BaseActivity {

    public static final int RC_SELECT_OPPONENT = 300;
    public static final String EXTRA_OPPONENT = "EXTRA_OPPONENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_opponent);

        // Add fragment
        Fragment teamsFragment = getSupportFragmentManager().findFragmentByTag(OpponentsFragment.TAG);
        if (teamsFragment == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.container, OpponentsFragment.newInstance(true));
            transaction.commit();
        }
    }

    public void setResult(Opponent opponent) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_OPPONENT, opponent);
        setResult(RESULT_OK, intent);
        finish();
    }

    public static void start(Activity activity) {
        activity.startActivityForResult(new Intent(activity, SelectOpponentActivity.class), RC_SELECT_OPPONENT);
    }
}
