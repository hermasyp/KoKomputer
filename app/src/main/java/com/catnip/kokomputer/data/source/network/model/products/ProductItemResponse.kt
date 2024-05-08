package com.catnip.kokomputer.data.source.network.model.products

import com.google.gson.annotations.SerializedName

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
data class ProductItemResponse(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("img_url")
    val imgUrl: String?,
    @SerializedName("price")
    val price: Double?,
    @SerializedName("desc")
    val desc: String?,
    @SerializedName("rating")
    val rating: Double?,
)
