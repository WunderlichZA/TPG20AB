package za.ac.cut.hockeyapplication.activity;

import android.support.design.button.MaterialButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import za.ac.cut.hockeyapplication.R;

public class CoachMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_menu);

    }

    public void onButtonClicked(View v){
        if(v.getId() == R.id.button_logout_coach){
            logout();
        }
    }

    private void logout() {
        Backendless.UserService.logout(new AsyncCallback<Void>() {
            @Override
            public void handleResponse(Void response) {
                finish();
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Toast.makeText(CoachMenuActivity.this, fault.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
