package za.ac.cut.hockeyapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import za.ac.cut.hockeyapplication.R;
import za.ac.cut.hockeyapplication.fragment.TeamsFragment;
import za.ac.cut.hockeyapplication.model.Team;

public class SelectTeamActivity extends BaseActivity {

    public static final int RC_SELECT_TEAM = 200;
    public static final String EXTRA_TEAM = "EXTRA_TEAM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_team);

        // Add fragment
        Fragment teamsFragment = getSupportFragmentManager().findFragmentByTag(TeamsFragment.TAG);
        if (teamsFragment == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.container, TeamsFragment.newInstance(true));
            transaction.commit();
        }
    }

    public void setResult(Team team) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_TEAM, team);
        setResult(RESULT_OK, intent);
        finish();
    }

    public static void start(Activity activity) {
        activity.startActivityForResult(new Intent(activity, SelectTeamActivity.class), RC_SELECT_TEAM);
    }
}
