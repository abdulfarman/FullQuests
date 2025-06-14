package com.example.quests.domain.model

import com.example.quests.data.model.PostListDto

data class PostListUi(
    val userId: Int?,
    val id: Int?,
    val title: String,
    val body: String
)

fun PostListDto.toPostListUi(): PostListUi {
    return PostListUi(
        userId = userId,
        id = id,
        title = title ?: "NA",
        body = body ?: "NA"
    )
}
