package za.ac.cut.hockeyapplication.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class UserItemsActivity extends AppCompatActivity {

    private List<Users> usersList;
    private Users user;

    private ProgressDialog progressDialog;
    ListView listView;

    ArrayAdapter<Users> usersArrayAdapter;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_items);

        listView = findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    user = (Users) listView.getItemAtPosition(i);
                    Intent intent = new Intent(UserItemsActivity.this, SetRolesActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("USER_OBJECT", user);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                    Log.e("Users", "onItemClick: " + e.getMessage());
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadUsers();
    }

    public void loadUsers() {
        progressDialog = new ProgressDialog(this);
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
                        usersArrayAdapter = new ArrayAdapter<>(UserItemsActivity.this, R.layout.support_simple_spinner_dropdown_item, usersList);
                        //listView.setAdapter(usersArrayAdapter);
                        listView.setAdapter(usersArrayAdapter);
                        Log.e("UsersActivity", "handleResponse: " + usersList.get(1)
                                                                             .getName()
                                                                             .toString());
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
