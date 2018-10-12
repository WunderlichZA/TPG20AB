package za.ac.cut.hockeyapplication;

import android.content.Intent;
import android.support.design.button.MaterialButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class RegisterActivity extends AppCompatActivity {

    private MaterialButton registerUser;
    EditText edName, edSurname, edPassword, edConfirmPassword, edEmail;

    BackendlessUser user = new BackendlessUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerUser = findViewById(R.id.button_register_user);
        edEmail = findViewById(R.id.email_edit_text);
        edName = findViewById(R.id.name_edit_text);
        edSurname = findViewById(R.id.surname_edit_text);
        edPassword = findViewById(R.id.password_edit_text);
        edConfirmPassword = findViewById(R.id.password_confirm_edit_text);

        registerUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password, confirmedPassword;
                password = edPassword.getText().toString();
                confirmedPassword = edPassword.getText().toString();

                if(TextUtils.isEmpty(password) &&
                        TextUtils.isEmpty(confirmedPassword)){
                    Toast.makeText(RegisterActivity.this, "Fields cannot be empty", Toast.LENGTH_LONG).show();
                }else if(password.equalsIgnoreCase(confirmedPassword)){
                    user.setProperty("name", edName.getText().toString());
                    user.setProperty("surname", edSurname.getText().toString());
                    user.setProperty("email", edEmail.getText().toString());
                    user.setPassword(edConfirmPassword.getText().toString());

                    Backendless.UserService.register(user, new AsyncCallback<BackendlessUser>() {
                        @Override
                        public void handleResponse(BackendlessUser response) {
                            Toast.makeText(RegisterActivity.this, "Registration successful", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                            finish();
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            Toast.makeText(RegisterActivity.this, fault.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });

                    //Toast.makeText(RegisterActivity.this, "Manyeke", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(RegisterActivity.this, "Password do not match", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
