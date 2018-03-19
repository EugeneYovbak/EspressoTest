package com.boost.espressotest.data.local.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "products")
class ProductEntity {
    @PrimaryKey
    @ColumnInfo(name = "id") var id: Long = 0
    @ColumnInfo(name = "name") var name: String = ""
    @ColumnInfo(name = "price_in_cents") var priceInCents: Long = 0
    @ColumnInfo(name = "producer_name") var producerName: String = ""
    @ColumnInfo(name = "image_url") var imageUrl: String = ""
    @ColumnInfo(name = "favorite") var isFavorite = false

    constructor()

    @Ignore
    constructor(id: Long, name: String, priceInCents: Long, producerName: String, imageUrl: String) {
        this.id = id
        this.name = name
        this.priceInCents = priceInCents
        this.producerName = producerName
        this.imageUrl = imageUrl
    }
}
