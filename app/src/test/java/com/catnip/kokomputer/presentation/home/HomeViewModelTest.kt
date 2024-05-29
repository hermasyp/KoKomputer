package com.catnip.kokomputer.presentation.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.catnip.kokomputer.data.repository.CategoryRepository
import com.catnip.kokomputer.data.repository.ProductRepository
import com.catnip.kokomputer.data.repository.UserRepository
import com.catnip.kokomputer.tools.MainCoroutineRule
import com.catnip.kokomputer.tools.getOrAwaitValue
import com.catnip.kokomputer.utils.ResultWrapper
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

/**
 * Written with love by Muhammad Hermas Yuda Pamungkas
 * Github : https://github.com/hermasyp
 */
class HomeViewModelTest {
    // khusuzon view model
    @get:Rule
    val testRule: TestRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val coroutineRule: TestRule = MainCoroutineRule(UnconfinedTestDispatcher())

    @MockK
    lateinit var categoryRepository: CategoryRepository

    @MockK
    lateinit var productRepository: ProductRepository

    @MockK
    lateinit var userRepository: UserRepository

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel =
            spyk(
                HomeViewModel(
                    categoryRepository,
                    productRepository,
                    userRepository,
                ),
            )
    }

    @Test
    fun getProducts() {
        every { productRepository.getProducts(any()) } returns
            flow {
                emit(ResultWrapper.Success(listOf(mockk(), mockk())))
            }
        val result = viewModel.getProducts().getOrAwaitValue()
        assertEquals(2, result.payload?.size)
        verify { productRepository.getProducts(any()) }
    }

    @Test
    fun getCategories() {
        every { categoryRepository.getCategories() } returns
            flow {
                emit(ResultWrapper.Success(listOf(mockk(), mockk())))
            }
        val result = viewModel.getCategories().getOrAwaitValue()
        assertEquals(2, result.payload?.size)
        verify { categoryRepository.getCategories() }
    }

    @Test
    fun isUsingDarkMode() {
        every { userRepository.isUsingDarkMode() } returns true
        val result = viewModel.isUsingDarkMode()
        assertEquals(true, result)
        verify { userRepository.isUsingDarkMode() }
    }

    @Test
    fun setUsingDarkMode() {
        every { userRepository.setUsingDarkMode(any()) } returns Unit
        viewModel.setUsingDarkMode(false)
        verify { userRepository.setUsingDarkMode(any()) }
    }
}
