package com.dog.ui.screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.dog.data.model.Comment
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel : ViewModel() {
    private val _likes = MutableStateFlow(0)
    val likes: StateFlow<Int> get() = _likes

    private val _isLiked = MutableStateFlow(false)
    val isLiked: StateFlow<Boolean> get() = _isLiked

    fun toggleLikeStatus() {
        _isLiked.value = !_isLiked.value
        if (_isLiked.value) {
            _likes.value += 1
        } else {
            _likes.value -= 1
        }
    }

    private val _content = mutableStateOf("")
    val content: State<String> get() = _content

    private val _commentsList = mutableStateListOf<Comment>()
    val commentsList: List<Comment> get() = _commentsList

    private val _commentsCount = MutableStateFlow(0)
    val commentsCount: StateFlow<Int> get() = _commentsCount

    fun addComment(comment: Comment) {
        _commentsList.add(comment)
        _commentsCount.value = _commentsList.size
    }

}
