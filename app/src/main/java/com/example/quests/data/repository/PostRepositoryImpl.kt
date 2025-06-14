package com.example.quests.data.repository

import com.example.quests.data.model.PostDetailDto
import com.example.quests.data.model.PostListDto
import com.example.quests.data.remote.PostApi
import com.example.quests.domain.repository.PostRepository

class PostRepositoryImpl(
    private val api: PostApi
) : PostRepository {

    override suspend fun fetchPostList(): List<PostListDto> {
        return api.fetchPostList()
    }

    override suspend fun fetchPostById(id: Int): PostDetailDto {
        return api.fetchPostById(id)
    }
}
