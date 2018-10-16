package za.ac.cut.hockeyapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.button.MaterialButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import za.ac.cut.hockeyapplication.R;
import za.ac.cut.hockeyapplication.model.Users;

public class SetRolesActivity extends BaseActivity {

    private Users loggedInUser;
    String userRole;

    LinearLayout rolesLayout;
    TextView errorMessage, selectUser;
    MaterialButton buttonSaveChanges, buttonSelectUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_roles);

        // Set toolbar
        Toolbar toolbar = findViewById(R.id.toolbar_include);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.title_activity_set_role);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        buttonSaveChanges = findViewById(R.id.save_changes_button);
        selectUser = findViewById(R.id.roleText);
        buttonSelectUser = findViewById(R.id.button_select_user);
        rolesLayout = findViewById(R.id.roles_layout);
    }

    public void showUsers(View view) {
        switch (view.getId()) {
            case R.id.button_select_user:
                SelectUserActivity.start(SetRolesActivity.this);
                break;
            case R.id.save_changes_button:
                saveChanges(loggedInUser);
                break;
        }
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.radio_admin:
                if (checked)
                    // User is Admin
                    userRole = "ADMIN";
                break;
            case R.id.radio_coach:
                if (checked)
                    // User ia Coach
                    userRole = "COACH";
                break;
            case R.id.radio_none:
                if (checked)
                    // User has no access to the app
                    userRole = "NONE";
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SelectUserActivity.RC_SELECT_USER && resultCode == RESULT_OK) {
            if (data != null && data.hasExtra(SelectUserActivity.EXTRA_USER)) {
                loggedInUser = (Users) data.getSerializableExtra(SelectUserActivity.EXTRA_USER);
                selectUser.setText(loggedInUser.toString());
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void saveChanges(final Users user) {

        if (user != null && userRole != null) {
            showLoadingProgress();
            Backendless.UserService.findById(user.getObjectId(), new AsyncCallback<BackendlessUser>() {
                @Override
                public void handleResponse(BackendlessUser userObj) {
                    userObj.setProperty("role", userRole);
                    Backendless.UserService.update(userObj, new AsyncCallback<BackendlessUser>() {
                        @Override
                        public void handleResponse(BackendlessUser updateUser) {
                            hideLoadingProgress();
                            Log.i("update user", "handleResponse: " + updateUser.getProperty("role")
                                                                                .toString());
                            Toast.makeText(SetRolesActivity.this, "Role successfully assigned.", Toast.LENGTH_LONG)
                                 .show();
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            hideLoadingProgress();
                            Toast.makeText(SetRolesActivity.this, fault.getMessage(), Toast.LENGTH_LONG)
                                 .show();
                        }
                    });
                }

                @Override
                public void handleFault(BackendlessFault fault) {
                    hideLoadingProgress();
                    Log.e("user update", "handleFault: " + fault.getMessage());
                }
            });
        } else if (userRole == null) {
            Toast.makeText(SetRolesActivity.this, "A role must be selected.", Toast.LENGTH_LONG)
                 .show();
        } else {
            Toast.makeText(SetRolesActivity.this, "User must be selected.", Toast.LENGTH_LONG)
                 .show();
        }
    }

    public static void start(Activity activity) {
        activity.startActivity(new Intent(activity, SetRolesActivity.class));
    }
}
