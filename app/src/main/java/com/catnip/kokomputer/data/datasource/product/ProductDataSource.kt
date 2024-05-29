package com.catnip.kokomputer.data.datasource.product

import com.catnip.kokomputer.data.model.Response
import com.catnip.kokomputer.data.source.network.model.checkout.CheckoutRequestPayload
import com.catnip.kokomputer.data.source.network.model.products.ProductItemResponse

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface ProductDataSource {
    suspend fun getProducts(categorySlug: String? = null): Response<List<ProductItemResponse>?>

    suspend fun createOrder(payload: CheckoutRequestPayload): Response<String?>
}
