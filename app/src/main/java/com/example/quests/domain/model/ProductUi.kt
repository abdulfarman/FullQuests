package com.example.quests.domain.model

import com.example.quests.data.model.ProductDto
import com.example.quests.data.model.ProductEntity

data class ProductUi(
    val id: Int,
    val title: String,
    val description: String
)


fun ProductEntity.toUi(): ProductUi {
    return ProductUi(
        id = id,
        title = title,
        description = description
    )
}

fun ProductDto.toUi(): ProductUi {
    return ProductUi(
        id = id,
        title = title,
        description = description
    )
}
