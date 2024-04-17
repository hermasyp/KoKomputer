package com.catnip.kokomputer.data.repository

import com.catnip.kokomputer.data.datasource.product.ProductDataSource
import com.catnip.kokomputer.data.mapper.toProducts
import com.catnip.kokomputer.data.model.Cart
import com.catnip.kokomputer.data.model.Product
import com.catnip.kokomputer.data.source.network.model.checkout.CheckoutItemPayload
import com.catnip.kokomputer.data.source.network.model.checkout.CheckoutRequestPayload
import com.catnip.kokomputer.utils.ResultWrapper
import com.catnip.kokomputer.utils.proceedFlow
import kotlinx.coroutines.flow.Flow

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface ProductRepository {
    fun getProducts(categorySlug: String? = null): Flow<ResultWrapper<List<Product>>>

    fun createOrder(products: List<Cart>): Flow<ResultWrapper<Boolean>>
}

class ProductRepositoryImpl(
    private val dataSource: ProductDataSource
) : ProductRepository {
    override fun getProducts(categorySlug: String?): Flow<ResultWrapper<List<Product>>> {
        return proceedFlow {
            dataSource.getProducts(categorySlug).data.toProducts()
        }
    }

    override fun createOrder(products: List<Cart>): Flow<ResultWrapper<Boolean>> {
        return proceedFlow {
            dataSource.createOrder(CheckoutRequestPayload(
                orders = products.map {
                    CheckoutItemPayload(
                        notes = it.itemNotes,
                        productId = it.productId.orEmpty(),
                        quantity = it.itemQuantity
                    )
                }
            )).status ?: false
        }
    }
}