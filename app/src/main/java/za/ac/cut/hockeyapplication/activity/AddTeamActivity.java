package za.ac.cut.hockeyapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import java.util.ArrayList;

import za.ac.cut.hockeyapplication.R;
import za.ac.cut.hockeyapplication.model.Team;
import za.ac.cut.hockeyapplication.model.Users;

public class AddTeamActivity extends BaseActivity {

    private static final String TAG = AddTeamActivity.class.getSimpleName();

    private TextInputLayout coachNameTextInputLayout;
    private TextInputEditText coachNameEditText;
    private TextInputLayout teamNameTextInputLayout;
    private TextInputEditText teamNameEditText;

    private Users coach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_team);

        // Set toolbar
        Toolbar toolbar = findViewById(R.id.toolbar_include);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.title_activity_add_team);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

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
            coachNameTextInputLayout.setError(null);
            coachNameEditText.setText(coach.getFullName());
        }
    }

    private boolean areValidFields() {
        boolean valid = true;
        String error = null;

        if (coach == null) {
            error = "Select team coach";
            valid = false;
        }
        coachNameTextInputLayout.setError(error);

        if (TextUtils.isEmpty(teamNameEditText.getText().toString())) {
            error = "Enter team name";
            valid = false;
        }
        teamNameTextInputLayout.setError(error);

        // Reset error
        error = null;

        return valid;
    }

    private void saveTeam(String ageGroup, String teamName) {
        showLoadingProgress();

        Team team = new Team(ageGroup, teamName);

        Backendless.Persistence.save(team, new AsyncCallback<Team>() {
            @Override
            public void handleResponse(Team savedTeam) {
                ArrayList<Users> coaches = new ArrayList<>();
                coaches.add(coach);
                Backendless.Data.of(Team.class)
                                .addRelation(savedTeam, "coach:Users:1", coaches, new AsyncCallback<Integer>() {
                                    @Override
                                    public void handleResponse(Integer response) {
                                        hideLoadingProgress();
                                        Toast.makeText(AddTeamActivity.this, "Team added successfully", Toast.LENGTH_SHORT)
                                             .show();
                                        finish();
                                    }

                                    @Override
                                    public void handleFault(BackendlessFault fault) {
                                        hideLoadingProgress();
                                        Toast.makeText(AddTeamActivity.this, fault.getMessage(), Toast.LENGTH_SHORT)
                                             .show();
                                    }
                                });
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                hideLoadingProgress();
                Log.e(TAG, "Error: " + fault.getMessage());
                // TODO
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SelectUserActivity.RC_SELECT_USER && resultCode == RESULT_OK) {
            if (data != null && data.hasExtra(SelectUserActivity.EXTRA_USER)) {
                coach = (Users) data.getSerializableExtra(SelectUserActivity.EXTRA_USER);
                setCoachName();
            }
        }
    }

    public static void start(Activity activity) {
        activity.startActivity(new Intent(activity, AddTeamActivity.class));
    }
}
