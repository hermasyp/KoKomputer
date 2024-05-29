package com.catnip.kokomputer.data.source.network.model.category

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
@Deprecated("no longer used")
data class CategoriesResponse(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("data")
    val data: List<CategoryItemResponse>?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: Boolean?,
)
