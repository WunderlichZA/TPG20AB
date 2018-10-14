package za.ac.cut.hockeyapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.View;

import za.ac.cut.hockeyapplication.R;

public class AddOpponentActivity extends Activity {

    public static final int RC_ADD_OPPONENT = 200;
    public static final String EXTRA_OPPONENT = "EXTRA_OPPONENT";

    private TextInputEditText opponentNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_opponent);

        final TextInputLayout opponentNameTextInputLayout = findViewById(R.id.opponent_name_text_input);
        opponentNameEditText = findViewById(R.id.opponent_name_edit_text);
        MaterialButton saveOpponentButton = findViewById(R.id.save_opponent_button);
        saveOpponentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

    }

    public static void start(Activity activity) {
        activity.startActivityForResult(new Intent(activity, AddOpponentActivity.class), RC_ADD_OPPONENT);
    }
}
