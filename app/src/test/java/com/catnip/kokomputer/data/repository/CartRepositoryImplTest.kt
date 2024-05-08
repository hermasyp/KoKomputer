package com.catnip.kokomputer.data.repository

import app.cash.turbine.test
import com.catnip.kokomputer.data.datasource.cart.CartDataSource
import com.catnip.kokomputer.data.model.Cart
import com.catnip.kokomputer.data.model.Product
import com.catnip.kokomputer.data.source.local.database.entity.CartEntity
import com.catnip.kokomputer.utils.ResultWrapper
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

/**
 * Written with love by Muhammad Hermas Yuda Pamungkas
 * Github : https://github.com/hermasyp
 */
class CartRepositoryImplTest {
    @MockK
    lateinit var ds: CartDataSource

    private lateinit var repo: CartRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repo = CartRepositoryImpl(ds)
    }

    @Test
    fun getUserCartData_success() {
        val entity1 =
            CartEntity(
                id = 1,
                productId = "afwwfawf",
                productName = "awfawf",
                productImgUrl = "awfawfafawf",
                productPrice = 8000.0,
                itemQuantity = 3,
                itemNotes = "awfwafawfawfafwafa",
            )
        val entity2 =
            CartEntity(
                id = 2,
                productId = "afwwfawf",
                productName = "awfawf",
                productImgUrl = "awfawfafawf",
                productPrice = 10000.0,
                itemQuantity = 3,
                itemNotes = "awfwafawfawfafwafa",
            )
        val mockList = listOf(entity1, entity2)
        val mockFlow = flow { emit(mockList) }
        every { ds.getAllCarts() } returns mockFlow
        runTest {
            repo.getUserCartData().map {
                delay(100)
                it
            }.test {
                delay(2201)
                val actualData = expectMostRecentItem()
                assertTrue(actualData is ResultWrapper.Success)
                assertEquals(mockList.size, actualData.payload?.first?.size)
                assertEquals(54000.0, actualData.payload?.second)
                verify { ds.getAllCarts() }
            }
        }
    }

    @Test
    fun getUserCartData_loading() {
    }

    @Test
    fun getUserCartData_error() {
    }

    @Test
    fun getUserCartData_empty() {
    }

    @Test
    fun getCheckoutData_success() {
    }

    @Test
    fun getCheckoutData_error() {
    }

    @Test
    fun getCheckoutData_loading() {
    }

    @Test
    fun getCheckoutData_empty() {
    }

    @Test
    fun createCart_success() {
        val mockProduct = mockk<Product>(relaxed = true)
        every { mockProduct.id } returns "123"
        coEvery { ds.insertCart(any()) } returns 1
        runTest {
            repo.createCart(mockProduct, 3, "afawfawf")
                .map {
                    delay(100)
                    it
                }.test {
                    delay(2201)
                    val actualData = expectMostRecentItem()
                    assertTrue(actualData is ResultWrapper.Success)
                    assertEquals(true, actualData.payload)
                    coVerify { ds.insertCart(any()) }
                }
        }
    }

    @Test
    fun createCart_loading() {
    }

    @Test
    fun createCart_error_processing() {
    }

    @Test
    fun createCart_error_no_product_id() {
    }

    @Test
    fun decreaseCart_when_quantity_more_than_0() {
        val mockCart =
            Cart(
                id = 2,
                productId = "afwwfawf",
                productName = "awfawf",
                productImgUrl = "awfawfafawf",
                productPrice = 10000.0,
                itemQuantity = 3,
                itemNotes = "awfwafawfawfafwafa",
            )
        coEvery { ds.deleteCart(any()) } returns 1
        coEvery { ds.updateCart(any()) } returns 1
        runTest {
            repo.decreaseCart(mockCart).map {
                delay(100)
                it
            }.test {
                delay(210)
                val actualData = expectMostRecentItem()
                assertTrue(actualData is ResultWrapper.Success)
                coVerify(atLeast = 0) { ds.deleteCart(any()) }
                coVerify(atLeast = 1) { ds.updateCart(any()) }
            }
        }
    }

    @Test
    fun decreaseCart_when_quantity_less_than_1() {
    }

    @Test
    fun increaseCart() {
    }

    @Test
    fun setCartNotes() {
        val mockCart =
            Cart(
                id = 2,
                productId = "afwwfawf",
                productName = "awfawf",
                productImgUrl = "awfawfafawf",
                productPrice = 10000.0,
                itemQuantity = 3,
                itemNotes = "awfwafawfawfafwafa",
            )
        coEvery { ds.updateCart(any()) } returns 1
        runTest {
            repo.decreaseCart(mockCart).map {
                delay(100)
                it
            }.test {
                delay(210)
                val actualData = expectMostRecentItem()
                assertTrue(actualData is ResultWrapper.Success)
                coVerify(atLeast = 1) { ds.updateCart(any()) }
            }
        }
    }

    @Test
    fun deleteCart() {
    }

    @Test
    fun deleteAllCart() {

    }
}
