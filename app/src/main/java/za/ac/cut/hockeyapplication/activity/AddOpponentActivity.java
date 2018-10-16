package za.ac.cut.hockeyapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import za.ac.cut.hockeyapplication.R;
import za.ac.cut.hockeyapplication.model.Opponent;

public class AddOpponentActivity extends BaseActivity {
    private static final String TAG = AddOpponentActivity.class.getSimpleName();

    private TextInputEditText opponentNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_opponent);

        // Set toolbar
        // Set toolbar
        Toolbar toolbar = findViewById(R.id.toolbar_include);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.title_activity_add_opponent);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        final TextInputLayout opponentNameTextInputLayout = findViewById(R.id.opponent_name_text_input);
        opponentNameEditText = findViewById(R.id.opponent_name_edit_text);
        MaterialButton saveOpponentButton = findViewById(R.id.save_opponent_button);
        saveOpponentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();
                String opponentName = opponentNameEditText.getText().toString();
                boolean showError = TextUtils.isEmpty(opponentName);
                opponentNameTextInputLayout.setError(showError ? "Enter opponent name" : null);

                if (!showError) {
                    saveOpponent(opponentName);
                }
            }
        });
    }

    private void saveOpponent(String opponentName) {
        showLoadingProgress();

        Opponent opponent = new Opponent();
        opponent.setName(opponentName);

        Backendless.Data.of(Opponent.class).save(opponent, new AsyncCallback<Opponent>() {
            @Override
            public void handleResponse(Opponent response) {
                hideLoadingProgress();
                Toast.makeText(AddOpponentActivity.this, "Opponent added successfully", Toast.LENGTH_SHORT)
                     .show();
                finish();
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                hideLoadingProgress();
                Toast.makeText(AddOpponentActivity.this, fault.getMessage(), Toast.LENGTH_SHORT)
                     .show();
            }
        });
    }

    public static void start(Activity activity) {
        activity.startActivity(new Intent(activity, AddOpponentActivity.class));
    }
}
