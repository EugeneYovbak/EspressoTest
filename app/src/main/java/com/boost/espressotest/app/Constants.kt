package com.boost.espressotest.app

import com.boost.espressotest.BuildConfig


object Constants {
    const val AUTH_TOKEN = "Authorization: Token " + BuildConfig.LCBO_API_KEY
}