package com.example.quests.domain.repository

import com.example.quests.data.model.ProductDto

interface ProductRepository {
    suspend fun searchProducts(query: String): List<ProductDto>
}
