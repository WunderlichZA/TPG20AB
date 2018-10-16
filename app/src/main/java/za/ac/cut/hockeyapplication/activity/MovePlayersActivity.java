package za.ac.cut.hockeyapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import java.util.ArrayList;

import za.ac.cut.hockeyapplication.R;
import za.ac.cut.hockeyapplication.model.Player;
import za.ac.cut.hockeyapplication.model.Team;

public class MovePlayersActivity extends BaseActivity {

    private static final String TAG = MovePlayersActivity.class.getSimpleName();

    private TextInputLayout playerNameTextInputLayout;
    private TextInputEditText playerNameEditText;
    private TextView playerCurrentTeamTextView;
    private TextInputLayout playerTeamNameTextInputLayout;
    private TextInputEditText playerTeamNameEditText;

    private Player player;
    private Team team;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_players);

        // Set toolbar
        Toolbar toolbar = findViewById(R.id.toolbar_include);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.title_activity_move_players);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        playerNameTextInputLayout = findViewById(R.id.player_name_text_input);
        playerNameTextInputLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectPlayerActivity.start(MovePlayersActivity.this);
            }
        });
        playerNameEditText = findViewById(R.id.player_name_edit_text);
        playerCurrentTeamTextView = findViewById(R.id.player_current_team);
        playerTeamNameTextInputLayout = findViewById(R.id.player_team_text_input);
        playerTeamNameTextInputLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectTeamActivity.start(MovePlayersActivity.this);
            }
        });
        playerTeamNameEditText = findViewById(R.id.player_team_edit_text);

        MaterialButton movePlayerButton = findViewById(R.id.move_player_button);
        movePlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (areValidFields()) {
                    movePlayer();
                }
            }
        });
    }

    private void setPlayerInfo() {
        if (player != null) {
            playerNameEditText.setText(player.getFullName());
            playerCurrentTeamTextView.setText("Currently in the " + player.getTeam()
                                                                          .getTeamName() + " team.");
            if (team != null) {
                playerTeamNameEditText.setText(team.getTeamName());
            }
        }
    }

    private boolean areValidFields() {
        boolean valid = true;
        String error = null;

        if (player == null) {
            error = "Select player";
            valid = false;
        }
        playerNameTextInputLayout.setError(error);

        // Reset error
        error = null;

        if (team == null) {
            error = "Select team";
            valid = false;
        }
        playerTeamNameTextInputLayout.setError(error);

        return valid;
    }

    private void movePlayer() {
        showLoadingProgress();
        player.setTeam(team);
        ArrayList<Team> teams = new ArrayList<>();
        teams.add(team);
        Backendless.Data.of(Player.class)
                        .setRelation(player, "team:Team:1", teams, new AsyncCallback<Integer>() {
                            @Override
                            public void handleResponse(Integer response) {
                                hideLoadingProgress();
                                setPlayerInfo();
                                Toast.makeText(MovePlayersActivity.this, "Player moved successfully", Toast.LENGTH_SHORT)
                                     .show();
                            }

                            @Override
                            public void handleFault(BackendlessFault fault) {
                                hideLoadingProgress();
                                Toast.makeText(MovePlayersActivity.this, fault.getMessage(), Toast.LENGTH_SHORT)
                                     .show();
                            }
                        });
    }

    @Override
    protected void onActivityResult(
            int requestCode, int resultCode, @Nullable Intent data
    ) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            switch (requestCode) {
                case SelectPlayerActivity.RC_SELECT_PLAYER:
                    if (data.hasExtra(SelectPlayerActivity.EXTRA_PLAYER)) {
                        player = (Player) data.getSerializableExtra(SelectPlayerActivity.EXTRA_PLAYER);
                        setPlayerInfo();
                    }
                    break;
                case SelectTeamActivity.RC_SELECT_TEAM:
                    if (data.hasExtra(SelectTeamActivity.EXTRA_TEAM)) {
                        team = (Team) data.getSerializableExtra(SelectTeamActivity.EXTRA_TEAM);
                        setPlayerInfo();
                    }
                    break;
            }
        }
    }

    public static void start(Activity activity) {
        activity.startActivity(new Intent(activity, MovePlayersActivity.class));
    }
}
