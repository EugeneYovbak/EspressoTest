package com.boost.espressotest.presentation.tools


import android.content.Context
import android.net.ConnectivityManager

class NetworkUtils(private val context: Context) {

    val isOnline: Boolean
        get() {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = connectivityManager.activeNetworkInfo
            return netInfo != null && netInfo.isConnected
        }
}
