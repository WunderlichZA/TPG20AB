package za.ac.cut.hockeyapplication.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toolbar;

import com.backendless.Backendless;
import com.backendless.async.callback.BackendlessCallback;
import com.backendless.exceptions.BackendlessFault;

import java.util.List;

import za.ac.cut.hockeyapplication.R;
import za.ac.cut.hockeyapplication.model.Users;

public class UserActivity extends AppCompatActivity {

    private List<Users> usersList;
    private Users user;

    private ProgressDialog progressDialog;
    AppCompatSpinner compatSpinner;
    ListView listView;

    ArrayAdapter<Users> usersArrayAdapter;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        toolbar = findViewById(R.id.toolbar);
        getSupportActionBar();

        listView = findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                user = (Users) listView.getItemAtPosition(i);
                Intent intent = new Intent(getBaseContext(), SetRolesActivity.class);
                intent.putExtra("userObject", user);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadUsers();
    }

    public void loadUsers() {
        progressDialog = new ProgressDialog(UserActivity.this);
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
                        usersArrayAdapter = new ArrayAdapter<>(UserActivity.this, R.layout.support_simple_spinner_dropdown_item, usersList);
                        //listView.setAdapter(usersArrayAdapter);
                        listView.setAdapter(usersArrayAdapter);
                        Log.e("UsersActivity", "handleResponse: " + usersList.get(1).getName());
                    }
                }

                @Override
                public void handleFault(BackendlessFault fault) {
                    super.handleFault(fault);
                    progressDialog.dismiss();
                    //errorMessage.setVisibility(View.VISIBLE);
                    Log.e("UsersActivity", "handleResponse: " + fault.getMessage());
                }
            });
        } catch (Exception e) {
            Log.e("Exception", "getUsers: " + e.getMessage());
        }
    }
}
