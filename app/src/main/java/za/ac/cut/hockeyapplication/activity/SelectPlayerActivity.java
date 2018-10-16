package za.ac.cut.hockeyapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;

import za.ac.cut.hockeyapplication.R;
import za.ac.cut.hockeyapplication.fragment.PlayersFragment;
import za.ac.cut.hockeyapplication.model.Player;

public class SelectPlayerActivity extends BaseActivity {

    public static final int RC_SELECT_PLAYER = 500;
    public static final String EXTRA_PLAYER = "EXTRA_PLAYER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_player);

        // Set toolbar
        Toolbar toolbar = findViewById(R.id.toolbar_include);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.title_activity_select_player);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Add fragment
        Fragment teamsFragment = getSupportFragmentManager().findFragmentByTag(PlayersFragment.TAG);
        if (teamsFragment == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.container, PlayersFragment.newInstance(false));
            transaction.commit();
        }
    }

    public void setResult(Player player) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_PLAYER, player);
        setResult(RESULT_OK, intent);
        finish();
    }

    public static void start(Activity activity) {
        activity.startActivityForResult(new Intent(activity, SelectPlayerActivity.class), RC_SELECT_PLAYER);
    }
}
