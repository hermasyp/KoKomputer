package com.catnip.kokomputer.presentation.home

import androidx.lifecycle.ViewModel
import com.catnip.kokomputer.data.repository.CategoryRepository
import com.catnip.kokomputer.data.repository.ProductRepository

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class HomeViewModel(
    private val categoryRepository: CategoryRepository,
    private val productRepository: ProductRepository
) : ViewModel() {

    fun getProducts() = productRepository.getProducts()
    fun getCategories() = categoryRepository.getCategories()

}