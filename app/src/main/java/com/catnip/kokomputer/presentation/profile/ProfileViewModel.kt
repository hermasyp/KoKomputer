package com.catnip.kokomputer.presentation.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.catnip.kokomputer.data.model.Profile

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class ProfileViewModel : ViewModel() {

    val profileData = MutableLiveData(
        Profile(
            name = "Muhammad Hermas Yuda P",
            username = "hermasyp",
            email = "hermas.yuda@gmail.com",
            profileImg = "https://avatars.githubusercontent.com/u/21256595?v=4"
        )
    )

    val isEditMode = MutableLiveData(false)

    fun changeEditMode() {
        val currentValue = isEditMode.value ?: false
        isEditMode.postValue(!currentValue)
    }
}