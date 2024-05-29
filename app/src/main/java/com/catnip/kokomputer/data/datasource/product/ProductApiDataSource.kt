package com.catnip.kokomputer.data.datasource.product

import com.catnip.kokomputer.data.model.Response
import com.catnip.kokomputer.data.source.network.model.checkout.CheckoutRequestPayload
import com.catnip.kokomputer.data.source.network.model.products.ProductItemResponse
import com.catnip.kokomputer.data.source.network.services.KoKomputerApiService

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class ProductApiDataSource(
    private val service: KoKomputerApiService,
) : ProductDataSource {
    override suspend fun getProducts(categorySlug: String?): Response<List<ProductItemResponse>?> {
        return service.getProducts(categorySlug)
    }

    override suspend fun createOrder(payload: CheckoutRequestPayload): Response<String?> {
        return service.createOrder(payload)
    }
}
