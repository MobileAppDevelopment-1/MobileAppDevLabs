package com.example.assignment8.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment8.R
import com.example.assignment8.databinding.ActivityPostDetailBinding
import com.example.assignment8.ui.adapters.CommentAdapter
import com.example.assignment8.ui.viewmodels.PostDetailViewModel

class PostDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPostDetailBinding
    private val viewModel: PostDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val postId = intent.getIntExtra("POST_ID", -1)
        if (postId == -1) finish()

        setupRecyclerView()
        setupObservers()
        viewModel.loadPostDetails(postId)
    }

    private fun setupRecyclerView() {
        binding.rvComments.layoutManager = LinearLayoutManager(this)
        binding.rvComments.adapter = CommentAdapter(mutableListOf())
    }

    private fun setupObservers() {
        viewModel.postDetails.observe(this) { (post, comments) ->
            binding.tvPostTitle.text = post.title
            binding.tvPostBody.text = post.body
            (binding.rvComments.adapter as CommentAdapter).updateData(comments)
        }

        viewModel.errorMessage.observe(this) { error ->
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        fun start(context: Context, postId: Int) {
            val intent = Intent(context, PostDetailActivity::class.java).apply {
                putExtra("POST_ID", postId)
            }
            context.startActivity(intent)
        }
    }
}
