package za.ac.cut.hockeyapplication.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import za.ac.cut.hockeyapplication.R;

public abstract class BaseActivity extends AppCompatActivity {

    protected AlertDialog dialog;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        hideKeyboard();
    }

    protected void showLoadingProgress() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(R.layout.loading_dialog_layout);
        dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
        dialog.show();
    }

    protected void hideLoadingProgress() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            dialog = null;
        }
    }

    protected void hideKeyboard() {
        if (getCurrentFocus() == null) {
            return;
        }

        InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        im.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }

    protected void logout() {
        showLoadingProgress();
        Backendless.UserService.logout(new AsyncCallback<Void>() {
            @Override
            public void handleResponse(Void response) {
                hideLoadingProgress();
                LoginActivity.start(BaseActivity.this);
                finish();
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                hideLoadingProgress();
                Toast.makeText(BaseActivity.this, fault.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    protected void showDialog(
            String title,
            String message,
            String positiveButton,
            String negativeButton,
            DialogInterface.OnClickListener positiveOnClickListener
    ) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(positiveButton, positiveOnClickListener);
        builder.setNegativeButton(negativeButton, null);
        dialog = builder.create();
        dialog.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        } else if (item.getItemId() == R.id.logout) {
            logout();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
