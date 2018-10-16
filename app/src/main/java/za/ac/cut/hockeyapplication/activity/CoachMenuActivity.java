package za.ac.cut.hockeyapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import za.ac.cut.hockeyapplication.R;

public class CoachMenuActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_menu);

        // Set toolbar
        Toolbar toolbar = findViewById(R.id.toolbar_include);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.title_activity_coach_menu);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.add_player_button:
                AddPlayerActivity.start(CoachMenuActivity.this);
                break;
            case R.id.my_players_button:
                MyPlayersActivity.start(CoachMenuActivity.this);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public static void start(Activity activity) {
        activity.startActivity(new Intent(activity, CoachMenuActivity.class));
    }
}
