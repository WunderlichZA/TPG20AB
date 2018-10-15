package za.ac.cut.hockeyapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import za.ac.cut.hockeyapplication.R;

public class CoachMenuActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_menu);
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
            case R.id.button_logout_coach:
                logout();
                break;
        }
    }

    public static void start(Activity activity) {
        activity.startActivity(new Intent(activity, CoachMenuActivity.class));
    }
}
