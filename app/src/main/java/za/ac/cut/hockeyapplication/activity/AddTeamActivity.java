package za.ac.cut.hockeyapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.Spinner;

import za.ac.cut.hockeyapplication.R;
import za.ac.cut.hockeyapplication.model.Users;

public class AddTeamActivity extends Activity {

    public static final int RC_ADD_TEAM = 100;
    public static final String EXTRA_TEAM = "EXTRA_TEAM";

    private TextInputLayout coachNameTextInputLayout;
    private TextInputEditText coachNameEditText;
    private TextInputLayout teamNameTextInputLayout;
    private TextInputEditText teamNameEditText;

    private Users coach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_team);

        final Spinner ageGroupSpinner = findViewById(R.id.age_group_spinner);
        coachNameTextInputLayout = findViewById(R.id.coach_name_text_input);
        coachNameTextInputLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectUserActivity.start(AddTeamActivity.this);
            }
        });
        coachNameEditText = findViewById(R.id.coach_name_edit_text);
        teamNameTextInputLayout = findViewById(R.id.team_name_text_input);
        teamNameEditText = findViewById(R.id.team_name_edit_text);
        MaterialButton saveOpponentButton = findViewById(R.id.save_team_button);
        saveOpponentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (areValidFields()) {
                    String ageGroup = (String) ageGroupSpinner.getSelectedItem();
                    String teamName = teamNameEditText.getText().toString();
                    saveTeam(ageGroup, teamName);
                }
            }
        });
    }

    private void setCoachName() {
        if (coach != null) {
            coachNameEditText.setText(coach.getName());
        }
    }

    private boolean areValidFields() {
        boolean valid = true;
        String error = null;

        if (TextUtils.isEmpty(teamNameEditText.getText().toString())) {
            error = "Enter team name";
            valid = false;
        }
        teamNameTextInputLayout.setError(error);

        // Reset error
        error = null;

        if (coach == null) {
            error = "Select team coach";
            valid = false;
        }
        coachNameTextInputLayout.setError(error);

        return valid;
    }

    private void saveTeam(String ageGroup, String teamName) {
        // TODO
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == AddTeamActivity.RC_ADD_TEAM && resultCode == RESULT_OK) {
            if (data != null && data.hasExtra(AddTeamActivity.EXTRA_TEAM)) {
                coach = (Users) data.getSerializableExtra(AddTeamActivity.EXTRA_TEAM);
                setCoachName();
            }
        }
    }

    public static void start(Activity activity) {
        activity.startActivity(new Intent(activity, AddTeamActivity.class));
    }
}
