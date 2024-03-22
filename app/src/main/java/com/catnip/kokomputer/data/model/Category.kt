package com.catnip.kokomputer.data.model

import java.util.UUID

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
data class Category(
    var id: String = UUID.randomUUID().toString(),
    var name: String,
    var imgUrl: String
)
