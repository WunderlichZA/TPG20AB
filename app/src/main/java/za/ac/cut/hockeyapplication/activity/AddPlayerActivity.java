package za.ac.cut.hockeyapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.View;

import com.backendless.Backendless;

import java.util.ArrayList;

import za.ac.cut.hockeyapplication.R;
import za.ac.cut.hockeyapplication.model.MedicalAidInfo;
import za.ac.cut.hockeyapplication.model.Player;
import za.ac.cut.hockeyapplication.model.Team;

public class AddPlayerActivity extends BaseActivity {

    private static final String TAG = "Add Player";

    private TextInputLayout playerNameTextInputLayout;
    private TextInputEditText playerNameEditText;
    private TextInputLayout playerSurnameTextInputLayout;
    private TextInputEditText playerSurnameEditText;
    private TextInputEditText selectTeamEditText;
    private TextInputLayout selectTeamTextInputLayout;

    private Player player;
    private Team team;
    private MedicalAidInfo medicalInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);

        playerNameEditText = findViewById(R.id.player_name_edit_text);
        playerSurnameEditText = findViewById(R.id.player_surname_edit_text);
        playerNameTextInputLayout = findViewById(R.id.player_name_text_input);
        playerSurnameTextInputLayout = findViewById(R.id.player_surname_text_input);
        selectTeamEditText = findViewById(R.id.select_team_edit_text);
        selectTeamTextInputLayout = findViewById(R.id.select_team_text_input);
        selectTeamTextInputLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectTeamActivity.start(AddPlayerActivity.this);
            }
        });

        MaterialButton buttonSavePlayer = findViewById(R.id.save_player_button);
        buttonSavePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (areValidFields()) {
                    savePlayer();
                }
            }
        });

        MaterialButton buttonMedicalInfo = findViewById(R.id.add_medical_info_button);
        buttonMedicalInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MedicalInfoActivity.start(AddPlayerActivity.this, medicalInfo);
            }
        });
    }

    public void onClicked(View view) {
        if (view.getId() == R.id.add_medical_info_button) {

        }
    }

    private boolean areValidFields() {
        boolean valid = true;
        String error = null;

        if (TextUtils.isEmpty(playerNameEditText.getText().toString())) {
            error = "Enter player name";
            valid = false;
        }
        playerNameTextInputLayout.setError(error);
        // Reset error
        error = null;

        if (TextUtils.isEmpty(playerSurnameEditText.getText().toString())) {
            error = "Enter player surname";
            valid = false;
        }
        playerSurnameTextInputLayout.setError(error);
        // Reset error
        error = null;

        return valid;
    }

    private void setTeamName() {
        if (team != null) {
            selectTeamTextInputLayout.setError(null);
            selectTeamEditText.setText(team.getTeamName());
        }
    }

    private void savePlayer() {
        String playerName = playerNameEditText.getText().toString();
        String playerSurname = playerSurnameEditText.getText().toString();

        Player player = new Player();
        player.setName(playerName);
        player.setSurname(playerSurname);

        if(medicalInfo == null) {
            //medicalInfo = new MedicalAidInfo();
        }

        player.setMedicalAidInfo(medicalInfo);
        player.setTeam(team);

        new SavePlayerTask().execute(player);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            switch (requestCode) {
                case SelectTeamActivity.RC_SELECT_TEAM:
                    team = (Team) data.getSerializableExtra(SelectTeamActivity.EXTRA_TEAM);
                    setTeamName();
                    break;
                case MedicalInfoActivity.RC_ADD_MEDICAL_INFO:
                    medicalInfo = (MedicalAidInfo) data.getSerializableExtra(MedicalInfoActivity.EXTRA_MEDICAL_INFO);
                    break;
            }
        }
    }

    private class SavePlayerTask extends AsyncTask<Player, Void, Boolean> {

        @Override
        protected void onPreExecute() {
            showLoadingProgress();
        }

        @Override
        protected Boolean doInBackground(Player... players) {
            try {
                final Player player = players[0];

                // Save player
                Player savedPlayer = Backendless.Data.of(Player.class).save(player);
                ArrayList<Team> teamCollection = new ArrayList<>();
                teamCollection.add(player.getTeam());
                Backendless.Data.of(Player.class).addRelation(savedPlayer, "team:Team:1", teamCollection);

                if(player.getMedicalAidInfo() != null) {
                    // Save medical info
                    MedicalAidInfo savedMedicalInfo = Backendless.Data.of(MedicalAidInfo.class).save(player.getMedicalAidInfo());
                    ArrayList<Player> playerCollection = new ArrayList<>();
                    playerCollection.add(savedPlayer);
                    Backendless.Data.of(MedicalAidInfo.class).addRelation(savedMedicalInfo, "player:Player:1", playerCollection);
                    ArrayList<MedicalAidInfo> medicalInfoCollection = new ArrayList<>();
                    medicalInfoCollection.add(savedMedicalInfo);
                    Backendless.Data.of(Player.class).addRelation(savedPlayer, "medicalAidInfo:MedicalAidInfo:1", medicalInfoCollection);
                }

            } catch (Exception exception)  {
                exception.printStackTrace();
                return false;
            }

            return true;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            hideLoadingProgress();
            if(result != null && result) {
                finish();
            } else {
                // show error
            }
        }
    }

    public static void start(Activity activity) {
        activity.startActivity(new Intent(activity, AddPlayerActivity.class));
    }
}
