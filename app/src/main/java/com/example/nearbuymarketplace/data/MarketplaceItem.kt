package com.example.nearbuymarketplace.data

data class MarketplaceItem(
    val id: String = "",
    val title: String = "",
    val price: Double = 0.0,
    val description: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val sellerId: String = ""
)