package com.catnip.kokomputer.data.source.network.model.checkout


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class CheckoutResponse(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: Boolean?
)