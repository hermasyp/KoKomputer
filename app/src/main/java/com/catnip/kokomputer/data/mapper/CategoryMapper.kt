package com.catnip.kokomputer.data.mapper

import com.catnip.kokomputer.data.model.Category
import com.catnip.kokomputer.data.source.network.model.category.CategoryItemResponse

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/

fun CategoryItemResponse?.toCategory() =
    Category(
        id = this?.id.orEmpty(),
        name = this?.name.orEmpty(),
        imgUrl = this?.imgUrl.orEmpty(),
        slug = this?.slug.orEmpty(),
        categoryDesc = ""
    )

fun Collection<CategoryItemResponse>?.toCategories() =
    this?.map { it.toCategory() } ?: listOf()