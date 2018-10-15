package za.ac.cut.hockeyapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import za.ac.cut.hockeyapplication.R;
import za.ac.cut.hockeyapplication.model.MedicalInfo;
import za.ac.cut.hockeyapplication.model.Users;

public class AddPlayerActivity extends BaseActivity {

    MedicalInfo medicalInfo;

    private TextInputLayout playerNameTextInputLayout;
    private TextInputEditText playerNameEditText;
    private TextInputLayout playerSurnameTextInputLayout;
    private TextInputEditText playerSurnameEditText;
    private TextInputEditText selectTeamEditText;
    private TextInputLayout selectTeamTextInputLayout;

    MaterialButton buttonSavePlayer, buttonMedicalInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);

        buttonSavePlayer = findViewById(R.id.save_player_button);

        playerNameEditText = findViewById(R.id.player_name_edit_text);
        playerSurnameEditText = findViewById(R.id.player_surname_edit_text);
        playerNameTextInputLayout = findViewById(R.id.player_name_text_input);
        playerSurnameTextInputLayout = findViewById(R.id.player_surname_text_input);
        selectTeamEditText = findViewById(R.id.select_team_edit_text);
        selectTeamTextInputLayout = findViewById(R.id.select_team_text_input);


        buttonSavePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(areValidFields()){
                   // TODO
               }
            }
        });

        buttonMedicalInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MedicalInfoActivity.start(AddPlayerActivity.this);
            }
        });

        selectTeamTextInputLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SelectUserActivity.RC_SELECT_USER && resultCode == RESULT_OK) {
            if (data != null && data.hasExtra(MedicalInfoActivity.EXTRA_USER)) {
                medicalInfo = (MedicalInfo) data.getSerializableExtra(MedicalInfoActivity.EXTRA_USER);
                // TODO  Backendless AsyncCall
            }
        }
    }

    public void onClicked(View view){
        if(view.getId() == R.id.add_medical_info_button){

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



    public static void start(Activity activity) {
        activity.startActivity(new Intent(activity, AddPlayerActivity.class));
    }
}
