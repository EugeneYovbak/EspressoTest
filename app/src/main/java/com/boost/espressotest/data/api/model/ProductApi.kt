package com.boost.espressotest.data.api.model

import com.google.gson.annotations.SerializedName

class ProductApi {

    @SerializedName("id")
    var id: Long = 0

    @SerializedName("name")
    var name: String = ""

    @SerializedName("price_in_cents")
    var priceInCents: Long = 0

    @SerializedName("producer_name")
    var producerName: String = ""

    @SerializedName("image_url")
    var imageUrl: String = ""
}
