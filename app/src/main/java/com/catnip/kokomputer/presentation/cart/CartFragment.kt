package com.catnip.kokomputer.presentation.cart

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.catnip.kokomputer.R
import com.catnip.kokomputer.data.datasource.cart.CartDataSource
import com.catnip.kokomputer.data.datasource.cart.CartDatabaseDataSource
import com.catnip.kokomputer.data.model.Cart
import com.catnip.kokomputer.data.repository.CartRepository
import com.catnip.kokomputer.data.repository.CartRepositoryImpl
import com.catnip.kokomputer.data.source.FirebaseAuth
import com.catnip.kokomputer.data.source.local.database.AppDatabase
import com.catnip.kokomputer.databinding.FragmentCartBinding
import com.catnip.kokomputer.presentation.checkout.CheckoutActivity
import com.catnip.kokomputer.presentation.common.adapter.CartListAdapter
import com.catnip.kokomputer.presentation.common.adapter.CartListener
import com.catnip.kokomputer.utils.GenericViewModelFactory
import com.catnip.kokomputer.utils.hideKeyboard
import com.catnip.kokomputer.utils.proceedWhen
import com.catnip.kokomputer.utils.toDollarFormat
import org.koin.android.ext.android.inject


class CartFragment : Fragment() {

    private lateinit var binding: FragmentCartBinding

    private val auth : FirebaseAuth by inject()

    private val viewModel: CartViewModel by viewModels {
        val db = AppDatabase.createInstance(requireContext())
        val ds: CartDataSource = CartDatabaseDataSource(db.cartDao())
        val rp: CartRepository = CartRepositoryImpl(ds)
        GenericViewModelFactory.create(CartViewModel(rp))
    }

    private val adapter: CartListAdapter by lazy {
        CartListAdapter(object : CartListener {
            override fun onPlusTotalItemCartClicked(cart: Cart) {
                viewModel.increaseCart(cart)
            }

            override fun onMinusTotalItemCartClicked(cart: Cart) {
                viewModel.decreaseCart(cart)
            }

            override fun onRemoveCartClicked(cart: Cart) {
                viewModel.removeCart(cart)
            }

            override fun onUserDoneEditingNotes(cart: Cart) {
                viewModel.setCartNotes(cart)
                hideKeyboard()
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        binding = FragmentCartBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupList()
        observeData()
        setClickListeners()
        getUser()
    }
    private fun getUser() {
        val user = auth.getCurrentUser()
        Log.d("AUTH", "getUser: FROM CART ${auth.hashCode()} user hash = ${user.hashCode()}")
    }

    private fun setClickListeners() {
        binding.btnCheckout.setOnClickListener {
            startActivity(Intent(requireContext(), CheckoutActivity::class.java))
        }
    }

    private fun observeData() {
        viewModel.getAllCarts().observe(viewLifecycleOwner) { result ->
            result.proceedWhen(
                doOnLoading = {
                    binding.layoutState.root.isVisible = true
                    binding.layoutState.pbLoading.isVisible = true
                    binding.layoutState.tvError.isVisible = false
                    binding.rvCart.isVisible = false
                },
                doOnSuccess = {
                    binding.layoutState.root.isVisible = false
                    binding.layoutState.pbLoading.isVisible = false
                    binding.layoutState.tvError.isVisible = false
                    binding.rvCart.isVisible = true
                    result.payload?.let { (carts, totalPrice) ->
                        //set list cart data
                        adapter.submitData(carts)
                        binding.tvTotalPrice.text = totalPrice.toDollarFormat()
                    }
                },
                doOnError = {
                    binding.layoutState.root.isVisible = true
                    binding.layoutState.pbLoading.isVisible = false
                    binding.layoutState.tvError.isVisible = true
                    binding.layoutState.tvError.text = result.exception?.message.orEmpty()
                    binding.rvCart.isVisible = false
                },
                doOnEmpty = {
                    binding.layoutState.root.isVisible = true
                    binding.layoutState.pbLoading.isVisible = false
                    binding.layoutState.tvError.isVisible = true
                    binding.layoutState.tvError.text = getString(R.string.text_cart_is_empty)
                    binding.rvCart.isVisible = false
                    result.payload?.let { (carts, totalPrice) ->
                        binding.tvTotalPrice.text = totalPrice.toDollarFormat()
                    }
                }
            )
        }
    }

    private fun setupList() {
        binding.rvCart.adapter = this@CartFragment.adapter
    }
}