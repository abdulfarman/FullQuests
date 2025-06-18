package com.example.quests.ui.screen.post

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quests.di.IoDispatcher
import com.example.quests.domain.model.PostListUi
import com.example.quests.domain.usecase.FetchPostListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostListViewModel @Inject constructor(
    private val fetchPostListUseCase: FetchPostListUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val TAG = "PostListViewModel"
    var state by mutableStateOf(PostUiState())
        private set

    init {
        loadPosts()
    }

    fun loadPosts() {
        viewModelScope.launch(ioDispatcher) {
            state = state.copy(isLoading = true)
            try {
                val posts = fetchPostListUseCase()
                state = state.copy(post = posts, isLoading = false)
            } catch (e: Exception) {
                state = state.copy(error = "Error in Fetching Posts", isLoading = false)
                Log.e(TAG, "Error loading posts: ${e.message}", e)
            }
        }
    }
}

data class PostUiState(
    val post: List<PostListUi> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
