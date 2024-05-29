package com.catnip.kokomputer.data.datasource.category

import com.catnip.kokomputer.data.model.Response
import com.catnip.kokomputer.data.source.network.model.category.CategoryItemResponse

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface CategoryDataSource {
    suspend fun getCategories(): Response<List<CategoryItemResponse>?>
}
