package com.catnip.kokomputer.data.datasource.product

import com.catnip.kokomputer.data.source.network.model.checkout.CheckoutRequestPayload
import com.catnip.kokomputer.data.source.network.model.checkout.CheckoutResponse
import com.catnip.kokomputer.data.source.network.model.products.ProductResponse

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface ProductDataSource {
    suspend fun getProducts(categorySlug: String? = null): ProductResponse
    suspend fun createOrder(payload : CheckoutRequestPayload) : CheckoutResponse
}

