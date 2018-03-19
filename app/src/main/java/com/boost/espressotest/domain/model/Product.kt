package com.boost.espressotest.domain.model


data class Product(var id: Long,
                   var name: String,
                   var priceInCents: Long,
                   var producerName: String,
                   var imageUrl: String,
                   var isFavorite: Boolean = false
)
