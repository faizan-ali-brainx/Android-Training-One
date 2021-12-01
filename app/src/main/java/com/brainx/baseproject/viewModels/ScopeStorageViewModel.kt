package com.brainx.baseproject.viewModels

import android.content.Context
import com.brainx.baseproject.api.SharedPreference
import com.brainx.baseproject.base.BaseViewModel
import com.brainx.baseproject.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class ScopeStorageViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    private val sPref: SharedPreference,
    private val authRepo: AuthRepository
) : BaseViewModel() {

}