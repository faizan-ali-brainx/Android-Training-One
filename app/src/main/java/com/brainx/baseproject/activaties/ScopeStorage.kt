package com.brainx.baseproject.activaties

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.brainx.baseproject.base.BaseActivity
import com.brainx.baseproject.databinding.ActivityScopeStorageBinding
import com.brainx.baseproject.databinding.ActivityWorkManagerBinding
import com.brainx.baseproject.utils.checkStoragePermission
import com.brainx.baseproject.utils.openSettings
import com.brainx.baseproject.utils.runTimePermissions
import com.brainx.baseproject.viewModels.ScopeStorageViewModel
import com.brainx.baseproject.viewModels.WorkManagerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScopeStorage : BaseActivity<ScopeStorageViewModel, ActivityScopeStorageBinding>() {
    override val mViewModel: ScopeStorageViewModel by viewModels()
    override fun getViewBinding() = ActivityScopeStorageBinding.inflate(layoutInflater)

    override fun customOnCreate(savedInstanceState: Bundle?) {
        mViewBinding.apply {
            viewModel = mViewModel
            accessStorageData()
        }
    }

    private fun accessStorageData() {
        if (this.checkStoragePermission()) {

        } else {
            requestExternalStoragePermission()
        }
    }

    private fun requestExternalStoragePermission() {
        mViewModel.apply {
            runTimePermissions(listOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE)) { permissionGranted, isPermanentlyDenied ->
                with(resources) {
                    if (permissionGranted) {
                        if (!permissionGranted && !isPermanentlyDenied) {
                            mViewModel.showToast("Permission Denied")
                        }
                        if (permissionGranted && !isPermanentlyDenied) {
                            // Do task
                            mViewModel.showToast("Accessing Storage Media")
                        }
                    } else if (isPermanentlyDenied) {
                        mViewModel.showToast("Goto Settings and Please grant Permissions")
                        this@ScopeStorage.openSettings()
                    }
                }
            }
        }
    }



}