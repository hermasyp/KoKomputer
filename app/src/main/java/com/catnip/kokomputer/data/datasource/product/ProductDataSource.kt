package com.catnip.kokomputer.data.datasource.product

import com.catnip.kokomputer.data.model.Product

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface ProductDataSource {
    fun getProducts(): List<Product>
}