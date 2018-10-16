package za.ac.cut.hockeyapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;

import za.ac.cut.hockeyapplication.R;
import za.ac.cut.hockeyapplication.fragment.PlayersFragment;

public class MyPlayersActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_players);

        // Set toolbar
        Toolbar toolbar = findViewById(R.id.toolbar_include);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.title_activity_my_players);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Add fragment
        Fragment teamsFragment = getSupportFragmentManager().findFragmentByTag(PlayersFragment.TAG);
        if (teamsFragment == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.container, PlayersFragment.newInstance(true));
            transaction.commit();
        }
    }

    public static void start(Activity activity) {
        activity.startActivity(new Intent(activity, MyPlayersActivity.class));
    }
}
