package com.catnip.kokomputer.data.model

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
data class Cart(
    var id: Int? = null,
    var productId: String? = null,
    var productName: String,
    var productImgUrl: String,
    var productPrice: Double,
    var itemQuantity: Int = 0,
    var itemNotes: String? = null
)