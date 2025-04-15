package com.example.assignment8.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment8.api.dto.Posts
import com.example.assignment8.repositories.PostRepository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _postList: MutableLiveData<Posts> =
        MutableLiveData(arrayListOf())
    val postList: LiveData<Posts> = _postList

    fun getPostList() {
        viewModelScope.launch {
            val response = PostRepository.getAllPosts()
            _postList.postValue(response)
        }
    }
}
