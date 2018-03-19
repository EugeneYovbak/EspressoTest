package com.boost.espressotest.presentation.tools

import android.content.Context
import android.widget.Toast

object Utils {

    //region Toast Utils
    //TODO try extension fun
    fun showToast(context: Context?, message: String) {
        if (context != null) Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
    //endregion
}
