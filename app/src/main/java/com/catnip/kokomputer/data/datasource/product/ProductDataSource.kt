package com.catnip.kokomputer.data.datasource.product

import com.catnip.kokomputer.data.source.network.model.products.ProductResponse

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface ProductDataSource {
    suspend fun getProducts(categorySlug: String? = null): ProductResponse
}

