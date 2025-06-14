package com.example.quests.domain.model

import com.example.quests.data.model.PostDetailDto

data class PostDetailUi(
    val userId: Int?,
    val id: Int?,
    val title: String,
    val body: String
)

fun PostDetailDto.toPostDetailUi(): PostDetailUi {
    return PostDetailUi(
        userId = userId,
        id = id,
        title = title ?: "NA",
        body = body ?: "NA"
    )
}
