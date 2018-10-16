package za.ac.cut.hockeyapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.backendless.Backendless;
import com.backendless.async.callback.BackendlessCallback;
import com.backendless.exceptions.BackendlessFault;

import java.util.List;

import za.ac.cut.hockeyapplication.R;
import za.ac.cut.hockeyapplication.model.Users;

public class SelectUserActivity extends BaseActivity {

    public static final int RC_SELECT_USER = 100;
    public static final String EXTRA_USER = "EXTRA_USER";

    private List<Users> usersList;
    private Users user;

    ListView listView;
    ArrayAdapter<Users> usersArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_items);

        // Set toolbar
        Toolbar toolbar = findViewById(R.id.toolbar_include);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.title_activity_select_user);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        listView = findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    user = (Users) listView.getItemAtPosition(i);
                    Intent intent = new Intent(SelectUserActivity.this, SetRolesActivity.class);
                    intent.putExtra(EXTRA_USER, user);
                    setResult(RESULT_OK, intent);
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
        showLoadingProgress();

        Backendless.Data.of(Users.class).find(new BackendlessCallback<List<Users>>() {
            @Override
            public void handleResponse(List<Users> response) {
                if (response != null) {
                    hideLoadingProgress();
                    usersList = response;
                    usersArrayAdapter = new ArrayAdapter<>(SelectUserActivity.this, R.layout.support_simple_spinner_dropdown_item, usersList);
                    listView.setAdapter(usersArrayAdapter);
                }
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                hideLoadingProgress();
                //errorMessage.setVisibility(View.VISIBLE);
                Log.e("UsersActivity", "handleResponse: " + fault.getMessage());
            }
        });
    }

    public static void start(Activity activity) {
        activity.startActivityForResult(new Intent(activity, SelectUserActivity.class), RC_SELECT_USER);
    }
}
