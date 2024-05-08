package com.catnip.kokomputer.data.source.network.model.checkout

import com.google.gson.annotations.SerializedName

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
data class CheckoutRequestPayload(
    @SerializedName("orders")
    val orders: List<CheckoutItemPayload>,
)
