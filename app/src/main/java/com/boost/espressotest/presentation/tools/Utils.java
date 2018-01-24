package com.boost.espressotest.presentation.tools;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

/**
 * @author PerSpiKyliaTor on 19.01.18.
 */

public class Utils {

    //region Device Utils
    public static void showToast(Context context, String message) {
        if (context != null) Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void hideKeyboard(Context context, View viewToHide) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(viewToHide.getWindowToken(), 0);
    }
    //endregion
}
