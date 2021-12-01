package com.brainx.baseproject.activaties

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.work.*
import com.brainx.baseproject.base.BaseActivity
import com.brainx.baseproject.databinding.ActivityWorkManagerBinding
import com.brainx.baseproject.viewModels.WorkManagerViewModel
import com.brainx.baseproject.workManager.ObserveWorkProgress
import com.brainx.baseproject.workManager.OneTimeRequest
import com.brainx.baseproject.workManager.PeriodicRequest
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class WorkManagerActivity : BaseActivity<WorkManagerViewModel, ActivityWorkManagerBinding>()
{
    override val mViewModel: WorkManagerViewModel by viewModels()
    override fun getViewBinding() = ActivityWorkManagerBinding.inflate(layoutInflater)

    override fun customOnCreate(savedInstanceState: Bundle?) {
        mViewBinding.apply {
            viewModel = mViewModel


            btnOneTimeWork.setOnClickListener {
                val oneTimeRequest : WorkRequest = OneTimeWorkRequestBuilder<OneTimeRequest>().build()
                WorkManager.getInstance(this@WorkManagerActivity).enqueue(oneTimeRequest)
            }
            btnPeriodicWork.setOnClickListener {
                val recurringRequest : WorkRequest = PeriodicWorkRequestBuilder<PeriodicRequest>(10, TimeUnit.SECONDS)
                    .build()
                WorkManager.getInstance(this@WorkManagerActivity).enqueue(recurringRequest)
            }
            btnCancelPeriodicWork.setOnClickListener {
                WorkManager.getInstance(this@WorkManagerActivity).cancelAllWork()
            }
            btnConstrainedWork.setOnClickListener {
                val wifiConstraints = Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.UNMETERED)
                    .build()
                val wifiRequiredRequest : WorkRequest = OneTimeWorkRequestBuilder<OneTimeRequest>()
                    .setConstraints(wifiConstraints)
                    .build()
                WorkManager.getInstance(this@WorkManagerActivity).enqueue(wifiRequiredRequest)
            }
            btnObserveWork.setOnClickListener {
                val observerRequest : WorkRequest = OneTimeWorkRequestBuilder<ObserveWorkProgress>().build()
                WorkManager.getInstance(this@WorkManagerActivity)
                    .getWorkInfoByIdLiveData(observerRequest.id)
                    .observe(this@WorkManagerActivity, Observer {
                        workInfo : WorkInfo? ->
                        if (workInfo != null && workInfo.state == WorkInfo.State.SUCCEEDED) {
                            Log.d("progress", "Work Successfully Completed")
                        }
                    })
                WorkManager.getInstance(this@WorkManagerActivity).enqueue(observerRequest)
            }
        }
    }

}