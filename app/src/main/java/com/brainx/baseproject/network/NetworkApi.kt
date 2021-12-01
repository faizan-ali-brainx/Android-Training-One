package com.brainx.baseproject.network

import com.brainx.baseproject.network.apiServices.PostApiService
import com.brainx.androidbase.network.NetworkApi

val postService: PostApiService by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
    NetworkApi.INSTANCE.getApi(PostApiService::class.java)
}