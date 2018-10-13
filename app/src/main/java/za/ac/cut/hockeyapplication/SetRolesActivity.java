package za.ac.cut.hockeyapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.button.MaterialButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.async.callback.BackendlessCallback;
import com.backendless.exceptions.BackendlessFault;

import java.util.ArrayList;
import java.util.List;

import businesslayer.model.Users;

public class SetRolesActivity extends AppCompatActivity {

    private static int REQUEST_CODE = 1;

    List<Users> usersList = new ArrayList<>();
    private Users loggedInUser;
    ArrayAdapter<Users> adapter = null;
    String userRole;

    AppCompatSpinner compatSpinner;
    ProgressDialog progressDialog;
    LinearLayout rolesLayout;
    TextView errorMessage, selectUser;
    MaterialButton buttonSaveChanges, buttonSelectUser;

    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_roles);

        buttonSaveChanges = findViewById(R.id.save_changes_button);
        selectUser = findViewById(R.id.roleText);
        buttonSelectUser = findViewById(R.id.button_select_user);
        rolesLayout = findViewById(R.id.roles_layout);

        Intent intent  = getIntent();
        bundle = intent.getExtras();
        if(bundle != null){
            loggedInUser = (Users) bundle.getSerializable("USER_OBJECT");
            loggedInUser.setRole(userRole);
            selectUser.setText(loggedInUser.getName() + " " + loggedInUser.getSurname());
        }
    }

    public void showUsers(View view) {
        switch (view.getId()){
            case R.id.button_select_user:
                startActivityForResult(new Intent(SetRolesActivity.this, UserItemsActivity.class), 1);
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
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            Bundle bundle = data.getExtras();
            loggedInUser = (Users) bundle.getSerializable("USER_OBJECT");
            loggedInUser.setRole(userRole);
            selectUser.setText(loggedInUser.getName());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void loadUsers() {
        progressDialog = new ProgressDialog(SetRolesActivity.this);
        progressDialog.setMax(100);
        progressDialog.setMessage("Loading users");
        progressDialog.setTitle("Roles");
        progressDialog.show();

        try {

            Backendless.Data.of(Users.class).find(new BackendlessCallback<List<Users>>() {
                @Override
                public void handleResponse(List<Users> response) {
                    if (response != null) {
                        usersList = response;
                        progressDialog.dismiss();
                        rolesLayout.setVisibility(View.VISIBLE);
                        adapter = new ArrayAdapter<>(SetRolesActivity.this, R.layout.support_simple_spinner_dropdown_item, usersList);
                        compatSpinner.setAdapter(adapter);
                        Log.e("Tag", "handleResponse: " + usersList.get(1).getName().toString());
                    }
                }

                @Override
                public void handleFault(BackendlessFault fault) {
                    super.handleFault(fault);
                    progressDialog.dismiss();
                    //errorMessage.setVisibility(View.VISIBLE);
                    Log.e("Tag", "handleResponse: " + fault.getMessage());
                }
            });
        } catch (Exception e) {
            Log.e("Exception", "getUsers: " + e.getMessage());
        }
    }

    public void saveChanges(Users user) {

//        user = (Users) bundle.getSerializable("USER_OBJECT");
//        user.setRole(userRole);
//        selectUser.setText(user.getName());


        Backendless.Persistence.save(user, new AsyncCallback<Users>() {
            @Override
            public void handleResponse(Users response) {
                Log.i("OnActivityResults", "handleResponse: " + response.getRole());
                Toast.makeText(SetRolesActivity.this, "Role assigned successfully", Toast.LENGTH_LONG).show();
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.e("OnActivityResults", "handleFault: " + fault.getMessage());
                Toast.makeText(SetRolesActivity.this, "" + fault.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
