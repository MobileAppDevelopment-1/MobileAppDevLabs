package com.example.assignment8.api

import com.example.assignment8.api.dto.Comments
import com.example.assignment8.api.dto.Post
import com.example.assignment8.api.dto.Posts
import retrofit2.http.GET
import retrofit2.http.Path

interface JsonPlaceHolderService {
    @GET("posts")
    suspend fun getPosts(): Posts

    @GET("posts/{id}")
    suspend fun getPostById(@Path("id") id: Int): Post

    @GET("posts/{id}/comments")
    suspend fun getCommentsByPostId(@Path("id") postId: Int): Comments
}
