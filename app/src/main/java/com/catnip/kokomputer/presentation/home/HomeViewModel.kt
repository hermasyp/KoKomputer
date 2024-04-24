package com.catnip.kokomputer.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.catnip.kokomputer.data.repository.CategoryRepository
import com.catnip.kokomputer.data.repository.ProductRepository
import com.catnip.kokomputer.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class HomeViewModel(
    private val categoryRepository: CategoryRepository,
    private val productRepository: ProductRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    fun getProducts(categorySlug: String? = null) =
        productRepository.getProducts(categorySlug).asLiveData(Dispatchers.IO)

    fun getCategories() = categoryRepository.getCategories().asLiveData(Dispatchers.IO)

    fun isUsingDarkMode() = userRepository.isUsingDarkMode()
    fun setUsingDarkMode(isUsingDarkMode: Boolean) =
        userRepository.setUsingDarkMode(isUsingDarkMode)
}