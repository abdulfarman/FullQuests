package com.example.quests.ui.screen.products

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quests.domain.model.ProductUi
import com.example.quests.domain.usecase.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {

    var state by mutableStateOf(ProductUiState())
        private set

    init {
        loadProducts("phone")
    }

    fun loadProducts(query: String) {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            try {
                val products = getProductsUseCase(query)
                state = state.copy(products = products, isLoading = false)
            } catch (e: Exception) {
                state = state.copy(error = e.message ?: "Unknown error", isLoading = false)
            }
        }
    }
}

data class ProductUiState(
    val products: List<ProductUi> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
