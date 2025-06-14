package com.example.quests.domain.usecase

import com.example.quests.domain.model.PostDetailUi
import com.example.quests.domain.model.toPostDetailUi
import com.example.quests.domain.repository.PostRepository

class FetchPostByIdUseCase(
    private val repository: PostRepository
) {
    suspend operator fun invoke(id: Int): PostDetailUi {
        val post = repository.fetchPostById(id)
        return post.toPostDetailUi()
    }
}
