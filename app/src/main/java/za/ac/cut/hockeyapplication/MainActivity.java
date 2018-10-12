package za.ac.cut.hockeyapplication;

import android.content.Intent;
import android.support.design.button.MaterialButton;
import android.support.v7.app.AppCompatActivity;
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

public class  MainActivity extends AppCompatActivity {

    private TextView loggedUser, userRole;
    private LinearLayout adminMenu, coachMenu;

    private MaterialButton buttonLogout, buttonSetRoles;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        coachMenu = findViewById(R.id.coach_menu);
        loggedUser = findViewById(R.id.logged_in_user);
        userRole = findViewById(R.id.role);

        buttonLogout = findViewById(R.id.logout_button);
        buttonSetRoles = findViewById(R.id.set_roles_button);

        String userId = UserIdStorageFactory.instance().getStorage().get();
        Backendless.Data.of(BackendlessUser.class).findById(userId, new AsyncCallback<BackendlessUser>() {
            @Override
            public void handleResponse(BackendlessUser response) {
                String role = response.getProperty("role").toString();
                if(role.equalsIgnoreCase("ADMIN")){
                    coachMenu.setVisibility(View.GONE);
                    loggedUser.setText(response.getEmail().toString());
                    userRole.setText(role);
                }else{
                    loggedUser.setText(response.getEmail().toString());
                    userRole.setText("COACH");
                    coachMenu.setVisibility(View.VISIBLE);
                    adminMenu.setVisibility(View.GONE);
                }
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Toast.makeText(MainActivity.this, fault.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Backendless.UserService.logout(new AsyncCallback<Void>() {
                    @Override
                    public void handleResponse(Void response) {
                        finish();
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Toast.makeText(MainActivity.this, fault.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        buttonSetRoles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SetRolesActivity.class));
            }
        });
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
}