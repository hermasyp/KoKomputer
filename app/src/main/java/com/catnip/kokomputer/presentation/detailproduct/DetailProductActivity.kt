package com.catnip.kokomputer.presentation.detailproduct

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.catnip.kokomputer.R
import com.catnip.kokomputer.databinding.ActivityCheckoutBinding
import com.catnip.kokomputer.databinding.ActivityDetailProductBinding

class DetailProductActivity : AppCompatActivity() {
    private val binding : ActivityDetailProductBinding by lazy {
        ActivityDetailProductBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}