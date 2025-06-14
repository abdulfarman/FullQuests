package com.example.quests.domain.usecase

import com.example.quests.domain.model.PostListUi
import com.example.quests.domain.model.toUi
import com.example.quests.domain.repository.PostRepository

class FetchPostListUseCase(
    private val repository: PostRepository
) {
    suspend operator fun invoke(): List<PostListUi> {
        val posts = repository.fetchPostList()
        return posts.map { it.toUi() }
    }
}
