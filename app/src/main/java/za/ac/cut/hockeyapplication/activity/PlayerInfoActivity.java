package za.ac.cut.hockeyapplication.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.button.MaterialButton;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import za.ac.cut.hockeyapplication.R;
import za.ac.cut.hockeyapplication.model.MedicalAidInfo;
import za.ac.cut.hockeyapplication.model.Player;

public class PlayerInfoActivity extends BaseActivity {

    private static final String TAG = PlayerInfoActivity.class.getSimpleName();
    public static final String EXTRA_PLAYER = "EXTRA_PLAYER";

    private TextView playerName;
    private TextView medicalAidName;
    private TextView medicalAidPlan;
    private TextView medicalAidNumber;
    private TextView allergies;

    private Player player;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_info);

        if (getIntent().hasExtra(EXTRA_PLAYER)) {
            player = (Player) getIntent().getSerializableExtra(EXTRA_PLAYER);
        } else {
            finish();
        }

        // Set toolbar
        Toolbar toolbar = findViewById(R.id.toolbar_include);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.title_activity_player_info);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        playerName = findViewById(R.id.player_name);
        medicalAidName = findViewById(R.id.medical_aid_name);
        medicalAidPlan = findViewById(R.id.medical_aid_plan);
        medicalAidNumber = findViewById(R.id.medical_aid_number);
        allergies = findViewById(R.id.allergies);

        MaterialButton callParentOneButton = findViewById(R.id.call_parent_one_button);
        callParentOneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (player != null && player.getMedicalAidInfo() != null) {
                    if (!TextUtils.isEmpty(player.getMedicalAidInfo().getParentOneCellNumber())) {
                        callNumber(player.getMedicalAidInfo().getParentOneCellNumber());
                    } else {
                        Toast.makeText(PlayerInfoActivity.this, "First assign a number for Parent 1", Toast.LENGTH_SHORT)
                             .show();
                    }
                }
            }
        });

        MaterialButton callParentTwoButton = findViewById(R.id.call_parent_two_button);
        callParentTwoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (player != null && player.getMedicalAidInfo() != null) {
                    if (!TextUtils.isEmpty(player.getMedicalAidInfo().getParentTwoCellNumber())) {
                        callNumber(player.getMedicalAidInfo().getParentTwoCellNumber());
                    } else {
                        Toast.makeText(PlayerInfoActivity.this, "First assign a number for Parent 2", Toast.LENGTH_SHORT)
                             .show();
                    }
                }
            }
        });

        populateViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_player_info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.edit:
                EditPlayerActivity.start(PlayerInfoActivity.this, player);
                return true;
            case R.id.delete:
                deletePlayer();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void populateViews() {
        if (player != null && player.getMedicalAidInfo() != null) {
            playerName.setText(player.getFullName());
            MedicalAidInfo medicalAidInfo = player.getMedicalAidInfo();
            medicalAidName.setText("Medical Aid Name: " + medicalAidInfo.getMedicalAidName());
            medicalAidPlan.setText("Medical Aid Plan: " + medicalAidInfo.getMedicalAidPlan());
            medicalAidNumber.setText("Medical Aid Number: " + medicalAidInfo.getMedicalAidNumber());
            allergies.setText("Allergies: " + medicalAidInfo.getAllergies());
        }
    }

    private void callNumber(String phoneNumber) {
        try {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + phoneNumber.trim()));
            startActivity(intent);
        } catch (Exception exception) {
            Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void deletePlayer() {
        if (player != null) {
            String title = "Delete Player";
            String message = "Are you sure you want to delete " + player.getFullName() + "?";
            String positiveButton = "Yes";
            String negativeButton = "No";
            showDialog(title, message, positiveButton, negativeButton, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    showLoadingProgress();
                    Backendless.Data.of(Player.class).remove(player, new AsyncCallback<Long>() {
                        @Override
                        public void handleResponse(Long response) {
                            hideLoadingProgress();
                            Toast.makeText(PlayerInfoActivity.this, "Player deleted successfully", Toast.LENGTH_SHORT)
                                 .show();
                            finish();
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            hideLoadingProgress();
                            Toast.makeText(PlayerInfoActivity.this, fault.getMessage(), Toast.LENGTH_SHORT)
                                 .show();
                        }
                    });
                }
            });
        }
    }

    @Override
    protected void onActivityResult(
            int requestCode, int resultCode, @Nullable Intent data
    ) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == EditPlayerActivity.RC_EDIT_PLAYER && data != null) {
            player = (Player) data.getSerializableExtra(EditPlayerActivity.EXTRA_PLAYER);
            populateViews();
        }
    }

    public static void start(Activity activity, Player player) {
        Intent intent = new Intent(activity, PlayerInfoActivity.class);
        intent.putExtra(EXTRA_PLAYER, player);
        activity.startActivity(intent);
    }
}
