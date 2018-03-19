package com.boost.espressotest.data.api.model

import com.google.gson.annotations.SerializedName

data class ProductApi(
        @SerializedName("id") var id: Long?,
        @SerializedName("name") var name: String?,
        @SerializedName("price_in_cents") var priceInCents: Long?,
        @SerializedName("producer_name") var producerName: String?,
        @SerializedName("image_url") var imageUrl: String?
)
