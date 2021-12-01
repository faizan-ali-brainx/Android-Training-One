package com.brainx.baseproject.activaties

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.brainx.baseproject.base.BaseActivity
import com.brainx.baseproject.databinding.ActivityBaseBinding
import com.brainx.baseproject.viewModels.BaseViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BaseActivity : BaseActivity<BaseViewModel, ActivityBaseBinding>() {
    override val mViewModel: BaseViewModel by viewModels()
    override fun getViewBinding() = ActivityBaseBinding.inflate(layoutInflater)

    override fun customOnCreate(savedInstanceState: Bundle?) {
        mViewBinding.apply {
            viewModel = mViewModel
            startFirstActivityBtn.setOnClickListener {
                val firstIntent = Intent(this@BaseActivity, FirstActivity::class.java)
                result.launch(firstIntent)
            }
            startSecondActivityBtn.setOnClickListener {
                val secondIntent = Intent(this@BaseActivity, SecondActivity::class.java)
                result.launch(secondIntent)
            }
            startThirdActivityBtn.setOnClickListener {
                val thirdIntent = Intent(this@BaseActivity, ThirdActivity::class.java)
                result.launch(thirdIntent)
            }
            startWorkManagerBtn.setOnClickListener {
                val workIntent = Intent(this@BaseActivity, WorkManagerActivity::class.java)
                startActivity(workIntent)
            }
        }
    }

    // Region Register For Activity Result Start
    var result = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == 3000) {
            mViewModel.showToast("First Activity Closed")
        }
        if (result.resultCode == 3001) {
            mViewModel.showToast("Second Activity Closed")
        }
        if (result.resultCode == 3002) {
            mViewModel.showToast("Third Activity Closed")
        }
    }
    // Region Register For Activity Result End

}