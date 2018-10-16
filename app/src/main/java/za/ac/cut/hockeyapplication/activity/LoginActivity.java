package za.ac.cut.hockeyapplication.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.button.MaterialButton;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import za.ac.cut.hockeyapplication.R;
import za.ac.cut.hockeyapplication.helper.UserHelper;

public class LoginActivity extends BaseActivity {

    private MaterialButton buttonRegister, buttonLogin, buttonReset;
    private EditText etUsername, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.username_edit_text);
        etPassword = findViewById(R.id.user_password_edit_text);
        buttonRegister = findViewById(R.id.register_button);
        buttonLogin = findViewById(R.id.login_button);
        buttonReset = findViewById(R.id.reset_button);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();
                Log.e("Login Activity", "onClick: error");
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();
                String username, password;
                username = etUsername.getText().toString();
                password = etPassword.getText().toString();
                if (!(TextUtils.isEmpty(username) && TextUtils.isEmpty(password))) {
                    showLoadingProgress();
                    Backendless.UserService.login(username, password, new AsyncCallback<BackendlessUser>() {
                        @Override
                        public void handleResponse(BackendlessUser response) {
                            hideLoadingProgress();
                            String role = response.getProperty(UserHelper.PROPERTY_ROLE).toString();
                            if (role.equalsIgnoreCase(UserHelper.ROLE_ADMIN)) {
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                finish();
                            }
                            if (role.equalsIgnoreCase(UserHelper.ROLE_NONE)) {
                                Toast.makeText(LoginActivity.this, "Ask your Admin to assign you a role for you to use this app", Toast.LENGTH_LONG)
                                     .show();
                            }
                            if (role.equalsIgnoreCase(UserHelper.ROLE_COACH)) {
                                startActivity(new Intent(LoginActivity.this, CoachMenuActivity.class));
                                finish();
                            }
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            hideLoadingProgress();
                            Toast.makeText(LoginActivity.this, fault.getMessage(), Toast.LENGTH_LONG)
                                 .show();
                        }
                    }, true);
                } else {
                    Toast.makeText(LoginActivity.this, "Field cannot be empty", Toast.LENGTH_LONG)
                         .show();
                }
            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetUserPassword();
            }
        });
    }

    public void resetUserPassword() {
        hideKeyboard();
        try {
            final EditText etPasswordReset = new EditText(this);
            dialog = new AlertDialog.Builder(this).setTitle("Reset Password")
                                                  .setMessage("Please enter your email address to send reset instructions to:")
                                                  .setView(etPasswordReset)
                                                  .setPositiveButton("Send", new DialogInterface.OnClickListener() {
                                                      @Override
                                                      public void onClick(
                                                              DialogInterface dialogInterface, int i
                                                      ) {
                                                          String email = etPasswordReset.getText()
                                                                                        .toString()
                                                                                        .trim();
                                                          if (!TextUtils.isEmpty(email)) {
                                                              Backendless.UserService.restorePassword(email, new AsyncCallback<Void>() {
                                                                  @Override
                                                                  public void handleResponse(
                                                                          Void response
                                                                  ) {
                                                                      if (response != null) {
                                                                          Toast.makeText(LoginActivity.this, "Password reset instructions has been sent to " + etPasswordReset
                                                                                  .getText()
                                                                                  .toString(), Toast.LENGTH_LONG)
                                                                               .show();
                                                                      }
                                                                  }

                                                                  @Override
                                                                  public void handleFault(
                                                                          BackendlessFault fault
                                                                  ) {
                                                                      Toast.makeText(LoginActivity.this, fault
                                                                              .getMessage(), Toast.LENGTH_LONG)
                                                                           .show();
                                                                  }
                                                              });
                                                          } else {
                                                              Toast.makeText(LoginActivity.this, "Enter email", Toast.LENGTH_SHORT)
                                                                   .show();
                                                          }
                                                      }
                                                  })
                                                  .setNegativeButton("Cancel", null)
                                                  .create();
            dialog.show();
        } catch (Exception e) {
            Log.e("Login Activity", "resetUserPassword: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }
}
