package com.brainx.baseproject.workManager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.brainx.androidbase.appContext
import com.brainx.baseproject.utils.countDown
import kotlinx.coroutines.delay

class ObserveWorkProgress(appContext: Context, workerParameters: WorkerParameters) :
    CoroutineWorker(appContext, workerParameters){

    override suspend fun doWork(): Result {
        for (i in 1..10) {
            appContext.countDown(i)
            delay(1000)
        }
        return Result.success()
    }
}