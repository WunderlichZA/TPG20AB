package za.ac.cut.hockeyapplication.fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import za.ac.cut.hockeyapplication.R;

public abstract class BaseFragment extends Fragment {
    public abstract String getTitle();

    private AlertDialog dialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        hideLoadingProgress();
    }

    protected void showLoadingProgress() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
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
