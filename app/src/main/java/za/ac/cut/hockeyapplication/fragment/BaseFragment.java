package za.ac.cut.hockeyapplication.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;

import za.ac.cut.hockeyapplication.R;

public abstract class BaseFragment extends Fragment {
    public abstract String getTitle();

    private Dialog dialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onPause() {
        super.onPause();
        hideLoadingProgress();
    }

    protected void showLoadingProgress() {
        dialog  = new Dialog(requireActivity());
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
        Activity activity = getActivity();
        if (activity != null) {
            View currentFocus = activity.getCurrentFocus();

            if (currentFocus == null) {
                return;
            }

            InputMethodManager im = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
        }
    }
}
