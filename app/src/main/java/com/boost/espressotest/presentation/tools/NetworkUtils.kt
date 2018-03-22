package com.boost.espressotest.presentation.tools


import android.content.Context
import android.net.ConnectivityManager

class NetworkUtils(private val context: Context) {

    // TODO: 3/22/18 function or field?
    val isOnline: Boolean
        get() {
            // TODO: 3/22/18 this can be implemented as extension for context
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = connectivityManager.activeNetworkInfo
            return netInfo != null && netInfo.isConnected
        }
}
