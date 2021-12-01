package com.brainx.baseproject.base

import com.brainx.androidbase.base.BxBaseApp
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApp : BxBaseApp() {
    override fun onCreate() {
        super.onCreate()
        setNetworkBaseUrl("https://jsonplaceholder.typicode.com/")
    }
}
