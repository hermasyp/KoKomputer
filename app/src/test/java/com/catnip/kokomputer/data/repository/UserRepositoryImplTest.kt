package com.catnip.kokomputer.data.repository

import com.catnip.kokomputer.data.datasource.user.UserDataSource
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * Written with love by Muhammad Hermas Yuda Pamungkas
 * Github : https://github.com/hermasyp
 */
class UserRepositoryImplTest {
    @MockK
    lateinit var ds: UserDataSource

    private lateinit var repo: UserRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repo = UserRepositoryImpl(ds)
    }

    @Test
    fun isUsingDarkMode() {
        every { ds.isUsingDarkMode() } returns true
        val actualResult = repo.isUsingDarkMode()
        verify { ds.isUsingDarkMode() }
        Assert.assertEquals(true, actualResult)
    }

    @Test
    fun setUsingDarkMode() {
        every { ds.setUsingDarkMode(any()) } returns Unit
        repo.setUsingDarkMode(true)
        verify { ds.setUsingDarkMode(any()) }
    }
}
