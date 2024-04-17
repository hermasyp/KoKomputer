package com.catnip.kokomputer.data.source.network.model.checkout

import com.google.gson.annotations.SerializedName

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
data class CheckoutItemPayload(
    @SerializedName("notes")
    val notes: String?,
    @SerializedName("product_id")
    val productId: String,
    @SerializedName("qty")
    val quantity: Int,
)
