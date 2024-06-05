package com.catnip.kokomputer.presentation.common.views

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.catnip.kokomputer.R
import com.catnip.kokomputer.databinding.LayoutContentStateBinding

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class ContentStateView
    @JvmOverloads
    constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0,
    ) : ConstraintLayout(context, attrs, defStyleAttr) {
        private var binding: LayoutContentStateBinding

        private var listener: ContentStateListener? = null

        init {
            inflate(context, R.layout.layout_content_state, this)
            binding = LayoutContentStateBinding.bind(this)
        }

        fun setListener(listener: ContentStateListener) {
            this.listener = listener
        }

        fun setState(
            state: ContentState,
            message: String? = null,
            @DrawableRes imgRes: Int? = null,
        ) {
            when (state) {
                ContentState.SUCCESS -> {
                    binding.root.isVisible = false
                    binding.pbLoading.isVisible = false
                    binding.ivContentState.isVisible = false
                    binding.tvError.isVisible = false
                    listener?.onContentShow(true)
                }

                ContentState.LOADING -> {
                    binding.root.isVisible = true
                    binding.pbLoading.isVisible = true
                    binding.ivContentState.isVisible = false
                    binding.tvError.isVisible = false
                    listener?.onContentShow(false)
                }

                ContentState.EMPTY -> {
                    binding.root.isVisible = true
                    binding.pbLoading.isVisible = false
                    binding.ivContentState.isVisible = true
                    binding.tvError.isVisible = true

                    imgRes?.let { binding.ivContentState.setImageResource(it) }
                        ?: run { binding.ivContentState.setImageResource(R.drawable.ic_state_empty) }
                    message?.let { binding.tvError.text = it } ?: run {
                        binding.tvError.text = context.getString(R.string.text_empty_data)
                    }
                    listener?.onContentShow(false)
                }

                ContentState.ERROR_NETWORK -> {
                    binding.root.isVisible = true
                    binding.pbLoading.isVisible = false
                    binding.ivContentState.isVisible = true
                    binding.tvError.isVisible = true
                    imgRes?.let { binding.ivContentState.setImageResource(it) }
                        ?: run { binding.ivContentState.setImageResource(R.drawable.ic_state_error_internet) }
                    message?.let { binding.tvError.text = it } ?: run {
                        binding.tvError.text = context.getString(R.string.no_internet_connection)
                    }
                    listener?.onContentShow(false)
                }

                ContentState.ERROR_GENERAL -> {
                    binding.root.isVisible = true
                    binding.pbLoading.isVisible = false
                    binding.ivContentState.isVisible = true
                    binding.tvError.isVisible = true
                    imgRes?.let { binding.ivContentState.setImageResource(it) }
                        ?: run { binding.ivContentState.setImageResource(R.drawable.ic_state_error_general) }
                    message?.let { binding.tvError.text = it } ?: run {
                        binding.tvError.text =
                            context.getString(R.string.error_when_getting_the_data_please_try_again_later)
                    }
                    listener?.onContentShow(false)
                }
            }
        }
    }

interface ContentStateListener {
    fun onContentShow(isContentShow: Boolean)
}

enum class ContentState {
    SUCCESS,
    LOADING,
    EMPTY,
    ERROR_NETWORK,
    ERROR_GENERAL,
}
