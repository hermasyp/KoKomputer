package com.catnip.kokomputer.data.repository

import com.catnip.kokomputer.data.datasource.product.ProductDataSource
import com.catnip.kokomputer.data.model.Product

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface ProductRepository {
    fun getProducts(): List<Product>
}

class ProductRepositoryImpl(private val dataSource: ProductDataSource) : ProductRepository {
    override fun getProducts(): List<Product> = dataSource.getProducts()
}