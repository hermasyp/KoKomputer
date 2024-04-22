package com.catnip.kokomputer.data.mapper

import com.catnip.kokomputer.data.model.Cart
import com.catnip.kokomputer.data.source.local.database.entity.CartEntity

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/

fun Cart?.toCartEntity() = CartEntity(
    id = this?.id,
    productId = this?.productId.orEmpty(),
    itemQuantity = this?.itemQuantity ?: 0,
    productName = this?.productName.orEmpty(),
    productPrice = this?.productPrice ?: 0.0,
    productImgUrl = this?.productImgUrl.orEmpty(),
    itemNotes = this?.itemNotes
)

fun CartEntity?.toCart() = Cart(
    id = this?.id,
    productId = this?.productId.orEmpty(),
    itemQuantity = this?.itemQuantity ?: 0,
    productName = this?.productName.orEmpty(),
    productPrice = this?.productPrice ?: 0.0,
    productImgUrl = this?.productImgUrl.orEmpty(),
    itemNotes = this?.itemNotes
)

fun List<CartEntity?>.toCartList() = this.map { it.toCart() }