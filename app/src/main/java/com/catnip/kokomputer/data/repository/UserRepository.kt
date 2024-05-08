package com.catnip.kokomputer.data.repository

import com.catnip.kokomputer.data.datasource.user.UserDataSource

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface UserRepository {
    fun isUsingDarkMode(): Boolean

    fun setUsingDarkMode(isUsingDarkMode: Boolean)
}

class UserRepositoryImpl(private val dataSource: UserDataSource) : UserRepository {
    override fun isUsingDarkMode(): Boolean {
        return dataSource.isUsingDarkMode()
    }

    override fun setUsingDarkMode(isUsingDarkMode: Boolean) {
        dataSource.setUsingDarkMode(isUsingDarkMode)
    }
}
