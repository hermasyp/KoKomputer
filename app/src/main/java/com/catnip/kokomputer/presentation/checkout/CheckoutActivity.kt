package com.catnip.kokomputer.presentation.checkout

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.catnip.kokomputer.R
import com.catnip.kokomputer.data.datasource.cart.CartDataSource
import com.catnip.kokomputer.data.datasource.cart.CartDatabaseDataSource
import com.catnip.kokomputer.data.datasource.product.ProductApiDataSource
import com.catnip.kokomputer.data.datasource.product.ProductDataSource
import com.catnip.kokomputer.data.repository.CartRepository
import com.catnip.kokomputer.data.repository.CartRepositoryImpl
import com.catnip.kokomputer.data.repository.ProductRepository
import com.catnip.kokomputer.data.repository.ProductRepositoryImpl
import com.catnip.kokomputer.data.source.local.database.AppDatabase
import com.catnip.kokomputer.data.source.network.services.KoKomputerApiService
import com.catnip.kokomputer.databinding.ActivityCheckoutBinding
import com.catnip.kokomputer.presentation.checkout.adapter.PriceListAdapter
import com.catnip.kokomputer.presentation.common.adapter.CartListAdapter
import com.catnip.kokomputer.presentation.common.views.ContentState
import com.catnip.kokomputer.presentation.common.views.ContentStateListener
import com.catnip.kokomputer.utils.GenericViewModelFactory
import com.catnip.kokomputer.utils.NoInternetException
import com.catnip.kokomputer.utils.proceedWhen
import com.catnip.kokomputer.utils.toDollarFormat

class CheckoutActivity : AppCompatActivity() {
    private val binding: ActivityCheckoutBinding by lazy {
        ActivityCheckoutBinding.inflate(layoutInflater)
    }

    private val viewModel: CheckoutViewModel by viewModels {
        val db = AppDatabase.createInstance(this)
        val s = KoKomputerApiService.invoke()
        val pds: ProductDataSource = ProductApiDataSource(s)
        val pr: ProductRepository = ProductRepositoryImpl(pds)
        val ds: CartDataSource = CartDatabaseDataSource(db.cartDao())
        val rp: CartRepository = CartRepositoryImpl(ds)
        GenericViewModelFactory.create(CheckoutViewModel(rp, pr))
    }

    private val adapter: CartListAdapter by lazy {
        CartListAdapter()
    }
    private val priceItemAdapter: PriceListAdapter by lazy {
        PriceListAdapter {
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupList()
        observeData()
        setClickListeners()
        setupStateView()
    }

    private fun setupStateView() {
        binding.csvCheckout.setListener(
            object : ContentStateListener {
                override fun onContentShow(isContentShow: Boolean) {
                    binding.layoutContent.root.isVisible = isContentShow
                    binding.layoutContent.rvCart.isVisible = isContentShow
                    binding.cvSectionOrder.isVisible = isContentShow
                }
            },
        )
    }

    private fun setClickListeners() {
        binding.ivBack.setOnClickListener {
            onBackPressed()
        }
        binding.btnCheckout.setOnClickListener {
            doCheckout()
        }
    }

    private fun showSuccessDialog() {
        val dialog =
            AlertDialog.Builder(this)
                .setTitle(getString(R.string.create_order_success))
                .setPositiveButton(getString(R.string.close)) { _, _ ->
                    finish()
                }
        dialog.show()
    }

    private fun doCheckout() {
        viewModel.checkoutCart().observe(this) {
            it.proceedWhen(
                doOnSuccess = {
                    binding.csvCheckout.setState(ContentState.SUCCESS)
                    viewModel.deleteAllCart()
                    showSuccessDialog()
                },
                doOnLoading = {
                    binding.csvCheckout.setState(ContentState.LOADING)
                },
                doOnError = {
                    if (it.exception is NoInternetException) {
                        binding.csvCheckout.setState(ContentState.ERROR_NETWORK)
                    } else {
                        binding.csvCheckout.setState(ContentState.ERROR_GENERAL)
                    }
                    Toast.makeText(
                        this,
                        getString(R.string.error_checkout),
                        Toast.LENGTH_SHORT,
                    ).show()
                },
            )
        }
    }

    private fun setupList() {
        binding.layoutContent.rvCart.adapter = adapter
        binding.layoutContent.rvShoppingSummary.adapter = priceItemAdapter
    }

    private fun observeData() {
        viewModel.checkoutData.observe(this) { result ->
            result.proceedWhen(doOnSuccess = {
                binding.csvCheckout.setState(ContentState.SUCCESS)
                result.payload?.let { (carts, priceItems, totalPrice) ->
                    adapter.submitData(carts)
                    binding.tvTotalPrice.text = totalPrice.toDollarFormat()
                    priceItemAdapter.submitData(priceItems)
                }
            }, doOnLoading = {
                binding.csvCheckout.setState(ContentState.LOADING)
            }, doOnError = {
                if (it.exception is NoInternetException) {
                    binding.csvCheckout.setState(ContentState.ERROR_NETWORK)
                } else {
                    binding.csvCheckout.setState(
                        ContentState.ERROR_GENERAL,
                        message = result.exception?.message.orEmpty(),
                    )
                }
            }, doOnEmpty = { data ->
                binding.csvCheckout.setState(
                    ContentState.EMPTY,
                    message = getString(R.string.text_cart_is_empty),
                )
                data.payload?.let { (_, _, totalPrice) ->
                    binding.tvTotalPrice.text = totalPrice.toDollarFormat()
                }
            })
        }
    }
}
