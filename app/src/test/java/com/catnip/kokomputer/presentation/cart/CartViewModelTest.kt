package com.catnip.kokomputer.presentation.cart

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.catnip.kokomputer.data.repository.CartRepository
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
class CartViewModelTest {
    // khusuzon view model
    @get:Rule
    val testRule: TestRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val coroutineRule: TestRule = MainCoroutineRule(UnconfinedTestDispatcher())

    @MockK
    lateinit var repo: CartRepository

    private lateinit var viewModel: CartViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = spyk(CartViewModel(repo))
    }

    @Test
    fun getAllCarts() {
        every { repo.getUserCartData() } returns
            flow {
                emit(
                    ResultWrapper.Success(
                        Pair(listOf(mockk(relaxed = true), mockk(relaxed = true)), 8000.0),
                    ),
                )
            }
        val result = viewModel.getAllCarts().getOrAwaitValue()
        assertEquals(2, result.payload?.first?.size)
        assertEquals(8000.0, result.payload?.second)
    }

    @Test
    fun decreaseCart() {
        every { repo.decreaseCart(any()) } returns
            flow {
                emit(ResultWrapper.Success(true))
            }
        viewModel.decreaseCart(mockk())
        verify { repo.decreaseCart(any()) }
    }

    @Test
    fun increaseCart() {
        every { repo.increaseCart(any()) } returns
            flow {
                emit(ResultWrapper.Success(true))
            }
        viewModel.increaseCart(mockk())
        verify { repo.increaseCart(any()) }
    }

    @Test
    fun removeCart() {
        every { repo.deleteCart(any()) } returns
            flow {
                emit(ResultWrapper.Success(true))
            }
        viewModel.removeCart(mockk())
        verify { repo.deleteCart(any()) }
    }

    @Test
    fun setCartNotes() {
        every { repo.setCartNotes(any()) } returns
            flow {
                emit(ResultWrapper.Success(true))
            }
        viewModel.setCartNotes(mockk())
        verify { repo.setCartNotes(any()) }
    }
}
