package com.example.quests.domain.usecase

import com.example.quests.domain.model.ProductUi
import com.example.quests.domain.model.toUi
import com.example.quests.domain.repository.ProductRepository

class GetProductsUseCase(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(query: String): List<ProductUi> {
        val products = repository.searchProducts(query)
        return products.map { it.toUi() }
    }
}
