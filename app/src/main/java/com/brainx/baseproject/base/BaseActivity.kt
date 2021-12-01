package com.brainx.baseproject.base

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.brainx.baseproject.api.SharedPreference
import com.brainx.androidext.ext.showToast
import com.brainx.androidbase.base.BxBaseActivity
import com.brainx.androidbase.network.stateManager.NetState
import javax.inject.Inject


abstract class BaseActivity<VM : ViewModel, VB : ViewDataBinding> : BxBaseActivity<VM, VB>() {

    //region Properties
    @Inject
    lateinit var sharedPreference: SharedPreference
    //endregion

    //region LifeCycle
    override fun onNetworkStateChanged(netState: NetState) {
        super.onNetworkStateChanged(netState)
        showToast(netState.isSuccess.toString())
    }
    //endregion
}
