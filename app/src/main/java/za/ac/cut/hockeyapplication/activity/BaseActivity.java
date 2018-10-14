package za.ac.cut.hockeyapplication.activity;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import za.ac.cut.hockeyapplication.R;

public abstract class BaseActivity extends AppCompatActivity {

    private Dialog dialog;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        hideKeyboard();
    }

    protected void showLoadingProgress() {
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.loading_dialog_layout);
        dialog.show();
    }

    protected void hideLoadingProgress() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            dialog = null;
        }
    }

    protected void hideKeyboard() {

        View currentFocus = getCurrentFocus();

        if (currentFocus == null) {
            return;
        }

        InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        im.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
    }

    protected void logout() {
        showLoadingProgress();
        Backendless.UserService.logout(new AsyncCallback<Void>() {
            @Override
            public void handleResponse(Void response) {
                hideLoadingProgress();
                finish();
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                hideLoadingProgress();
                Toast.makeText(BaseActivity.this, fault.getMessage(), Toast.LENGTH_LONG)
                     .show();
            }
        });
    }
}
