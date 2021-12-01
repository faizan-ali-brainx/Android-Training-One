package com.brainx.baseproject.base

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.brainx.baseproject.api.SharedPreference
import com.brainx.androidbase.base.BxBaseFragment
import javax.inject.Inject

abstract class BaseFragment<VM : ViewModel, VB : ViewDataBinding> : BxBaseFragment<VM, VB>() {

    @Inject
    lateinit var sharedPreference: SharedPreference

}
