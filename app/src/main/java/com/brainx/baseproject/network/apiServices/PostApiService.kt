package com.brainx.baseproject.network.apiServices

import com.brainx.baseproject.utils.ApiUrls
import com.brainx.baseproject.models.Post
import retrofit2.Response
import retrofit2.http.GET

interface PostApiService {
    @GET(ApiUrls.POST)
    suspend fun getPostList(): Response<List<Post>>
}