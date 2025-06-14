package com.example.quests.data.repository

import com.example.quests.data.model.ProductDto
import com.example.quests.data.remote.ProductApi
import com.example.quests.domain.repository.ProductRepository

class ProductRepositoryImpl(
    private val api: ProductApi
) : ProductRepository {
    override suspend fun searchProducts(query: String): List<ProductDto> {
        return api.searchProducts(query).products
    }
}
