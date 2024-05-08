package com.catnip.kokomputer.data.source.local.pref

import android.content.SharedPreferences
import com.catnip.kokomputer.utils.SharedPreferenceUtils.set

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface UserPreference {
    fun isUsingDarkMode(): Boolean

    fun setUsingDarkMode(isUsingDarkMode: Boolean)
}

class UserPreferenceImpl(private val pref: SharedPreferences) : UserPreference {
    override fun isUsingDarkMode(): Boolean = pref.getBoolean(KEY_IS_USING_DARK_MODE, false)

    override fun setUsingDarkMode(isUsingDarkMode: Boolean) {
        pref[KEY_IS_USING_DARK_MODE] = isUsingDarkMode
    }

    companion object {
        const val PREF_NAME = "kokomputer-pref"
        const val KEY_IS_USING_DARK_MODE = "KEY_IS_USING_DARK_MODE"
    }
}
