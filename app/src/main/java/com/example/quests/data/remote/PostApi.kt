package com.example.quests.data.remote

import com.example.quests.data.model.PostListDto
import retrofit2.http.GET


interface PostApi {
    @GET("posts")
    suspend fun fetchPostList(): List<PostListDto>
}
