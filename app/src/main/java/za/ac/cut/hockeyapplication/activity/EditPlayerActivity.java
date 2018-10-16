package za.ac.cut.hockeyapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import za.ac.cut.hockeyapplication.R;
import za.ac.cut.hockeyapplication.model.Player;

public class EditPlayerActivity extends Activity {

    private static final String TAG = EditPlayerActivity.class.getSimpleName();
    public static final int RC_EDIT_PLAYER = 600;
    public static final String EXTRA_PLAYER = "EXTRA_PLAYER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_player);
    }

    public static void start(Activity activity, Player player) {
        Intent intent = new Intent(activity, EditPlayerActivity.class);
        intent.putExtra(EXTRA_PLAYER, player);
        activity.startActivity(intent);
    }
}
