package com.example.quests.data.remote

import com.example.quests.data.model.PostDetailDto
import com.example.quests.data.model.PostListDto
import retrofit2.http.GET
import retrofit2.http.Path


interface PostApi {
    @GET("posts")
    suspend fun fetchPostList(): List<PostListDto>

    @GET("posts/{id}")
    suspend fun fetchPostById(
        @Path("id") id: Int
    ): PostDetailDto
}
