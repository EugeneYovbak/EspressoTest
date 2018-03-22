package com.boost.espressotest.data.local.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity
constructor(
        @PrimaryKey
        @ColumnInfo(name = "id") var id: Long = 0,
        @ColumnInfo(name = "name") var name: String = "",
        @ColumnInfo(name = "price_in_cents") var priceInCents: Long = 0,
        @ColumnInfo(name = "producer_name") var producerName: String = "",
        @ColumnInfo(name = "image_url") var imageUrl: String = "") {

    @ColumnInfo(name = "favorite") var isFavorite = false
}
