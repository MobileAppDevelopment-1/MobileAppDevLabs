package com.example.assignment8.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment8.api.dto.Comment
import com.example.assignment8.databinding.CommentItemBinding

class CommentAdapter(private var comments: List<Comment>) :
    RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val binding = CommentItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(comments[position])
    }

    override fun getItemCount(): Int = comments.size

    fun updateData(newComments: List<Comment>) {
        comments = newComments
        notifyDataSetChanged()
    }

    class CommentViewHolder(private val binding: CommentItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(comment: Comment) {
            binding.tvCommentName.text = comment.name
            binding.tvCommentEmail.text = comment.email
            binding.tvCommentBody.text = comment.body
        }
    }
}
