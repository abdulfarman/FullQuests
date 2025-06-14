package com.example.quests.ui.screen.products

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quests.domain.model.PostListUi
import com.example.quests.domain.usecase.FetchPostListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostListViewModel @Inject constructor(
    private val fetchPostListUseCase: FetchPostListUseCase
) : ViewModel() {

    private val TAG = "PostListViewModel"
    var state by mutableStateOf(ProductUiState())
        private set

    init {
        loadPosts()
    }

    fun loadPosts() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            try {
                val products = fetchPostListUseCase()
                state = state.copy(products = products, isLoading = false)
            } catch (e: Exception) {
                state = state.copy(error = "Error in Fetching Posts", isLoading = false)
                Log.e(TAG, "Error loading posts: ${e.message}", e)
            }
        }
    }
}

data class ProductUiState(
    val products: List<PostListUi> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
