package com.catnip.kokomputer.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.catnip.kokomputer.R
import com.catnip.kokomputer.data.datasource.category.DummyCategoryDataSource
import com.catnip.kokomputer.data.datasource.product.DummyProductDataSource
import com.catnip.kokomputer.data.model.Category
import com.catnip.kokomputer.data.model.Product
import com.catnip.kokomputer.data.repository.CategoryRepository
import com.catnip.kokomputer.data.repository.CategoryRepositoryImpl
import com.catnip.kokomputer.data.repository.ProductRepository
import com.catnip.kokomputer.data.repository.ProductRepositoryImpl
import com.catnip.kokomputer.databinding.FragmentCartBinding
import com.catnip.kokomputer.databinding.FragmentHomeBinding
import com.catnip.kokomputer.presentation.home.adapter.CategoryListAdapter
import com.catnip.kokomputer.presentation.home.adapter.ProductListAdapter
import com.catnip.kokomputer.utils.GenericViewModelFactory
import com.catnip.kokomputer.utils.GridSpacingItemDecoration

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeViewModel by viewModels {
        val productDataSource = DummyProductDataSource()
        val productRepository: ProductRepository = ProductRepositoryImpl(productDataSource)
        val categoryDataSource = DummyCategoryDataSource()
        val categoryRepository: CategoryRepository = CategoryRepositoryImpl(categoryDataSource)
        GenericViewModelFactory.create(HomeViewModel(categoryRepository, productRepository))
    }

    private val categoryAdapter: CategoryListAdapter by lazy {
        CategoryListAdapter {

        }
    }

    private val productAdapter: ProductListAdapter by lazy {
        ProductListAdapter {

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
        bindCategoryList(viewModel.getCategories())
        bindProductList(viewModel.getProducts())
    }

    private fun bindCategoryList(data: List<Category>) {
        binding.rvCategory.apply {
            adapter = categoryAdapter
        }
        categoryAdapter.submitData(data)
    }

    private fun bindProductList(data: List<Product>) {
        val itemDecoration = GridSpacingItemDecoration(2, 12, true)
        binding.rvProductList.apply {
            adapter = productAdapter
            addItemDecoration(itemDecoration)
        }
        productAdapter.submitData(data)
    }
}