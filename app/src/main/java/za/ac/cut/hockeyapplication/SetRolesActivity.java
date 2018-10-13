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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.async.callback.BackendlessCallback;
import com.backendless.exceptions.BackendlessFault;

import java.util.ArrayList;
import java.util.List;

import businesslayer.model.Users;

public class SetRolesActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private static int REQUEST_CODE = 1;

    List<Users> usersList = new ArrayList<>();
    ArrayAdapter<Users> adapter = null;
    String userRole;

    AppCompatSpinner compatSpinner;
    ProgressDialog progressDialog;
    LinearLayout rolesLayout;
    TextView errorMessage, selectUser;
    MaterialButton buttonSaveChanges, buttonSelectUser;

    Users user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_roles);

        buttonSaveChanges = findViewById(R.id.save_changes_button);
        selectUser = findViewById(R.id.roleText);
        buttonSelectUser = findViewById(R.id.button_select_user);
        rolesLayout = findViewById(R.id.roles_layout);
        //rolesLayout.setVisibility(View.GONE);

        buttonSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectUser.setText(user.getName());
            }
        });
    }

    public void showUsers(View view) {
        startActivityForResult(new Intent(SetRolesActivity.this, UserItemsActivity.class), 1);
        //startActivity(new Intent(SetRolesActivity.this, RegisterActivity.class));
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
        Bundle bundle = data.getExtras();
        Users user = (Users) bundle.getSerializable("USER_OBJECT");
        user.setRole(userRole);
        selectUser.setText(user.getName());
        if (requestCode == REQUEST_CODE) {
            if(requestCode == RESULT_OK){

//            Backendless.Persistence.save(user, new AsyncCallback<Users>() {
//                @Override
//                public void handleResponse(Users response) {
//                    Log.i("OnActivityResults", "handleResponse: " + response.getRole());
//                }
//
//                @Override
//                public void handleFault(BackendlessFault fault) {
//                    Log.e("OnActivityResults", "handleFault: " + fault.getMessage());
//                }
//            });
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //loadUsers();
    }

    public void getUsers() {

        try {

            Backendless.Data.of(Users.class).find(new BackendlessCallback<List<Users>>() {
                @Override
                public void handleResponse(List<Users> response) {
                    usersList = response;

                    Log.e("Tag", "handleResponse: " + usersList.get(1).getName().toString());
                }

                @Override
                public void handleFault(BackendlessFault fault) {
                    super.handleFault(fault);
                    Log.e("Tag", "handleResponse: " + fault.getMessage());
                }
            });
//            Backendless.Persistence.of("Users").find(new BackendlessCallback<List<Map>>() {
//                @Override
//                public void handleResponse(List<Map> response) {
//                    Log.i("set roles", "handleResponse" + response.size());
//                }
//
//                @Override
//                public void handleFault(BackendlessFault fault) {
//                    super.handleFault(fault);
//                    Log.e("set roles", "handleFault: " + fault.getMessage());
//                }
//            });
        } catch (Exception e) {
            Log.e("Exception", "getUsers: " + e.getMessage());
        }
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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(SetRolesActivity.this, "dfgdfgh", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Toast.makeText(SetRolesActivity.this, "Nothing", Toast.LENGTH_LONG).show();
    }
}
