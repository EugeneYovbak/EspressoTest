package com.boost.espressotest.data.api.model

import com.google.gson.annotations.SerializedName

class BaseResponse<T> {
    @SerializedName("result")
    var data: T? = null
}