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
import za.ac.cut.hockeyapplication.model.MedicalAidInfo;

public class MedicalInfoActivity extends BaseActivity {

    public static final int RC_ADD_MEDICAL_INFO = 400;
    public static final String EXTRA_MEDICAL_INFO = "EXTRA_MEDICAL_INFO";

    private MedicalAidInfo medicalInfo;

    private TextInputLayout medicalAidNameTextInputLayout;
    private TextInputEditText medicalAidNameEditText;
    private TextInputLayout medicalAidPlanTextInputLayout;
    private TextInputEditText medicalAidPlanEditText;
    private TextInputLayout medicalAidNumberTextInputLayout;
    private TextInputEditText medicalAidNumberEditText;
    private TextInputLayout allergiesTextInputLayout;
    private TextInputEditText allergiesEditText;
    private TextInputEditText parentOneCellNumberEditText;
    private TextInputLayout parentOneCellNumberTextInputLayout;
    private TextInputLayout parentTwoCellNumberInputLayout;
    private TextInputEditText parentTwoCellNumberEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_info);

        initViews();

        if (getIntent().hasExtra(EXTRA_MEDICAL_INFO)) {
            medicalInfo = (MedicalAidInfo) getIntent().getSerializableExtra(EXTRA_MEDICAL_INFO);
            populateViews();
        }

        MaterialButton buttonSaveMedicalInfo = findViewById(R.id.save_info_button);
        buttonSaveMedicalInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (areValidFields()) {
                    saveMedicalInfo();
                }
            }
        });
    }

    private void initViews() {
        medicalAidNameTextInputLayout = findViewById(R.id.medical_aid_name_text_input);
        medicalAidNameEditText = findViewById(R.id.medical_aid_name_edit_text);
        medicalAidPlanTextInputLayout = findViewById(R.id.medical_aid_plan_text_input);
        medicalAidPlanEditText = findViewById(R.id.medical_aid_plan_edit_text);
        medicalAidNumberTextInputLayout = findViewById(R.id.medical_aid_number_text_input);
        medicalAidNumberEditText = findViewById(R.id.medical_aid_number_edit_text);
        allergiesTextInputLayout = findViewById(R.id.allergies_text_input);
        allergiesEditText = findViewById(R.id.allergies_edit_text);
        parentOneCellNumberEditText = findViewById(R.id.parent_one_cell_number_edit_text);
        parentOneCellNumberTextInputLayout = findViewById(R.id.parent_one_cell_number_text_input);
        parentTwoCellNumberInputLayout = findViewById(R.id.parent_two_cell_number_text_input);
        parentTwoCellNumberEditText = findViewById(R.id.parent_two_cell_number_edit_text);
    }

    private void populateViews() {
        if (medicalInfo != null) {
            medicalAidNameEditText.setText(medicalInfo.getMedicalAidName());
            medicalAidPlanEditText.setText(medicalInfo.getMedicalAidPlan());
            medicalAidNumberEditText.setText(medicalInfo.getMedicalAidNumber());
            allergiesEditText.setText(medicalInfo.getAllergies());
            parentOneCellNumberEditText.setText(medicalInfo.getParentOneCellNumber());
            parentTwoCellNumberEditText.setText(medicalInfo.getParentTwpCellNumber());
        }
    }

    public void saveMedicalInfo() {
        if(medicalInfo == null) {
            medicalInfo = new MedicalAidInfo();
        }
        medicalInfo.setMedicalAidName(medicalAidNameEditText.getText().toString());
        medicalInfo.setMedicalAidPlan(medicalAidPlanEditText.getText().toString());
        medicalInfo.setMedicalAidNumber(medicalAidNumberEditText.getText().toString());
        medicalInfo.setAllergies(allergiesEditText.getText().toString());
        medicalInfo.setParentOneCellNumber(parentOneCellNumberEditText.getText().toString());
        medicalInfo.setParentTwpCellNumber(parentTwoCellNumberEditText.getText().toString());
        Intent intent = new Intent(MedicalInfoActivity.this, AddPlayerActivity.class);
        intent.putExtra(EXTRA_MEDICAL_INFO, medicalInfo);
        setResult(RESULT_OK, intent);
        finish();
    }

    private boolean areValidFields() {
        boolean valid = true;
        String error = null;

        if (TextUtils.isEmpty(medicalAidNameEditText.getText().toString())) {
            error = "Enter Medical Aid Name";
            valid = false;
        }
        medicalAidNameTextInputLayout.setError(error);
        // Reset error
        error = null;

        if (TextUtils.isEmpty(medicalAidPlanEditText.getText().toString())) {
            error = "Enter Aid Plan";
            valid = false;
        }
        medicalAidPlanTextInputLayout.setError(error);
        // Reset error
        error = null;

        if (TextUtils.isEmpty(medicalAidNumberEditText.getText().toString())) {
            error = "Enter Medical Aid Number";
            valid = false;
        }
        medicalAidNumberTextInputLayout.setError(error);
        // Reset error
        error = null;

        if (TextUtils.isEmpty(allergiesEditText.getText().toString())) {
            error = "Enter Allergies";
            valid = false;
        }
        allergiesTextInputLayout.setError(error);
        // Reset error
        error = null;

        if (TextUtils.isEmpty(parentOneCellNumberEditText.getText().toString())) {
            error = "Enter Parent Cell Number";
            valid = false;
        }
        parentOneCellNumberTextInputLayout.setError(error);
        // Reset error
        error = null;

        if (TextUtils.isEmpty(parentTwoCellNumberEditText.getText().toString())) {
            error = "Enter Parent Cell Number";
            valid = false;
        }
        parentTwoCellNumberInputLayout.setError(error);
        // Reset error
        error = null;

        return valid;
    }

    public static void start(Activity activity, MedicalAidInfo medicalInfo) {
        Intent intent = new Intent(activity, MedicalInfoActivity.class);
        if (medicalInfo != null) {
            intent.putExtra(EXTRA_MEDICAL_INFO, medicalInfo);
        }
        activity.startActivityForResult(intent, RC_ADD_MEDICAL_INFO);
    }
}