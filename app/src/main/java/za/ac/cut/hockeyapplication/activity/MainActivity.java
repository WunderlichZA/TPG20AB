package za.ac.cut.hockeyapplication.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.local.UserIdStorageFactory;

import za.ac.cut.hockeyapplication.R;
import za.ac.cut.hockeyapplication.helper.UserHelper;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    String userId;

    private TextView loggedUser, userRole;
    private LinearLayout adminMenu, coachMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        coachMenu = findViewById(R.id.coach_menu);
        adminMenu = findViewById(R.id.admin_menu);
        loggedUser = findViewById(R.id.logged_in_user);
        userRole = findViewById(R.id.role);
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            userId = UserIdStorageFactory.instance().getStorage().get();
            Backendless.Data.of(BackendlessUser.class)
                            .findById(userId, new AsyncCallback<BackendlessUser>() {
                                @Override
                                public void handleResponse(BackendlessUser response) {
                                    String role = response.getProperty(UserHelper.PROPERTY_ROLE)
                                                          .toString();
                                    if (role.equalsIgnoreCase(UserHelper.ROLE_ADMIN)) {
                                        coachMenu.setVisibility(View.GONE);
                                        loggedUser.setText(response.getEmail());
                                        userRole.setText(role);
                                    } else {
                                        loggedUser.setText(response.getEmail());
                                        userRole.setText(UserHelper.ROLE_COACH);
                                        coachMenu.setVisibility(View.VISIBLE);
                                        adminMenu.setVisibility(View.GONE);
                                    }
                                }

                                @Override
                                public void handleFault(BackendlessFault fault) {
                                    Toast.makeText(MainActivity.this, fault.getMessage(), Toast.LENGTH_LONG)
                                         .show();
                                }
                            });
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.add_team_button:
                TeamsOpponentsActivity.start(MainActivity.this);
                break;
            case R.id.move_players_button:
                MovePlayersActivity.start(MainActivity.this);
                break;
            case R.id.set_roles_button:
                SetRolesActivity.start(MainActivity.this);
                break;
            case R.id.coach_menu_button:
                CoachMenuActivity.start(MainActivity.this);
                break;
            case R.id.logout_button:
                logout();
                break;
        }
    }
}