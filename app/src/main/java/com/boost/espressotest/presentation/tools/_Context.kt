package com.boost.espressotest.presentation.tools

import android.content.Context
import android.net.ConnectivityManager
import android.view.LayoutInflater
import android.widget.Toast

val Context.layoutInflater: LayoutInflater get() = LayoutInflater.from(this)

fun Context.isOnline(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val netInfo = connectivityManager.activeNetworkInfo
    return netInfo != null && netInfo.isConnected
}

fun Context?.showToast(message: String) {
    if (this != null) Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}