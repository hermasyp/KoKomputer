package com.catnip.kokomputer.presentation.checkout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.catnip.kokomputer.data.repository.CartRepository
import kotlinx.coroutines.Dispatchers

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class CheckoutViewModel(private val cartRepository: CartRepository) : ViewModel() {

    val checkoutData = cartRepository.getCheckoutData().asLiveData(Dispatchers.IO)

}