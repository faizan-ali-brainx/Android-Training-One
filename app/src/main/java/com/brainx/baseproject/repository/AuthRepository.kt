package com.brainx.baseproject.repository

import com.brainx.baseproject.api.SharedPreference
import com.brainx.baseproject.network.apiServices.PostApiService
import com.brainx.baseproject.network.postService
import com.brainx.androidbase.ext.request
import com.brainx.androidbase.models.AppException
import com.brainx.androidbase.network.NetworkApi
import com.brainx.androidbase.network.ResultState
import com.brainx.baseproject.models.Post
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val sPref: SharedPreference
) {

    suspend fun getPost(data: (List<Post>?) -> Unit, error: (AppException) -> Unit) {
        request({
            NetworkApi.INSTANCE.getApi(PostApiService::class.java).getPostList()
        }, {
            when (it) {
                is ResultState.Error -> error(it.error)
                is ResultState.Success -> data(it.data)
                is ResultState.Headers -> {
                    //Can handle Headers here
                }
            }
        })
    }

    suspend fun getPost1(data: (List<Post>?) -> Unit, error: (AppException) -> Unit) {
        request({
            postService.getPostList()
        }, {
            when (it) {
                is ResultState.Error -> error(it.error)
                is ResultState.Success -> data(it.data)
                is ResultState.Headers -> {
                    //Can handle Headers here
                }
            }
        })
    }


}
