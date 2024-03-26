package com.catnip.kokomputer.presentation.detailproduct

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.catnip.kokomputer.data.model.Product
import com.catnip.kokomputer.databinding.ActivityDetailProductBinding
import com.catnip.kokomputer.utils.GenericViewModelFactory
import com.catnip.kokomputer.utils.toDollarFormat

class DetailProductActivity : AppCompatActivity() {
    private val binding : ActivityDetailProductBinding by lazy {
        ActivityDetailProductBinding.inflate(layoutInflater)
    }
    private val viewModel: DetailProductViewModel by viewModels {
        GenericViewModelFactory.create(
            DetailProductViewModel(intent?.extras)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        bindProduct(viewModel.product)
        setClickListener()
        observeData()
    }

    private fun setClickListener() {
        binding.ivBack.setOnClickListener {
            onBackPressed()
        }
        binding.ivMinus.setOnClickListener {
            viewModel.minus()
        }
        binding.ivPlus.setOnClickListener {
            viewModel.add()
        }
    }
    private fun bindProduct(product: Product?) {
        product?.let { item ->
            binding.ivProductImage.load(item.imgUrl) {
                crossfade(true)
            }
            binding.tvProductName.text = item.name
            binding.tvProductDesc.text = item.desc
            binding.tvProductPrice.text = item.price.toDollarFormat()
            binding.tvProductRating.text = item.rating.toString()
        }
    }

    private fun observeData() {
        viewModel.priceLiveData.observe(this) {
            binding.tvCalculatedProductPrice.text = it.toDollarFormat()
        }
        viewModel.productCountLiveData.observe(this) {
            binding.tvProductCount.text = it.toString()
        }
    }
    companion object {
        const val EXTRA_PRODUCT = "EXTRA_PRODUCT"
        fun startActivity(context: Context, product: Product) {
            val intent = Intent(context, DetailProductActivity::class.java)
            intent.putExtra(EXTRA_PRODUCT, product)
            context.startActivity(intent)
        }
    }
}