package com.catnip.kokomputer.data.model

import com.google.gson.annotations.SerializedName

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
data class Response<T>(
    @SerializedName("status")
    val status: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: T,
    @SerializedName("status")
    val statusStr: ApiStatus,
)

enum class ApiStatus {
    @SerializedName("success")
    Success,
    @SerializedName("failed")
    Failed
}
