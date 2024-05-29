package com.catnip.kokomputer.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.catnip.kokomputer.data.model.Category
import com.catnip.kokomputer.data.model.Product
import com.catnip.kokomputer.data.source.FirebaseAuth
import com.catnip.kokomputer.databinding.FragmentHomeBinding
import com.catnip.kokomputer.presentation.detailproduct.DetailProductActivity
import com.catnip.kokomputer.presentation.home.adapter.CategoryListAdapter
import com.catnip.kokomputer.presentation.home.adapter.ProductListAdapter
import com.catnip.kokomputer.presentation.main.MainActivity
import com.catnip.kokomputer.utils.ApiErrorException
import com.catnip.kokomputer.utils.GridSpacingItemDecoration
import com.catnip.kokomputer.utils.NoInternetException
import com.catnip.kokomputer.utils.proceedWhen
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    private val homeViewModel: HomeViewModel by viewModel()

    private val auth: FirebaseAuth by inject<FirebaseAuth>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth.doLogin()
    }

    private val categoryAdapter: CategoryListAdapter by lazy {
        CategoryListAdapter {
            // when category clicked
            getProductData(it.slug)
        }
    }

    private fun applyUiMode() {
        val isUsingDarkMode = homeViewModel.isUsingDarkMode()
        AppCompatDelegate.setDefaultNightMode(
            if (isUsingDarkMode) {
                AppCompatDelegate.MODE_NIGHT_YES
            } else {
                AppCompatDelegate.MODE_NIGHT_NO
            },
        )
        binding.swDarkMode.isChecked = isUsingDarkMode
    }

    private fun setSwitchListener() {
        binding.swDarkMode.setOnCheckedChangeListener { btn, isChecked ->
            homeViewModel.setUsingDarkMode(isChecked)
            applyUiMode()
        }
    }

    private val productAdapter: ProductListAdapter by lazy {
        ProductListAdapter {
            DetailProductActivity.startActivity(requireContext(), it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        applyUiMode()
        setupListProduct()
        setupListCategory()
        getCategoryData()
        getProductData(null)
        setSwitchListener()
        binding.layoutHeader.ivSettings.setOnClickListener {
            navigateToProfile()
        }
        getUser()
    }

    private fun getUser() {
        val user = auth.getCurrentUser()
        Log.d("AUTH", "getUser: FROM HOME ${auth.hashCode()} user hash = ${user.hashCode()}")
    }

    private fun setupListCategory() {
        binding.rvCategory.apply {
            adapter = categoryAdapter
        }
    }

    private fun setupListProduct() {
        val itemDecoration = GridSpacingItemDecoration(2, 12, true)
        binding.rvProductList.apply {
            adapter = productAdapter
            addItemDecoration(itemDecoration)
        }
    }

    private fun getCategoryData() {
        homeViewModel.getCategories().observe(viewLifecycleOwner) {
            it.proceedWhen(
                doOnSuccess = {
                    it.payload?.let { data -> bindCategoryList(data) }
                },
                doOnError = {
                    if(it.exception is ApiErrorException){
                        val errorBody = it.exception.errorResponse
                        Log.d("Error", "getCategoryData: ${errorBody.message}")
                    }else if(it.exception is NoInternetException){

                    }
                }
            )
        }
    }

    private fun getProductData(categorySlug: String? = null) {
        homeViewModel.getProducts(categorySlug).observe(viewLifecycleOwner) {
            it.proceedWhen(
                doOnSuccess = {
                    it.payload?.let { data -> bindProductList(data) }
                },
            )
        }
    }

    private fun bindCategoryList(data: List<Category>) {
        categoryAdapter.submitData(data)
    }

    private fun bindProductList(data: List<Product>) {
        productAdapter.submitData(data)
    }

    private fun navigateToProfile() {
        if (requireActivity() !is MainActivity) return
        (requireActivity() as MainActivity).navigateToTabProfile()
    }
}
