package com.example.quests.domain.repository

import com.example.quests.data.model.PostListDto

interface PostRepository {
    suspend fun fetchPostList(): List<PostListDto>
}
