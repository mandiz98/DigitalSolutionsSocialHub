package com.example.digital_solutions_social_hub.ui.custom_views

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import com.example.digital_solutions_social_hub.R
import com.example.digital_solutions_social_hub.databinding.ViewAppToolBarBinding

class AppToolbar(context: Context, attributeSet: AttributeSet) :
    LinearLayout(context, attributeSet) {

    private val binding = ViewAppToolBarBinding.inflate(LayoutInflater.from(context), this, true)

    var title: String
        get() = binding.txtTitle.text.toString()
        set(value) {
            binding.txtTitle.text = value
            invalidate()
        }

    var status: String?
        get() = binding.txtStatus.text.toString()
        set(value) {
            binding.txtStatus.text = value
        }

    var iconDrawable: Drawable? = null
        set(value) {
            binding.imgIcon.setImageDrawable(value)
        }

    private var onMenuItemClickListener: (() -> Unit)? = null
    fun onMenuItemClickListener(function: () -> Unit) {
        onMenuItemClickListener = function
    }

    init {
        context.theme.obtainStyledAttributes(attributeSet, R.styleable.AppToolbar, 0, 0).apply {
            try {
                binding.txtTitle.text = getString(R.styleable.AppToolbar_title) ?: ""
                binding.txtStatus.text = getString(R.styleable.AppToolbar_status) ?: ""
                binding.btnBack.setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        R.drawable.ic_back
                    )
                )
                binding.imgIcon.setImageDrawable(getDrawable(R.styleable.AppToolbar_imgIcon))
            } finally {
                recycle()
            }
        }
        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.imgIcon.setOnClickListener {
            onMenuItemClickListener?.invoke()
        }
    }
}