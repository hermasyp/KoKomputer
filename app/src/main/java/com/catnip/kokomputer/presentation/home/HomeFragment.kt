package com.catnip.kokomputer.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.catnip.kokomputer.data.datasource.category.CategoryApiDataSource
import com.catnip.kokomputer.data.datasource.category.CategoryDataSource
import com.catnip.kokomputer.data.datasource.product.ProductApiDataSource
import com.catnip.kokomputer.data.datasource.product.ProductDataSource
import com.catnip.kokomputer.data.datasource.user.UserDataSource
import com.catnip.kokomputer.data.datasource.user.UserDataSourceImpl
import com.catnip.kokomputer.data.model.Category
import com.catnip.kokomputer.data.model.Product
import com.catnip.kokomputer.data.repository.CategoryRepository
import com.catnip.kokomputer.data.repository.CategoryRepositoryImpl
import com.catnip.kokomputer.data.repository.ProductRepository
import com.catnip.kokomputer.data.repository.ProductRepositoryImpl
import com.catnip.kokomputer.data.repository.UserRepository
import com.catnip.kokomputer.data.repository.UserRepositoryImpl
import com.catnip.kokomputer.data.source.local.pref.UserPreference
import com.catnip.kokomputer.data.source.local.pref.UserPreferenceImpl
import com.catnip.kokomputer.data.source.network.services.KoKomputerApiService
import com.catnip.kokomputer.databinding.FragmentHomeBinding
import com.catnip.kokomputer.presentation.detailproduct.DetailProductActivity
import com.catnip.kokomputer.presentation.home.adapter.CategoryListAdapter
import com.catnip.kokomputer.presentation.home.adapter.ProductListAdapter
import com.catnip.kokomputer.presentation.main.MainActivity
import com.catnip.kokomputer.presentation.main.MainViewModel
import com.catnip.kokomputer.utils.GenericViewModelFactory
import com.catnip.kokomputer.utils.GridSpacingItemDecoration
import com.catnip.kokomputer.utils.proceedWhen

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeViewModel by viewModels {
        val service = KoKomputerApiService.invoke()
        val userPreference: UserPreference = UserPreferenceImpl(requireContext())
        val userDataSource: UserDataSource = UserDataSourceImpl(userPreference)
        val userRepository: UserRepository = UserRepositoryImpl(userDataSource)
        val productDataSource: ProductDataSource = ProductApiDataSource(service)
        val productRepository: ProductRepository = ProductRepositoryImpl(productDataSource)
        val categoryDataSource: CategoryDataSource = CategoryApiDataSource(service)
        val categoryRepository: CategoryRepository = CategoryRepositoryImpl(categoryDataSource)
        GenericViewModelFactory.create(
            HomeViewModel(
                categoryRepository,
                productRepository,
                userRepository
            )
        )
    }

    private val mainViewModel : MainViewModel by activityViewModels()

    private val categoryAdapter: CategoryListAdapter by lazy {
        CategoryListAdapter {
            // when category clicked
            getProductData(it.slug)
        }
    }

    private fun applyUiMode() {
        val isUsingDarkMode = viewModel.isUsingDarkMode()
        AppCompatDelegate.setDefaultNightMode(
            if (isUsingDarkMode)
                AppCompatDelegate.MODE_NIGHT_YES
            else
                AppCompatDelegate.MODE_NIGHT_NO
        )
        binding.swDarkMode.isChecked = isUsingDarkMode
    }

    private fun setSwitchListener() {
        binding.swDarkMode.setOnCheckedChangeListener { btn, isChecked ->
            viewModel.setUsingDarkMode(isChecked)
            applyUiMode()
        }
    }

    private val productAdapter: ProductListAdapter by lazy {
        ProductListAdapter {
            DetailProductActivity.startActivity(requireContext(), it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
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
        viewModel.getCategories().observe(viewLifecycleOwner) {
            it.proceedWhen(
                doOnSuccess = {
                    it.payload?.let { data -> bindCategoryList(data) }
                }
            )
        }
    }

    private fun getProductData(categorySlug: String? = null) {
        viewModel.getProducts(categorySlug).observe(viewLifecycleOwner) {
            it.proceedWhen(
                doOnSuccess = {
                    it.payload?.let { data -> bindProductList(data) }
                }
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
        Toast.makeText(requireContext(), mainViewModel.myName, Toast.LENGTH_SHORT).show()
        if (requireActivity() !is MainActivity) return
        (requireActivity() as MainActivity).navigateToTabProfile()
    }
}