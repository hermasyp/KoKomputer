package com.catnip.kokomputer.data.datasource.user

import com.catnip.kokomputer.data.source.local.pref.UserPreference
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

/**
 * Written with love by Muhammad Hermas Yuda Pamungkas
 * Github : https://github.com/hermasyp
 */
class UserPreferenceDataSourceTest {
    @MockK
    lateinit var up: UserPreference

    private lateinit var ds: UserDataSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        ds = UserPreferenceDataSource(up)
    }

    @Test
    fun isUsingDarkMode() {
        every { up.isUsingDarkMode() } returns true
        val actualResult = ds.isUsingDarkMode()
        verify { up.isUsingDarkMode() }
        assertEquals(true, actualResult)
    }

    @Test
    fun setUsingDarkMode() {
        every { up.setUsingDarkMode(any()) } returns Unit
        ds.setUsingDarkMode(true)
        verify { up.setUsingDarkMode(any()) }
    }
}
