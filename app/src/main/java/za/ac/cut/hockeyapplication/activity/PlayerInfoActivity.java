package za.ac.cut.hockeyapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import za.ac.cut.hockeyapplication.R;
import za.ac.cut.hockeyapplication.model.MedicalAidInfo;
import za.ac.cut.hockeyapplication.model.Player;

public class PlayerInfoActivity extends BaseActivity {

    private static final String TAG = PlayerInfoActivity.class.getSimpleName();
    public static final String EXTRA_PLAYER= "EXTRA_PLAYER";

    private Player player;
    private MedicalAidInfo medicalInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_info);

        if(getIntent().hasExtra(EXTRA_PLAYER)) {
            player = (Player) getIntent().getSerializableExtra(EXTRA_PLAYER);
        } else {
            finish();
        }
    }

    private void updatePlayer() {
        showLoadingProgress();

        Backendless.Persistence.save(player, new AsyncCallback<Player>() {
            @Override
            public void handleResponse(Player savedPlayer) {
                savedPlayer.setMedicalAidInfo(medicalInfo);
                Backendless.Persistence.save(player, new AsyncCallback<Player>() {
                    @Override
                    public void handleResponse(Player response) {
                        hideLoadingProgress();
                        finish();
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        hideLoadingProgress();
                        Log.e(TAG, "Error: " + fault.getMessage());
                    }
                });
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                hideLoadingProgress();
                Log.e(TAG, "Error: " + fault.getMessage());
            }
        });
    }

    public static void start(Activity activity, Player player) {
        Intent intent = new Intent(activity, PlayerInfoActivity.class);
        intent.putExtra(EXTRA_PLAYER, player);
        activity.startActivity(intent);
    }
}
