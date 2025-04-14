package com.example.assignment5.ui.components

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.assignment5.R
import com.example.assignment5.databinding.PageselectorLayoutBinding

class PageSelector(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    private val binding: PageselectorLayoutBinding
    private var selectedPage: Int = 0
    private var totalPages: Int = 0
    private val pageButtons = mutableListOf<Button>()
    private var onPageSelectedListener: ((page: Int) -> Unit)? = null

    init {
        val inflater = LayoutInflater.from(context)
        binding = PageselectorLayoutBinding.inflate(inflater, this, true)
        setupInitialPages()
        setupEventListeners()
        readXmlAttrs(attrs)
    }

    private fun readXmlAttrs(attrs: AttributeSet?) {
        if (attrs == null) return

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.PageSelector)
        try {
            totalPages = typedArray.getInt(R.styleable.PageSelector_initialPageCount, 5)
            selectedPage = typedArray.getInt(R.styleable.PageSelector_initialSelectedPage, 1)
            createPageButtons(totalPages)
        } finally {
            typedArray.recycle()
        }
    }

    private fun setupInitialPages() {
        createPageButtons(totalPages)
    }

    private fun createPageButtons(count: Int) {
        binding.pagesContainer.removeAllViews()
        pageButtons.clear()
        totalPages = count

        val screenWidth = Resources.getSystem().displayMetrics.widthPixels
        val minButtonWidth = 100.dpToPx()
        val maxButtonsPerRow = maxOf(1, screenWidth / minButtonWidth)
        val actualButtons = minOf(count, maxButtonsPerRow)

        for (i in 1..count) {
            val button = Button(context).apply {
                text = i.toString()
                layoutParams = LinearLayout.LayoutParams(
                    0,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1f
                ).apply {
                    marginEnd = 8.dpToPx()
                    minimumWidth = minButtonWidth
                }
                setOnClickListener { onPageButtonClicked(i) }
                updateButtonStyle(i)
            }
            binding.pagesContainer.addView(button)
            pageButtons.add(button)

            if (i % actualButtons == 0 && i != count) {
                binding.pagesContainer.addView(View(context).apply {
                    layoutParams = LayoutParams(
                        LayoutParams.MATCH_PARENT,
                        1
                    )
                    setBackgroundColor(Color.LTGRAY)
                })
            }
        }
    }

    private fun onPageButtonClicked(page: Int) {
        selectedPage = page
        updateButtonStyles()
        Toast.makeText(context, "Page changed to $page", Toast.LENGTH_SHORT).show()
        onPageSelectedListener?.invoke(page)
    }

    private fun updateButtonStyles() {
        pageButtons.forEachIndexed { index, button ->
            updateButtonStyle(index + 1)
        }
    }

    private fun updateButtonStyle(page: Int) {
        val button = pageButtons.getOrNull(page - 1) ?: return
        if (page == selectedPage) {
            button.setBackgroundColor(ContextCompat.getColor(context, R.color.sky_green))
            button.setTextColor(Color.WHITE)
        } else {
            button.setBackgroundColor(ContextCompat.getColor(context, R.color.sky_blue))
            button.setTextColor(Color.BLACK)
        }
    }

    private fun setupEventListeners() {
        binding.confirmButton.setOnClickListener {
            val input = binding.pageInput.text.toString()
            if (input.isNotEmpty()) {
                val newPageCount = input.toIntOrNull() ?: 5
                if (newPageCount > 0) {
                    createPageButtons(newPageCount)
                    if (selectedPage > newPageCount) {
                        selectedPage = newPageCount
                        onPageSelectedListener?.invoke(selectedPage)
                    }
                    updateButtonStyles()
                } else {
                    Toast.makeText(context, "Please enter a valid number", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun Int.dpToPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()

    fun setSelectedPage(page: Int, showToast: Boolean = false) {
        if (page in 1..totalPages) {
            selectedPage = page
            updateButtonStyles()
            if (showToast) {
                Toast.makeText(context, "Jumped to page $page", Toast.LENGTH_SHORT).show()
            }
            onPageSelectedListener?.invoke(page)
        }
    }
}
