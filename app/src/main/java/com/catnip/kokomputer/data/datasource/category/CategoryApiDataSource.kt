package com.catnip.kokomputer.data.datasource.category

import com.catnip.kokomputer.data.model.Response
import com.catnip.kokomputer.data.source.network.model.category.CategoryItemResponse
import com.catnip.kokomputer.data.source.network.services.KoKomputerApiService

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class CategoryApiDataSource(
    private val service: KoKomputerApiService,
) : CategoryDataSource {
    override suspend fun getCategories(): Response<List<CategoryItemResponse>?> {
        return service.getCategories()
    }
}
