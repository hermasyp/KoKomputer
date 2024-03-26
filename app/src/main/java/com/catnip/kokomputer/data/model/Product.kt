package com.catnip.kokomputer.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
@Parcelize
data class Product(
    var id: String = UUID.randomUUID().toString(),
    var name: String,
    var imgUrl: String,
    var price: Double,
    var desc: String,
    var rating: Double,
) : Parcelable
